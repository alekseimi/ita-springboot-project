package ita.springboot.application.model;

import org.encog.ConsoleStatusReportable;
import org.encog.app.analyst.EncogAnalyst;
import org.encog.app.analyst.script.normalize.AnalystField;
import org.encog.ml.MLRegression;
import org.encog.ml.data.versatile.NormalizationHelper;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.columns.ColumnDefinition;
import org.encog.ml.data.versatile.columns.ColumnType;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.model.EncogModel;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.EncogUtility;

import java.io.File;

public class DataNormalization {

    private File dataSourceFile;

    /*
    public void normalizeData(){
        EncogAnalyst analyst = new EncogAnalyst();
        AnalystWizard wizard = new AnalystWizard(analyst);
        wizard.wizard(sourceFile, true, AnalystFileFormat.DECPNT_COMMA);
        final AnalystNormalizeCSV norm = new AnalystNormalizeCSV();
        norm.analyze(sourceFile, true, CSVFormat.ENGLISH, analyst);
        norm.setProduceOutputHeaders(true);
        norm.normalize(targetFile);
        Encog.getInstance().shutdown();
    }
   */


    public VersatileMLDataSet dataInput(){
        VersatileDataSource source = new CSVDataSource(dataSourceFile, false, CSVFormat.DECIMAL_POINT);
        VersatileMLDataSet data = new VersatileMLDataSet(source);

        data.defineSourceColumn("sepal-length", 0, ColumnType.continuous);
        data.defineSourceColumn("sepal-width", 1, ColumnType.continuous);
        data.defineSourceColumn("petal-length", 2, ColumnType.continuous);
        data.defineSourceColumn("petal-width", 3, ColumnType.continuous);

        ColumnDefinition outputColumn = data.defineSourceColumn("species", 4,
                ColumnType.nominal);
        data.analyze();
        data.defineSingleOutputOthersInput(outputColumn);
        return data;


    }

    public void dumpFieldInfo(EncogAnalyst analyst){
        System.out.println("Fields found in the file:");
        for (AnalystField field: analyst.getScript().getNormalize()
        .getNormalizedFields()){
            StringBuilder line = new StringBuilder();
            line.append(field.getName());
            line.append(",action=");
            line.append(field.getAction());
            line.append(",min=");
            line.append(field.getActualLow());
            line.append(",max=");
            line.append(field.getActualHigh());
            System.out.println(line.toString());
        }
    }
    public void setDataSourceFile(File dataSourceFile){
        this.dataSourceFile = dataSourceFile;
    }
}
