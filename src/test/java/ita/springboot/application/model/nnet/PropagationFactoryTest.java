package ita.springboot.application.model.nnet;

import com.google.common.graph.Network;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;


import org.encog.engine.network.activation.ActivationBiPolar;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.propagation.scg.ScaledConjugateGradient;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.encog.neural.networks.BasicNetwork;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropagationFactoryTest {


    public static double XOR_INPUT[][] = {{0.0, 0.0}, {1.0, 0.0},
            {0.0, 1.0}, {1.0, 1.0}};
    public static double XOR_IDEAL[][] = {{0.0, 0.0}, {1.0, 0.0},
            {0.0, 1.0}, {1.0, 1.0}};

    private PropagationFactory propagationFactoryUnderTest;

    private BasicNetwork network;

    private MLDataSet mlDataSet;

    private MLDataSet mlDataSetSingle;

    @BeforeEach
    public void setUp() {
        network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 2));
        network.addLayer(new BasicLayer(new ActivationLinear(), true, 5));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
        network.getStructure().finalizeStructure();
        network.reset();

        mlDataSet = new BasicMLDataSet(XOR_INPUT, XOR_IDEAL);
    }

    @After
    public void tearDown() {
        Encog.getInstance().shutdown();
    }


    @Test
    public void testResilientPropagation() {
        // Setup
        final String trainingType = "ResilientPropagation";

        // Run the test
        final Propagation train = new PropagationFactory().create(trainingType, network, mlDataSet);

        // Verify the results
        assertTrue(train instanceof ResilientPropagation);
    }

    @Test
    public void testScaledConjugateGradient() {
        // Setup
        final String trainingType = "ScaledConjugateGradient";

        // Run the test
        final Propagation train = new PropagationFactory().create(trainingType, network, mlDataSet);

        // Verify the results
        assertTrue(train instanceof ScaledConjugateGradient);
    }

    @Test
    public void testManhattanPropagation() {
        // Setup
        final String trainingType = "ManhattanPropagation";

        // Run the test
        final Propagation train = new PropagationFactory().create(trainingType, network, mlDataSet);

        // Verify the results
        assertTrue(train instanceof ManhattanPropagation);
    }

    @Test
    public void tesPropagation_shouldBeNull() {
        final String trainingType = "StyleDoesNotExist";
        assertNull(new PropagationFactory().create(trainingType, network, mlDataSet));
    }


}
