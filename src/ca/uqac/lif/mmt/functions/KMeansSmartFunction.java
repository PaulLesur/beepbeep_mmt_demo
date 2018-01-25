package ca.uqac.lif.mmt.functions;

import ca.uqac.lif.cep.peg.ml.DoublePointCast;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.sets.Multiset;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class KMeansSmartFunction extends KMeansFunction {


    public KMeansSmartFunction(int k) {
        super(k);
    }


    @Override
    public Set<?> getValue(Set points) {
        //Clustering tool
        KMeansPlusPlusClusterer<DoublePoint> clusterer = new KMeansPlusPlusClusterer(this.m_k);
        //
        Set<DoublePoint> d_points = new HashSet();
        // Iterates on each point of the original dataset
        Iterator iter = points.iterator();

        while(iter.hasNext()) {
            // Next point
            Object o = iter.next();
            // Point is cast to DoublePoint and added to d_points hashset
            d_points.add(DoublePointCast.getDoublePoint(o));
        }
        // List of clusters OR list of centers ?
        List<CentroidCluster<DoublePoint>> clusters = clusterer.cluster(d_points);

        Multiset clustersSet = new Multiset();

        Iterator iterator = clusters.iterator();
        while (iterator.hasNext())
        {
            clustersSet.add((CentroidCluster)iterator.next());
        }

        return clustersSet;


        // Empty list of centers
//        Set<DoublePoint> centers = new HashSet();
//        // Iterates on each cluster
//        Iterator var6 = clusters.iterator();
//
//        while(var6.hasNext()) {
//            // Gets the current cluster
//            CentroidCluster<DoublePoint> cluster = (CentroidCluster)var6.next();
//            //Adds the center to the list of centers
//            centers.add((DoublePoint)cluster.getCenter());
//        }
//
//        return centers;
    }

}
