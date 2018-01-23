package ca.uqac.lif.mmt.examples.duration;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.mmt.functions.GetDuration;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.SetBuilderProcessor;


import java.io.FileNotFoundException;

public class DurationExample {

    private static int k = 5;
    private static int refreshInterval = 5;

    public static void main(String args[]) throws FileNotFoundException {

        // Extracting data from source file
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        //Retrieving duration for each Connection object
        FunctionProcessor durationExtractor = new FunctionProcessor(new GetDuration());

        // Building set, needed for K-means algorithm
        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k, refreshInterval);

        // Creating K-means function
        KMeansFunction kmf = new KMeansFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);

        // Building the pipeline
        Connector.connect(source, durationExtractor, setBuilder, fp);


        Pullable p = fp.getPullableOutput();

        while(p.hasNext()){
            System.out.println((p.pull()).toString());
        }
    }
}
