package ca.uqac.lif.mmt.duration;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.mmt.processors.ExtractConnectionDuration;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.SetBuilderProcessor;


import java.io.FileNotFoundException;

public class DurationExample {

    private static int k = 5;

    public static void main(String args[]) throws FileNotFoundException {

        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        ExtractConnectionDuration durationExtractor = new ExtractConnectionDuration();

        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k);

        KMeansFunction kmf = new KMeansFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);


        Connector.connect(source, durationExtractor, setBuilder, fp);

        Pullable p = fp.getPullableOutput();

        while(p.hasNext()){
            System.out.println((p.pull()).toString());
        }

    }
}
