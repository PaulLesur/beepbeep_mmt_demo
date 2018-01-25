package ca.uqac.lif.mmt.diagramPalette;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.sets.Multiset;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.HashSet;
import java.util.Queue;

public class MultisetToDataset  extends SingleProcessor{
    public MultisetToDataset() {
        super(1,1);
    }

    private boolean isWindowOpen = false;

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        HashSet inSet = (HashSet) inputs[0];
        Object[] inArray = inSet.toArray();

        XYSeriesCollection result = new XYSeriesCollection();

        XYSeries series = new XYSeries("Key");

        for (int i = 0; i < inArray.length; i++) {
            DoublePoint temp = (DoublePoint) inArray[i];

            double x = temp.getPoint()[0];
            double y = temp.getPoint()[1];

            series.add(x, y);
        }


        result.addSeries(series);

        Object[]out = {result};
        output.add(out);



        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
