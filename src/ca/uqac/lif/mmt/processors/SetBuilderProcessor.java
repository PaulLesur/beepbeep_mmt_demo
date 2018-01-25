package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.sets.Multiset;

import java.util.Queue;
import java.util.Set;


public class SetBuilderProcessor extends SingleProcessor{

    private Multiset localSet;
    private int minimum;
    private int interval;
    private int tranche = 0;

    public SetBuilderProcessor(int min, int interval) {
        super(1,1);
        this.localSet = new Multiset();
        this.minimum = min;
        this.interval = interval;
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

        this.localSet.add(inputs[0]);


//        System.out.println(this.localSet.size()+"%"+interval+"="+((this.localSet.size()%interval)));
        if ((this.localSet.size()%interval)==0){
            Object[] o = {this.localSet};
            output.add(o);
            tranche+=interval;
        }





        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
