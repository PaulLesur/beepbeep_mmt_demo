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
        Set<DoublePoint> d_points = new HashSet();

        // Iterates on each point of the original dataset
        Iterator iterator1 = points.iterator();
        while(iterator1.hasNext()) {
            Object o = iterator1.next();
            // Point is cast to DoublePoint and added to d_points hashset
            d_points.add(DoublePointCast.getDoublePoint(o));
        }
        // List of clusters
        List<CentroidCluster<DoublePoint>> clusters = clusterer.cluster(d_points);

        Multiset clustersSet = new Multiset();
        Iterator iterator2 = clusters.iterator();
        while (iterator2.hasNext())
        {
            clustersSet.add((CentroidCluster)iterator2.next());
        }

        return clustersSet;
    }

}
