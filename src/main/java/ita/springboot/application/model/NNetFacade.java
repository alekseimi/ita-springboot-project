package ita.springboot.application.model;

import org.encog.ConsoleStatusReportable;
import org.encog.engine.network.activation.*;
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.versatile.NormalizationHelper;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.model.EncogModel;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.simple.EncogUtility;
import org.springframework.context.annotation.Bean;
import sun.rmi.server.Activation;


public class NNetFacade {


    /*


    public BasicNetwork createNN( ActivationFunctionType activationFunctionType) {
        BasicNetwork network = new BasicNetwork();
        ActivationFunction activationFunction = getActivationFunction(activationFunctionType);
        network.addLayer(new BasicLayer(null, true, 1));


        return network;
    }
     */

    public EncogModel buildModel(VersatileMLDataSet dataSet){
        EncogModel model = new EncogModel(dataSet);
        model.selectMethod(dataSet, MLMethodFactory.TYPE_FEEDFORWARD);
        model.setReport(new ConsoleStatusReportable());
        dataSet.normalize();

        model.holdBackValidation(0.3, true, 10);
        model.selectTrainingType(dataSet);
        MLRegression bestMethod = (MLRegression)model.crossvalidate(5, true);

        System.out.println("Training error: " + EncogUtility.calculateRegressionError(bestMethod,
                model.getTrainingDataset()));
        System.out.println("Validation error" + EncogUtility.calculateRegressionError(bestMethod,
                model.getValidationDataset()));

        NormalizationHelper helper = dataSet.getNormHelper();
        System.out.println("Final model: " + bestMethod);


        return model;
    }

    public void train(BasicNetwork basicNetwork, MLDataSet training){
        final Train train = new ResilientPropagation(basicNetwork, training);
    }

   private static ActivationFunction getActivationFunction(ActivationFunctionType activationFunctionType){
        ActivationFunction activationFunction;
        switch (activationFunctionType) {
            case BIPOLAR:
                activationFunction = new ActivationBiPolar();
                break;
            case LINEAR:
                activationFunction = new ActivationLinear();
                break;
            case LOG:
                activationFunction = new ActivationLOG();
                break;
            case TANH:
                activationFunction = new ActivationTANH();
                break;
            case SIGMOID:
                activationFunction = new ActivationSigmoid();
                break;
            default:
                activationFunction = new ActivationStep();
                break;
        }

        return activationFunction;
    }
}
