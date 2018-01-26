package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BarChartGenerator extends SingleProcessor{

    private String title;
    private String xAxis;
    private String yAxis;
    private String fileName;
    private int xResolution;
    private int yResolution;



    public BarChartGenerator(String title, String xAxis, String yAxis, String fileName, int xResolution, int yResolution) {
        super(1,0);
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.fileName = fileName;
        this.xResolution = xResolution;
        this.yResolution = yResolution;
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> outputs) {

        HashMap<String, Integer> input = (HashMap<String, Integer>) inputs[0];

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : input.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            dataset.addValue(value, this.title, key);

        }

        

        JFreeChart barChart = ChartFactory.createBarChart(
                this.title,
                this.xAxis,
                this.yAxis,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        BufferedImage image = barChart.createBufferedImage(this.xResolution,this.yResolution);
        File imageFile = new File(this.fileName);
        try {
            ImageIO.write(image, "PNG", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
