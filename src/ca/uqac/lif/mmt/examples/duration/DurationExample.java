package ca.uqac.lif.mmt.examples.duration;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetDuration;
import ca.uqac.lif.mmt.functions.KMeansSmartFunction;
import ca.uqac.lif.mmt.processors.ClustersDataFormatter;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.ScatterPlotGenerator;
import ca.uqac.lif.mmt.processors.SetBuilderProcessor;


import java.io.FileNotFoundException;

public class DurationExample {

    private static int k = 5;
    private static int refreshInterval = 100;

    public static void main(String args[]) throws FileNotFoundException {

        // Extracting data from source file
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        //Retrieving duration for each Connection object
        FunctionProcessor durationExtractor = new FunctionProcessor(new GetDuration());

        // Building set, needed for K-means algorithm
        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k, refreshInterval);

        // Creating K-means function
        KMeansFunction kmf = new KMeansSmartFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);


        // Setting up a printer to print data
        ClustersDataFormatter dataFormatter = new ClustersDataFormatter();
        Pump pump = new Pump();
        ScatterPlotGenerator plotGenerator = new ScatterPlotGenerator("Durations", "Duration of the connection", "", "duration.png");
        FunctionProcessor printer = new FunctionProcessor(plotGenerator);


        // Building the pipeline
        Connector.connect(source, durationExtractor, setBuilder, fp, dataFormatter, pump, printer);


        while (true){
            pump.run();
        }
    }
}
