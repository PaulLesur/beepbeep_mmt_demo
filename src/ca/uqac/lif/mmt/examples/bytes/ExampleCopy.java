package ca.uqac.lif.mmt.examples.bytes;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Palette;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.mtnp.DrawPlot;
import ca.uqac.lif.cep.mtnp.UpdateTable;
import ca.uqac.lif.cep.mtnp.UpdateTableStream;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.Passthrough;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.diagramPalette.MultisetToDataset;
import ca.uqac.lif.mmt.diagramPalette.ScatterPlotPrinter;
import ca.uqac.lif.mmt.functions.GetDestinationBytes;
import ca.uqac.lif.mmt.functions.GetSourceBytes;
import ca.uqac.lif.mmt.processors.*;
import ca.uqac.lif.mtnp.plot.Plot;
import ca.uqac.lif.mtnp.plot.gral.Scatterplot;

import java.awt.*;

public class ExampleCopy {

    private static int k = 20;
    private static int refreshInterval = 100;
    private static float limit = 100000.0f;

    public static void main(String args[]) {

        // Lire les connexions dans le fichier et les filtrer
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");
        BytesFilterProcessor filter = new BytesFilterProcessor(limit);
        Fork fork = new Fork(2);
        Connector.connect(source, filter, fork);

        // Extraction des paramètres intéressants
        FunctionProcessor sourceBytes = new FunctionProcessor(new GetSourceBytes());
        FunctionProcessor destinationBytes= new FunctionProcessor(new GetDestinationBytes());
        Connector.connect(fork, 0, sourceBytes,0);
        Connector.connect(fork, 1, destinationBytes,0);

//        UpdateTable table = new UpdateTableStream("Sent Bytes","Received Bytes");
//        Connector.connect(sourceBytes, 0, table, Connector.TOP);
//        Connector.connect(destinationBytes, 0, table, Connector.BOTTOM);

        // On construit une paire qui correspond aux coordonnées
        PairBuilderProcessor pairBuilder = new PairBuilderProcessor();
        Connector.connect(sourceBytes, 0, pairBuilder, 0);
        Connector.connect(destinationBytes, 0, pairBuilder, 1);

        // On construit un set de données exploitables pour KMeans
        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k, refreshInterval);
        Connector.connect(pairBuilder, setBuilder);

        // On applique KMeans
        KMeansFunction kmf = new KMeansFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);

        Connector.connect(setBuilder, fp);

        MultisetToDataset dataSetBuilder = new MultisetToDataset();
        Connector.connect(fp,dataSetBuilder);

        Pump pump = new Pump();
        ScatterPlotPrinter printer = new ScatterPlotPrinter();

        Connector.connect(dataSetBuilder, pump, printer);

        Pullable p = fp.getPullableOutput();

        while(true){
            pump.run();
        }

//        System.out.println("Traitement terminé");



//        Pullable p = printer.getPullableOutput();
//        while (p.hasNext()){
////            System.out.println("OUT : "+p.pull());
//            p.pull();
//
//        }







    }

}
