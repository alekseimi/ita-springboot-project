package ita.springboot.application.modelTests;

import org.encog.Encog;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.EncogUtility;
import org.encog.util.simple.TrainingSetUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Basic;
import javax.swing.plaf.basic.BasicScrollBarUI;

@SpringBootTest
public class DataFacadeTest {

    private MLDataSet trainingSet;
    private BasicNetwork basicNetwork;

    @Before
    public void loadData(){
        trainingSet = TrainingSetUtil.loadCSVTOMemory(CSVFormat.ENGLISH,
              "src/test/testFile.csv"  , false, 2, 1);
        basicNetwork = EncogUtility.simpleFeedForward(2, 4, 0, 1, true);
    }



}
