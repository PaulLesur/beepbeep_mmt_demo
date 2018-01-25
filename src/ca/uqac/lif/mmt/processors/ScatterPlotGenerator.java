package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.FunctionException;
import ca.uqac.lif.cep.functions.SimpleFunction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class ScatterPlotGenerator extends SimpleFunction{

    JFreeChart chart;
    XYSeriesCollection dataset;

    private String title;
    private String xAxis;
    private String yAxis;
    private String fileName;

    public ScatterPlotGenerator(String title, String xAxis, String yAxis, String fileName){
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.fileName = fileName;
    }

    @Override
    public void compute(Object[] inputs, Object[] outputs) throws FunctionException {

        dataset = (XYSeriesCollection) inputs[0];

        chart = ChartFactory.createScatterPlot(
                this.title, // chart title
                this.xAxis, // x axis label
                this.yAxis, // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        BufferedImage image = chart.createBufferedImage(800,600);
        File imageFile = new File(this.fileName);
        try {
            ImageIO.write(image, "PNG", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInputArity() {
        return 1;
    }

    @Override
    public int getOutputArity() {
        return 0;
    }

    @Override
    public void reset() {

    }

    @Override
    public void getInputTypesFor(Set<Class<?>> set, int i) {

    }

    @Override
    public Class<?> getOutputTypeFor(int i) {
        return null;
    }

    @Override
    public Function duplicate() {
        return null;
    }
}
