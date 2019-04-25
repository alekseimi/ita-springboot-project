package ita.springboot.application.model.nnet;

import org.encog.Encog;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;

import java.io.IOException;


public class HandwrittenNN {

    private static int iterationsCount;
    private static int epochsCount;
    private static int minHiddenLayerNeuronCount;
    private static int maxHiddenLayerNeuronCount;
    private static int hiddenLayerNeuronCountStep;
    private static int minHiddenLayerCount;
    private static int maxHiddenLayerCount;

    public static void main(final String args[]) throws IOException, InvalidFileFormatException {

        /*
        int iterationsCount, int epochsCount,
        int minHiddenLayerNeuronCount,
        int maxHiddenLayerNeuronCount,
        int hiddenLayerNeuronCountStep,
        int minHiddenLayerCount,
        int maxHiddenLayerCount
         */

        NeuralNetworkTrainer trainer = createNeuralNetworkTrainer(iterationsCount,
                epochsCount,
                minHiddenLayerNeuronCount,
                maxHiddenLayerNeuronCount,
                hiddenLayerNeuronCountStep,
                minHiddenLayerCount,
                maxHiddenLayerCount);

        MLDataSet trainingSet = getDataSet("C:\\Users\\Aleksej\\Documents\\MNIST\\train-labels.idx1-ubyte",
                "C:\\Users\\Aleksej\\Documents\\MNIST\\train-images.idx3-ubyte");
        MLDataSet validationSet = getDataSet("C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-labels.idx1-ubyte",
                "C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-images.idx3-ubyte");

        trainer.setTrainingSet(trainingSet);
        trainer.setValidationSet(validationSet);

        trainer.train();

        // Use network to make predictions, or save weights somewhere
        // BasicNetwork bestNetwork = trainer.getBestNetwork();

        Encog.getInstance().shutdown();
    }


    /**
     * iterationsCount = 3;
     * epochsCount = 200;
     * <p>
     * minHiddenLayerNeuronCount = 100;
     * maxHiddenLayerNeuronCount = 200;
     * hiddenLayerNeuronCountStep = 100;
     * <p>
     * minHiddenLayerCount = 1;
     * maxHiddenLayerCount = 2;
     */

    private static NeuralNetworkTrainer createNeuralNetworkTrainer(int iterationsCount, int epochsCount,
                                                                   int minHiddenLayerNeuronCount,
                                                                   int maxHiddenLayerNeuronCount,
                                                                   int hiddenLayerNeuronCountStep,
                                                                   int minHiddenLayerCount,
                                                                   int maxHiddenLayerCount) {

        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer(new ClassificationError());

        trainer.setIterationsCount(iterationsCount);
        trainer.setEpochsCount(epochsCount);
        trainer.setMinHiddenLayerNeuronCount(minHiddenLayerNeuronCount);
        trainer.setMaxHiddenLayerNeuronCount(maxHiddenLayerNeuronCount);
        trainer.setHiddenLayerNeuronCountStep(hiddenLayerNeuronCountStep);
        trainer.setMinHiddenLayerCount(minHiddenLayerCount);
        trainer.setMaxHiddenLayerCount(maxHiddenLayerCount);

        return trainer;
    }

    private static MLDataSet getDataSet(String labelsFileName, String imagesFileName) throws IOException, InvalidFileFormatException {
        MNISTReader mnistReader = new MNISTReader();

        double[][] images = mnistReader.readImages(imagesFileName);
        double[][] labels = mnistReader.readLabels(labelsFileName);

        MLDataSet dataSet = new BasicMLDataSet(images, labels);

        return dataSet;
    }


    public HandwrittenNN() {
    }

    public static int getIterationsCount() {
        return iterationsCount;
    }

    public static void setIterationsCount(int iterationsCount) {
        HandwrittenNN.iterationsCount = iterationsCount;
    }

    public static int getEpochsCount() {
        return epochsCount;
    }

    public static void setEpochsCount(int epochsCount) {
        HandwrittenNN.epochsCount = epochsCount;
    }

    public static int getMinHiddenLayerNeuronCount() {
        return minHiddenLayerNeuronCount;
    }

    public static void setMinHiddenLayerNeuronCount(int minHiddenLayerNeuronCount) {
        HandwrittenNN.minHiddenLayerNeuronCount = minHiddenLayerNeuronCount;
    }

    public static int getMaxHiddenLayerNeuronCount() {
        return maxHiddenLayerNeuronCount;
    }

    public static void setMaxHiddenLayerNeuronCount(int maxHiddenLayerNeuronCount) {
        HandwrittenNN.maxHiddenLayerNeuronCount = maxHiddenLayerNeuronCount;
    }

    public static int getHiddenLayerNeuronCountStep() {
        return hiddenLayerNeuronCountStep;
    }

    public static void setHiddenLayerNeuronCountStep(int hiddenLayerNeuronCountStep) {
        HandwrittenNN.hiddenLayerNeuronCountStep = hiddenLayerNeuronCountStep;
    }

    public static int getMinHiddenLayerCount() {
        return minHiddenLayerCount;
    }

    public static void setMinHiddenLayerCount(int minHiddenLayerCount) {
        HandwrittenNN.minHiddenLayerCount = minHiddenLayerCount;
    }

    public static int getMaxHiddenLayerCount() {
        return maxHiddenLayerCount;
    }

    public static void setMaxHiddenLayerCount(int maxHiddenLayerCount) {
        HandwrittenNN.maxHiddenLayerCount = maxHiddenLayerCount;
    }
}
