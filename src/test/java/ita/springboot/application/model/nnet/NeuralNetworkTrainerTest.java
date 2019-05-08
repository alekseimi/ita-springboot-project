package ita.springboot.application.model.nnet;

import ita.springboot.application.model.NNetResult;
import org.encog.neural.networks.BasicNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NeuralNetworkTrainerTest {

    public static double XOR_INPUT[][] = {{0.0, 0.0}, {1.0, 0.0},
            {0.0, 1.0}, {1.0, 1.0}};
    public static double XOR_IDEAL[][] = {{0.0, 0.0}, {1.0, 0.0},
            {0.0, 1.0}, {1.0, 1.0}};

    @Mock
    private INeuralNetworkError mockNeuralNetworkError;

    private NeuralNetworkTrainer neuralNetworkTrainerUnderTest;


    @BeforeEach
    private void setUp() {
        initMocks(this);
        neuralNetworkTrainerUnderTest = new NeuralNetworkTrainer(mockNeuralNetworkError);
    }

    @Test
    public void testTrain() throws Exception {
        // Setup
        final int inputSize = 0;
        final int outputSize = 0;
        final int iterationsCount = 0;
        final int epochsCount = 0;
        final String trainingType = "trainingType";
        final String activationType = "activationType";
        final int hiddenLayersCount = 0;
        final int hiddenLayerNeuronCount = 0;
        final NNetResult expectedResult = null;
        when(mockNeuralNetworkError.getError(new double[]{}, new double[]{})).thenReturn(0.0);

        // Run the test
        final NNetResult result = neuralNetworkTrainerUnderTest.train(inputSize, outputSize, iterationsCount, epochsCount, trainingType, activationType, hiddenLayersCount, hiddenLayerNeuronCount);

        // Verify the results 
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTrain_ThrowsIOException() throws Exception {
        // Setup
        final int inputSize = 0;
        final int outputSize = 0;
        final int iterationsCount = 0;
        final int epochsCount = 0;
        final String trainingType = "trainingType";
        final String activationType = "activationType";
        final int hiddenLayersCount = 0;
        final int hiddenLayerNeuronCount = 0;
        final NNetResult expectedResult = null;
        when(mockNeuralNetworkError.getError(new double[]{}, new double[]{})).thenReturn(0.0);

        // Run the test
        assertThrows(IOException.class, () -> {
            neuralNetworkTrainerUnderTest.train(inputSize, outputSize, iterationsCount, epochsCount, trainingType, activationType, hiddenLayersCount, hiddenLayerNeuronCount);
        });
    }


}
