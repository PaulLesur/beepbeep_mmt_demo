package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.sets.Multiset;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

public class ClustersDataFormatter  extends SingleProcessor{
    public ClustersDataFormatter() {
        super(1,1);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        Multiset inSet = (Multiset) inputs[0];

        XYSeriesCollection result = new XYSeriesCollection();


//            CentroidCluster<DoublePoint> currentCluster = (CentroidCluster<DoublePoint>)inArray[i];
//            System.out.println(i+" : "+currentCluster.toString());

        Iterator iteratorSet = inSet.iterator();

        int counter = 0;
        while(iteratorSet.hasNext()){
//                System.out.println(iteratorSet.next());

            CentroidCluster<DoublePoint> currentCluster = (CentroidCluster<DoublePoint>) iteratorSet.next();
//            System.out.println(currentCluster);

            XYSeries series = new XYSeries(String.valueOf(counter));
            int nbPointsOfCurrentCluster = currentCluster.getPoints().size();

            for (int i = 0; i<nbPointsOfCurrentCluster; i++ ){

//                System.out.println(counter+":"+i);
                DoublePoint temp = (DoublePoint) currentCluster.getPoints().get(i);

                double x = temp.getPoint()[0];
                double y = temp.getPoint()[1];

                series.add(x, y);
            }


            result.addSeries(series);
            counter++;
        }

//
//
//            DoublePoint temp = (DoublePoint) inArray[i];
//
//            double x = temp.getPoint()[0];
//            double y = temp.getPoint()[1];
//
//            series.add(x, y);





        Object[]out = {result};
        output.add(out);



        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
