package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.mmt.parser.Connection;

import java.util.Queue;

public class ExtractConnectionDuration extends SingleProcessor{
    public ExtractConnectionDuration() {
        super(1,1);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        Connection inputConnection = (Connection)inputs[0];

        Object[] o = {inputConnection.getConnectionDuration()};

        output.add(o);

        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
