package ita.springboot.application.model.nnet;

import org.encog.ml.data.MLDataSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MNISTReaderTest {

    private MNISTReader mnistReaderUnderTest;


    /*
    //Desktop
    final String imagesSourceTrain = "D:\\MNIST\\train-images.idx3-ubyte";

    final String labelsSourceTrain = "D:\\MNIST\\train-labels.idx1-ubyte";

    final String imagesSourceValidation = "D:\\MNIST\\t10k-images.idx3-ubyte";

    final String labelsSourceValidation = "D:\\MNIST\\t10k-labels.idx1-ubyte";

     */



    final String imagesSourceTrain = "C:\\Users\\Aleksej\\Documents\\MNIST\\train-images.idx3-ubyte";

    final String labelsSourceTrain = "C:\\Users\\Aleksej\\Documents\\MNIST\\train-labels.idx1-ubyte";

    final String imagesSourceValidation = "C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-images.idx3-ubyte";

    final String labelsSourceValidation =  "C:\\Users\\Aleksej\\Documents\\MNIST\\t10k-labels.idx1-ubyte";


    @Before
    public void setUp() {
        mnistReaderUnderTest = new MNISTReader();
    }

    @Test
    public void testReadImages() throws Exception {

        final int expectedResult = 10000;
        try {
            final double[][] result = mnistReaderUnderTest.readImages(imagesSourceValidation);
            assertEquals(expectedResult, result.length);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReadImages_ThrowsIOException() throws Exception {
        final String fileName = "fileName";

        assertThrows(IOException.class, () -> {
            mnistReaderUnderTest.readImages(fileName);
        });
    }

    @Test
    public void testReadImages_ThrowsInvalidFileFormatException() throws Exception {
        final String fileName = "D:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.1\\product-info.json";
        assertThrows(InvalidFileFormatException.class, () -> {
            mnistReaderUnderTest.readImages(fileName);
        });
    }

    @Test
    public void testReadLabels() throws Exception {
        // Setup
        final int expectedResult = 10000;
        try {
            final double[][] result = mnistReaderUnderTest.readLabels(labelsSourceValidation);
            assertEquals(expectedResult, result.length);
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadLabels_ThrowsIOException() throws Exception {

        final String fileName = "shouldThrowIOException";

        assertThrows(IOException.class, () -> {
            mnistReaderUnderTest.readLabels(fileName);
        });
    }

    @Test
    public void testReadLabels_ThrowsInvalidFileFormatException() throws Exception {


        // Setup
        final String fileName = "D:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.1\\product-info.json";
        final double[][] expectedResult = new double[][]{};

        // Run the test
        assertThrows(InvalidFileFormatException.class, () -> {
            mnistReaderUnderTest.readLabels(fileName);
        });
    }
}
