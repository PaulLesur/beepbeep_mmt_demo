package ca.uqac.lif.mmt.diagramPalette;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.Title;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Queue;

public class ScatterPlotPrinter extends SingleProcessor{

    private boolean firstRun = true;
    ChartFrame frame;
    JFreeChart chart;
    XYSeriesCollection dataset;

    public ScatterPlotPrinter() {
        super(1,0);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {


        dataset = (XYSeriesCollection) inputs[0];

        chart = ChartFactory.createScatterPlot(
                "Scatter Plot", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

//        System.out.println(((XYSeriesCollection) inputs[0]).getSeries(0).getItems().get(0));;
//        System.out.println(((XYSeriesCollection)chart.getXYPlot().getDataset()).getSeries().get(0));

        BufferedImage image = chart.createBufferedImage(800,600);
        File imageFile = new File("Bytes.png");
        try {
            ImageIO.write(image, "PNG", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }



//        if (firstRun) {
//            firstRun = false;
////            // create and display a frame...
////            frame = new ChartFrame("First", chart);
////            frame.pack();
////            frame.setVisible(true);
//        } else {
//            //TODO Refresh
////            frame.getChartPanel().getChart().fireChartChanged();
//
//        }


        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}

//frame.getChartPanel().removeAll();
//        frame.getChartPanel().revalidate();
//        ChartPanel panel = new ChartPanel(chart);
//        frame.getChartPanel().setLayout(new BorderLayout());
//        frame.getChartPanel().add(panel);
//        frame.getChartPanel().repaint();