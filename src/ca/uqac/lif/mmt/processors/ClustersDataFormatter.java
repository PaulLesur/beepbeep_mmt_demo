package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.sets.Multiset;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Iterator;
import java.util.Queue;


/**
 * Processor that takes a Multiset and format it to XYSeriesCollection
 */
public class ClustersDataFormatter  extends SingleProcessor{
    public ClustersDataFormatter() {
        super(1,1);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        // Input
        Multiset inSet = (Multiset) inputs[0];

        // The XYSeriesCollection returned
        XYSeriesCollection result = new XYSeriesCollection();

        // Iterator used to iterate over the input Multiset
        Iterator iteratorSet = inSet.iterator();

        int counter = 0;

        // We iterate over the input Multiset
        while(iteratorSet.hasNext()){
            CentroidCluster<DoublePoint> currentCluster = (CentroidCluster<DoublePoint>) iteratorSet.next();
            XYSeries series = new XYSeries(String.valueOf(counter));
            int nbPointsOfCurrentCluster = currentCluster.getPoints().size();

            // Iterate over each DoublePoint of the cluster
            for (int i = 0; i<nbPointsOfCurrentCluster; i++ ){
                DoublePoint temp = (DoublePoint) currentCluster.getPoints().get(i);
                double x = temp.getPoint()[0];
                double y = 0;
                try{
                     y = temp.getPoint()[1];
                } catch (ArrayIndexOutOfBoundsException e){ // prevent exception
                }


                // We add the DoublePoint to the series, each series corresponds to a cluster.
                series.add(x, y);
            }

            // We add each cluster to the output dataset
            result.addSeries(series);
            counter++;
        }


        Object[]out = {result};
        output.add(out);



        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
