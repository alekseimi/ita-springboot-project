package ita.springboot.application.model.nnet;

import ita.springboot.application.web.dto.NNetSettingsDto;
import org.encog.Encog;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;

import java.io.IOException;
import java.util.ArrayList;

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


public class HandwrittenNN {

    private String activationType;
    private String trainingType;

    private int iterationsCount;
    private int epochsCount;
    private int hiddenLayerCount;
    private int hiddenLayerNeuronCount;

    public BasicNetwork createModel() throws IOException, InvalidFileFormatException {
        NeuralNetworkTrainer trainer = createNeuralNetworkTrainer();
        /*
        MLDataSet trainingSet = getDataSet("C:\\Users\\Aleksej\\Documents\\MNIST\\train-labels.idx1-ubyte",
                "C:\\Users\\Aleksej\\Documents\\MNIST\\train-images.idx3-ubyte");
        MLDataSet validationSet = getDataSet("C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-labels.idx1-ubyte",
                "C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-images.idx3-ubyte");

         D:\MNIST\

         */
        MLDataSet trainingSet = getDataSet("D:\\MNIST\\train-labels.idx1-ubyte",
                "D:\\MNIST\\train-images.idx3-ubyte");
        MLDataSet validationSet = getDataSet("D:\\MNIST\\t10k-labels.idx1-ubyte",
                "D:\\MNIST\\t10k-images.idx3-ubyte");

        trainer.setTrainingSet(trainingSet);
        trainer.setValidationSet(validationSet);

        int inputSize = trainingSet.get(0).getInputArray().length;
        int outputSize = trainingSet.get(0).getIdealArray().length;

        trainer.train(inputSize, outputSize, epochsCount, trainingType, activationType, hiddenLayerCount, hiddenLayerNeuronCount);
        BasicNetwork network = trainer.getBestNetwork();
        Encog.getInstance().shutdown();
        return network;
    }

    public NeuralNetworkTrainer createNeuralNetworkTrainer() {
        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer(new ClassificationError());
        return trainer;
    }


    public MLDataSet getDataSet(String labelsFileName, String imagesFileName) throws IOException, InvalidFileFormatException {
        MNISTReader mnistReader = new MNISTReader();

        double[][] images = mnistReader.readImages(imagesFileName);
        double[][] labels = mnistReader.readLabels(labelsFileName);

        MLDataSet dataSet = new BasicMLDataSet(images, labels);

        return dataSet;
    }


    public int getIterationsCount() {
        return iterationsCount;
    }

    public HandwrittenNN setIterationsCount(int iterationsCount) {
        this.iterationsCount = iterationsCount;
        return this;
    }

    public int getEpochsCount() {
        return epochsCount;
    }

    public HandwrittenNN setEpochsCount(int epochsCount) {
        this.epochsCount = epochsCount;
        return this;
    }

    public String getActivationType() {
        return activationType;
    }

    public HandwrittenNN setActivationType(String activationType) {
        this.activationType = activationType;
        return this;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public HandwrittenNN setTrainingType(String trainingType) {
        this.trainingType = trainingType;
        return this;
    }

    public int getHiddenLayerCount() {
        return hiddenLayerCount;
    }

    public HandwrittenNN setHiddenLayerCount(int hiddenLayerCount) {
        this.hiddenLayerCount = hiddenLayerCount;
        return this;
    }

    public int getHiddenLayerNeuronCount() {
        return hiddenLayerNeuronCount;
    }

    public HandwrittenNN setHiddenLayerNeuronCount(int hiddenLayerNeuronCount) {
        this.hiddenLayerNeuronCount = hiddenLayerNeuronCount;
        return this;
    }
}




    /*


        public NeuralNetworkTrainer createNeuralNetworkTrainer() {

        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer(new ClassificationError());

        String[] activationTypeArray = new String[1];
        activationTypeArray[0] = activationType;

        String[] trainingTypeArray = new String[1];
        trainingTypeArray[0] = trainingType;

        trainer.setIterationsCount(iterationsCount);
        trainer.setEpochsCount(epochsCount);
        trainer.setMinHiddenLayerNeuronCount(minHiddenLayerNeuronCount);
        trainer.setMaxHiddenLayerNeuronCount(maxHiddenLayerNeuronCount);
        trainer.setHiddenLayerNeuronCountStep(hiddenLayerNeuronCountStep);
        trainer.setMinHiddenLayerCount(minHiddenLayerCount);
        trainer.setMaxHiddenLayerCount(maxHiddenLayerCount);
        trainer.setActivationTypes(activationTypeArray);
        trainer.setTrainingTypes(trainingTypeArray);
        return trainer;
    }
     */