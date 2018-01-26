package ca.uqac.lif.mmt.examples.bytes;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.mtnp.DrawPlot;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetDestinationBytes;
import ca.uqac.lif.mmt.functions.GetSourceBytes;
import ca.uqac.lif.mmt.processors.*;
import ca.uqac.lif.mtnp.plot.gral.Scatterplot;

public class BytesKMeansExample {

    private static int k = 20;
    private static int refreshInterval = 100;
    private static float limit = 10000.0f;

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


        // On convertit en table exploitable
        MultisetToTableProcessor table = new MultisetToTableProcessor(1, "x", "y");
        Connector.connect(fp, table);

        Scatterplot scatterplot = new Scatterplot();
        scatterplot.withLines(false);
//        scatterplot.setPalette(Plot.QUALITATIVE_1);


        DrawPlot draw = new DrawPlot(scatterplot);

        Connector.connect(table, draw);

        BitmapJFrame window = new BitmapJFrame();

        Pump pump = new Pump();

        Connector.connect(draw, pump);
        Connector.connect(pump,window);



//
//        Pullable p = table.getPullableOutput();
//        while (p.hasNext()){
//            System.out.println(p.pull());
//            p.pull();
//
//        }




        window.start();


        System.out.println("Displaying plot. Press Ctrl+C or close the window to end.");
        while(true){
            pump.run();
//            Thread.sleep(1000);
//            System.out.println("ERG");
        }







    }

}
