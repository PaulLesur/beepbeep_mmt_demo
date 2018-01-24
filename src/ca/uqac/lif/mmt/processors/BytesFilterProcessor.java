package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.mmt.parser.Connection;

import java.util.Queue;




public class BytesFilterProcessor extends SingleProcessor{

    private float limit;

    public BytesFilterProcessor(float limit) {
        super(1,1);
        this.limit = limit;
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        Connection in = (Connection) inputs[0];

        if (!(
                (in.getDestinationBytes()>limit)
                ||
                (in.getSourceBytes()>limit)
        ))
        {
            Object[] o = {in};
            output.add(o);
        }


        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
