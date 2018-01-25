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
import java.util.Queue;

public class BarChartGenerator extends SingleProcessor{
    public BarChartGenerator() {
        super(1,0);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> outputs) {

        int[] input = (int[]) inputs[0];

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i = 0; i<input.length; i++){
            dataset.addValue(input[i], "Hour", String.valueOf(i));
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Hours",
                "Hours",
                "Number of connections",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        BufferedImage image = barChart.createBufferedImage(800,600);
        File imageFile = new File("hours.png");
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
