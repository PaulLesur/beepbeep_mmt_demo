package ca.uqac.lif.mmt.duration;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.io.LineReader;
import ca.uqac.lif.mmt.parser.processors.ConnectionBuilder;

import java.io.File;
import java.io.FileNotFoundException;

public class Duration {
    public static void main(String args[]) throws FileNotFoundException {

        LineReader source = new LineReader(new File("./data/2006/11/20061101.txt"));
        ConnectionBuilder cc = new ConnectionBuilder();

//        DataMiningProcessor dmp = new DataMiningProcessor

        Connector.connect(source, cc);

        Pullable p = cc.getPullableOutput(0);

        while(p.hasNext()){
            p.pull();
        }

    }
}
