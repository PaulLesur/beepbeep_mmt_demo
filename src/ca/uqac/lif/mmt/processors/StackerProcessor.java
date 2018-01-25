package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.UniformProcessor;

import java.util.Queue;

public class StackerProcessor extends SingleProcessor{

    private int refresh;
    private int counter;

    public StackerProcessor(int refresh) {
        super(1,1);
        this.refresh = refresh;
        this.counter = 0;
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> outputs) {

        counter++;

        if (this.counter%refresh == 0){
            outputs.add(inputs);
        }

        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
