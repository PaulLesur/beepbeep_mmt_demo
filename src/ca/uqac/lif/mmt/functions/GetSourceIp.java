package ca.uqac.lif.mmt.functions;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.FunctionException;
import ca.uqac.lif.cep.functions.SimpleFunction;
import ca.uqac.lif.mmt.parser.Connection;
import ca.uqac.lif.mmt.parser.arguments.IpAddress;

import java.util.Set;

public class GetSourceIp extends SimpleFunction{
    @Override
    public void compute(Object[] inputs, Object[] outputs) throws FunctionException {
        outputs[0] = ((Connection)inputs[0]).getSourceIpAddress();
    }

    @Override
    public int getInputArity() {
        return 1;
    }

    @Override
    public int getOutputArity() {
        return 1;
    }

    @Override
    public void reset() {

    }

    @Override
    public void getInputTypesFor(Set<Class<?>> set, int i) {
        set.add(Connection.class);
    }

    @Override
    public Class<?> getOutputTypeFor(int i) {
        return IpAddress.class;
    }

    @Override
    public Function duplicate() {
        return null;
    }
}
