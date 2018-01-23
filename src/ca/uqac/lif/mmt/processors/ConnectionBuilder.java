package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.mmt.parser.Connection;
import ca.uqac.lif.mmt.parser.Parser;

import java.util.Queue;

/**
 * Processor that takes a String and create a {@link Connection} from this String.
 */
public class ConnectionBuilder extends SingleProcessor{

    /**
     * This processor has 1 input and 1 output
     */
    public ConnectionBuilder() {
        super(1, 1);
    }

    /**
     * Compute the tranformation from String to {@link Connection}. It calls a method from the {@link Parser} class.
     * @param inputs A String
     * @param outputs The {@link Connection} created from the input
     * @return
     */
    protected boolean compute(Object[] inputs, Queue<Object[]> outputs) {

        Connection connection = Parser.parseConnection(inputs[0].toString());

        Object[] o = {connection};

        outputs.add(o);

        return true;
    }

    public Processor duplicate() {
        return null;
    }
}
