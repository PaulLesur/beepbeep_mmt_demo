package ca.uqac.lif.mmt.parser.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.mmt.parser.Connection;
import ca.uqac.lif.mmt.parser.Parser;

import java.util.Queue;

public class ConnectionBuilder extends SingleProcessor{

    public ConnectionBuilder() {
        super(1, 1);
    }

    protected boolean compute(Object[] inputs, Queue<Object[]> outputs) {

        System.out.println(inputs[0].toString());

        Connection connection = Parser.parseConnection(inputs[0].toString());

        Object[] o = {connection};

        outputs.add(o);

        return true;
    }

    public Processor duplicate() {
        return null;
    }
}
