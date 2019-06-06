package ita.springboot.application.model.nnet;

import ita.springboot.application.model.NNetResult;
import org.encog.engine.network.activation.ActivationFunction;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.Propagation;

import javax.persistence.Basic;
import java.io.IOException;

public class NeuralNetworkTrainer {
    private String[] activationTypes = new String[] { "Elliott", "LOG", "Ramp", "Sigmoid", "SteepenedSigmoid" };
    private String[] trainingTypes = new String[] { "QuickPropagation", "ResilientPropagation", "Backpropagation", "ScaledConjugateGradient", "ManhattanPropagation" };

    private MLDataSet trainingSet;
    private MLDataSet validationSet;

    private int epochsCount = 200;
    private int iterationsCount = 3;

    private int minHiddenLayerNeuronCount = 500;
    private int maxHiddenLayerNeuronCount = 500;
    private int hiddenLayerNeuronCountStep = 100;

    private int minHiddenLayerCount = 2;
    private int maxHiddenLayerCount = 2;


    private BasicNetwork bestNetwork;
    private double bestError = Double.MAX_VALUE;

    private INeuralNetworkError neuralNetworkError;

    public void setTrainingSet(MLDataSet trainingSet) {
        this.trainingSet = trainingSet;
    }

    public void setValidationSet(MLDataSet validationSet) {
        this.validationSet = validationSet;
    }

    public void setActivationTypes(String[] activationTypes) {
        this.activationTypes = activationTypes;
    }

    public void setTrainingTypes(String[] trainingTypes) {
        this.trainingTypes = trainingTypes;
    }

    public void setEpochsCount(int epochsCount) {
        this.epochsCount = epochsCount;
    }

    public void setIterationsCount(int iterationsCount) {
        this.iterationsCount = iterationsCount;
    }

    public void setMinHiddenLayerNeuronCount(int minHiddenLayerNeuronCount) {
        this.minHiddenLayerNeuronCount = minHiddenLayerNeuronCount;
    }

    public void setMaxHiddenLayerNeuronCount(int maxHiddenLayerNeuronCount) {
        this.maxHiddenLayerNeuronCount = maxHiddenLayerNeuronCount;
    }

    public void setHiddenLayerNeuronCountStep(int hiddenLayerNeuronCountStep) {
        this.hiddenLayerNeuronCountStep = hiddenLayerNeuronCountStep;
    }

    public void setMinHiddenLayerCount(int minHiddenLayerCount) {
        this.minHiddenLayerCount = minHiddenLayerCount;
    }

    public void setMaxHiddenLayerCount(int maxHiddenLayerCount) {
        this.maxHiddenLayerCount = maxHiddenLayerCount;
    }

    public BasicNetwork getBestNetwork() {
        return bestNetwork;
    }

    public NeuralNetworkTrainer(INeuralNetworkError neuralNetworkError) {
        this.neuralNetworkError = neuralNetworkError;
    }

    public NNetResult train(int inputSize, int outputSize, int iterationsCount, int epochsCount,
                            String trainingType, String activationType, int hiddenLayersCount, int hiddenLayerNeuronCount) throws IOException {
        long elapsedSum = 0;
        double validationErrorSum = 0;
        double classificationErrorSum = 0;

        for (int i = 1; i <= iterationsCount; i++) {
            long start = System.currentTimeMillis();

            BasicNetwork network = trainNetwork(inputSize, outputSize, epochsCount, trainingType, activationType, hiddenLayersCount, hiddenLayerNeuronCount);
            ErrorRate validationError = calculateError(validationSet, network);

            validationErrorSum += validationError.NeuralNetworkErrorRate;
            classificationErrorSum += validationError.ClassificationErrorRate;

            long end = System.currentTimeMillis();
            long elapsed = end - start;
            elapsedSum += elapsed;

            System.out.println("Validation error: " + validationError.ClassificationErrorRate + " Elapsed: " + elapsed);
            if(validationError.ClassificationErrorRate < bestError) {
                bestError = validationError.ClassificationErrorRate;
                bestNetwork = network;
            }
        }

        double averageValidationError = validationErrorSum / iterationsCount;
        long averageElapsed = elapsedSum / iterationsCount;
        double averageClassificationError = classificationErrorSum / iterationsCount;

        displayTrainingProgress(iterationsCount, epochsCount, trainingType, activationType, hiddenLayersCount, hiddenLayerNeuronCount,
                averageValidationError, averageElapsed, averageClassificationError);

        NNetResult nNetResults = new NNetResult(trainingType, activationType,
                epochsCount, iterationsCount,
                hiddenLayersCount, hiddenLayerNeuronCount,
                averageValidationError, averageElapsed, averageClassificationError);
        return nNetResults;
    }

