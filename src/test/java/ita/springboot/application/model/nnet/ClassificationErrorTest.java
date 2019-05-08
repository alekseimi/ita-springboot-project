package ita.springboot.application.model.nnet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ClassificationErrorTest {

    private ClassificationError classificationErrorUnderTest;

    @Before
    public void setUp() {
        classificationErrorUnderTest = new ClassificationError();
    }

    @Test
    public void testGetError_expectNoError() {
        // Setup
        final double[] outputData = new double[]{0, 0};
        final double[] idealData = new double[]{1, 1};
        final double expectedResult = 0.0;

        // Run the test
        final double result = classificationErrorUnderTest.getError(outputData, idealData);

        // Verify the results 
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testGetError_expectError() {
        // Setup
        final double[] outputData = new double[]{-1, -1};
        final double[] idealData = new double[]{0, 0};
        final double expectedResult = 1.0;

        // Run the test
        final double result = classificationErrorUnderTest.getError(outputData, idealData);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }
}
