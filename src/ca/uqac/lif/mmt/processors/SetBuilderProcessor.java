package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


public class SetBuilderProcessor extends SingleProcessor{

    private HashSet<Object> localSet;
    private int minimum;
    private int interval;

    public SetBuilderProcessor(int min, int interval) {
        super(1,1);
        this.localSet = new HashSet<>();
        this.minimum = min;
        this.interval = interval;
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        this.localSet.add(inputs[0]);

        Object[] o = {this.localSet};

        System.out.println();

        if (this.localSet.size()%interval==0){
            output.add(o);
        }





        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
