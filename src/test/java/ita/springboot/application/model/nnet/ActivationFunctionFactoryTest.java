package ita.springboot.application.model.nnet;

import org.encog.engine.network.activation.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ActivationFunctionFactoryTest {

    private ActivationFunctionFactory activationFunctionFactoryUnderTest;

    @Before
    public void setUp() {
        activationFunctionFactoryUnderTest = new ActivationFunctionFactory();
    }

    @Test
    public void testCreateBiPolar() {
        final String activationType = "BiPolar";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationBiPolar);
    }

    @Test
    public void testCreateBiPolarSteepenedSigmoid() {
        final String activationType = "BipolarSteepenedSigmoid";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationBipolarSteepenedSigmoid);
    }

    @Test
    public void testCreateActivationClippedLinear() {
        final String activationType = "ClippedLinear";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationClippedLinear);
    }

    @Test
    public void testCreateCompetitive() {
        final String activationType = "Competitive";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationCompetitive);
    }

    @Test
    public void testCreateElliott() {
        final String activationType = "Elliott";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationElliott);
    }

    @Test
    public void testCreateElliottSymmetric() {
        final String activationType = "ElliottSymmetric";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationElliottSymmetric);
    }

    @Test
    public void testCreateGaussian() {
        final String activationType = "Gaussian";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationGaussian);
    }

    @Test
    public void testCreateLinear() {
        final String activationType = "Linear";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationLinear);
    }

    @Test
    public void testCreateLOG() {
        final String activationType = "LOG";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationLOG);
    }

    @Test
    public void testCreateRamp() {
        final String activationType = "Ramp";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationRamp);
    }


    @Test
    public void testCreateSigmoid() {
        final String activationType = "Sigmoid";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationSigmoid);
    }


    @Test
    public void testCreateSIN() {
        final String activationType = "SIN";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationSIN);
    }


    @Test
    public void testCreateSoftMax() {
        final String activationType = "SoftMax";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationSoftMax);
    }

    @Test
    public void testCreateSteepenedSigmoid() {
        final String activationType = "SteepenedSigmoid";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationSteepenedSigmoid);
    }

    @Test
    public void testCreateStep() {
        final String activationType = "Step";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationStep);
    }

    @Test
    public void testCreateTANH() {
        final String activationType = "TANH";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertTrue(result instanceof ActivationTANH);
    }

    @Test
    public void testCreateNull() {
        final String activationType = "null";
        final ActivationFunction result = activationFunctionFactoryUnderTest.create(activationType);
        assertNull(result);
    }

}
