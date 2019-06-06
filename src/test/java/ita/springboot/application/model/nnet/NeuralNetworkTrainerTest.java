package ita.springboot.application.model.nnet;

import org.encog.engine.network.activation.ActivationBiPolar;
import org.encog.engine.network.activation.ActivationLinear;
import org.encog.neural.networks.BasicNetwork;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeuralNetworkTrainerTest {

    NeuralNetworkTrainer neuralNetworkTrainerUnderTest = new NeuralNetworkTrainer(new NeuralNetworkDummyError());

    @Test
    public void expect_OK_when_activation_is_bipolar_testBuiltNetwork() {
        int inputSize = 100;
        int outputSize = 10;
        int hiddenLayersCount = 1;
        int hiddenLayerNeuronsCount = 100;
        final String activationType = "BiPolar";
        final int testedHiddenLayer = 1;
        BasicNetwork networkUnderTest = neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
        assertEquals(inputSize, networkUnderTest.getInputCount());
        assertEquals(outputSize, networkUnderTest.getOutputCount());
        assertTrue(networkUnderTest.getActivation(1) instanceof ActivationBiPolar);
        assertEquals(hiddenLayersCount + 2, networkUnderTest.getLayerCount());
        assertEquals(hiddenLayerNeuronsCount, networkUnderTest.getLayerNeuronCount(testedHiddenLayer));
    }

    @Test
    public void expect_linear_when_activation_is_other_testBuiltNetwork() {
        int inputSize = 100;
        int outputSize = 10;
        int hiddenLayersCount = 1;
        int hiddenLayerNeuronsCount = 100;
        final String activationType = "NonExistantActivation";
        final int testedHiddenLayer = 1;
        BasicNetwork networkUnderTest = neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
        assertEquals(inputSize, networkUnderTest.getInputCount());
        assertEquals(outputSize, networkUnderTest.getOutputCount());
        assertTrue(networkUnderTest.getActivation(1) instanceof ActivationLinear);
        assertEquals(hiddenLayersCount + 2, networkUnderTest.getLayerCount());
        assertEquals(hiddenLayerNeuronsCount, networkUnderTest.getLayerNeuronCount(testedHiddenLayer));
        ;
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void expect_error_when_inputSize_negative_testBuiltNetwork() {
        int inputSize = -1;
        int outputSize = 10;
        int hiddenLayersCount = 1;
        int hiddenLayerNeuronsCount = 100;
        final String activationType = "NonExistantActivation";
        neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void expect_error_when_outputSize_negative_testBuiltNetwork() {
        int inputSize = 100;
        int outputSize = -10;
        int hiddenLayersCount = 1;
        int hiddenLayerNeuronsCount = 100;
        final String activationType = "NonExistantActivation";
        neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
    }

    @org.junit.Test(expected = NegativeArraySizeException.class)
    public void expect_error_when_hiddenLayerNeuronsCount_negative_testBuiltNetwork() {
        int inputSize = 100;
        int outputSize = 10;
        int hiddenLayersCount = 2;
        int hiddenLayerNeuronsCount = -1;
        final String activationType = "NonExistantActivation";
        neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
    }

    @org.junit.Test(expected = OutOfMemoryError.class)
    public void expect_error_when_negative_testBuiltNetwork() {
        int inputSize = 100;
        int outputSize = 10;
        int hiddenLayersCount = 999999999;
        int hiddenLayerNeuronsCount = 999999;
        final String activationType = "NonExistantActivation";
        neuralNetworkTrainerUnderTest.
                buildNetwork(inputSize, outputSize, activationType, hiddenLayersCount, hiddenLayerNeuronsCount);
    }


    private class NeuralNetworkDummyError implements INeuralNetworkError {
        @Override
        public double getError(double[] outputData, double[] idealData) {
            return 0;
        }
    }

}
