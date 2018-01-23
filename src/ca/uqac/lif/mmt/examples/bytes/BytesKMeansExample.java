package ca.uqac.lif.mmt.examples.bytes;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.gnuplot.GnuplotScatterplot;
import ca.uqac.lif.cep.mtnp.DrawPlot;
import ca.uqac.lif.cep.mtnp.UpdateTable;
import ca.uqac.lif.cep.mtnp.UpdateTableArray;
import ca.uqac.lif.cep.mtnp.UpdateTableStream;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.NaryToArray;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.cep.tuples.TupleFeeder;
import ca.uqac.lif.mmt.functions.GetDestinationBytes;
import ca.uqac.lif.mmt.functions.GetDuration;
import ca.uqac.lif.mmt.functions.GetSourceBytes;
import ca.uqac.lif.mmt.parser.Connection;
import ca.uqac.lif.mmt.processors.BitmapJFrame;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.SetBuilderProcessor;
import ca.uqac.lif.mtnp.plot.Plot;
import ca.uqac.lif.mtnp.plot.gnuplot.Scatterplot;

import java.io.FileNotFoundException;

public class BytesKMeansExample {

    private static int k = 10;

    public static void main(String args[]) throws FileNotFoundException, InterruptedException {

        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");
        Fork fork = new Fork(2);
        Connector.connect(source, fork);

        FunctionProcessor sourceBytes = new FunctionProcessor(new GetSourceBytes());
        FunctionProcessor destinationBytes= new FunctionProcessor(new GetDestinationBytes());
        Connector.connect(fork, 0, sourceBytes,0);
        Connector.connect(fork, 1, destinationBytes,0);

//        UpdateTable table = new UpdateTableStream("x","y");
//        Connector.connect(sourceBytes, 0, nta, Connector.TOP);
//        Connector.connect(destinationBytes, 0, nta, Connector.BOTTOM);

        TupleFeeder
        Connector.connect(sourceBytes, 0, table, 0);
        Connector.connect(destinationBytes, 0, table, 1);

        KMeansFunction kmf = new KMeansFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);

        Connector.connect(table, fp);



        DrawPlot draw = new DrawPlot(new ca.uqac.lif.mtnp.plot.gral.Scatterplot());
//        draw.setImageType(Plot.ImageType.PNG);

        Connector.connect(fp, draw);

        BitmapJFrame window = new BitmapJFrame();

        Pump pump = new Pump();

        Connector.connect(draw, pump);
        Connector.connect(pump,window);


        window.start();


//        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k);
//
//

//
//
//        Connector.connect(source, durationExtractor, setBuilder, fp);

//        Pullable p = window.getPullableOutput();


        System.out.println("Displaying plot. Press Ctrl+C or close the window to end.");
        while(true){
            pump.run();
            Thread.sleep(1000);
        }

    }

}