    public BasicNetwork buildNetwork(int inputSize, int outputSize, String activationType, int hiddenLayersCount, int hiddenLayerNeuronsCount) {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, inputSize));
        final ActivationFunction activationFunction = new ActivationFunctionFactory().create(activationType);
        for (int i = 1; i <= hiddenLayersCount; i++) {
            network.addLayer(new BasicLayer(activationFunction, true, hiddenLayerNeuronsCount));
        }
        network.addLayer(new BasicLayer(activationFunction, false, outputSize));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }

    private BasicNetwork trainNetwork(
            int inputSize, int outputSize, int epochsCount, String trainingType, String activationType, int hiddenLayersCount, int hiddenLayerNeuronsCount) {

        BasicNetwork network = buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
        final Propagation train = new PropagationFactory().create(trainingType, network, trainingSet);
        for(int epoch = 1; epoch <= epochsCount; epoch++) {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
        }

        train.finishTraining();
        return network;
    }

    private ErrorRate calculateError(MLDataSet validationSet, BasicNetwork network) {
        double error = 0;

        for(int i = 0; i < validationSet.size(); i++) {
            MLDataPair dataPair = validationSet.get(i);

            MLData output = network.compute(dataPair.getInput());
            double[] outputData = output.getData();
            double[] idealData = dataPair.getIdealArray();

            double sampleError = neuralNetworkError.getError(outputData, idealData);
            error += sampleError;
        }

        ErrorRate errorRate = new ErrorRate();
        errorRate.ClassificationErrorRate = error / validationSet.size();
        errorRate.NeuralNetworkErrorRate = network.calculateError(validationSet);
        return errorRate;
    }


    private void displayTrainingProgress(int iterationsCount, int epochsCount,
                                         String trainingType, String activationType, int hiddenLayersCount, int hiddenLayerNeuronCount,
                                         double averageValidationError, long averageElapsed, double averageClassificationError) {

        System.out.println(
                trainingType + "," +
                        activationType + "," +
                        hiddenLayersCount + "," +
                        hiddenLayerNeuronCount + "," +
                        epochsCount + "," +
                        averageValidationError + "," +
                        averageElapsed + "," +
                        iterationsCount + "," +
                        averageClassificationError + ",");
    }
}
     /*

         public void train() throws IOException {
        int inputSize = trainingSet.get(0).getInputArray().length;
        int outputSize = trainingSet.get(0).getIdealArray().length;

        for (String trainingType : trainingTypes) {
            for (String activationType : activationTypes) {
                for (int hiddenLayersCount = minHiddenLayerCount; hiddenLayersCount <= maxHiddenLayerCount; hiddenLayersCount++) {
                    for (int hiddenLayerNeuronCount = minHiddenLayerNeuronCount; hiddenLayerNeuronCount <= maxHiddenLayerNeuronCount; hiddenLayerNeuronCount += hiddenLayerNeuronCountStep) {
                        train(inputSize, outputSize, trainingType, activationType, hiddenLayersCount, hiddenLayerNeuronCount);
                    }
                }
              }

   */