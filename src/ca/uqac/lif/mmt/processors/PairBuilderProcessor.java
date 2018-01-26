package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import java.util.Queue;

public class PairBuilderProcessor extends SingleProcessor{
    public PairBuilderProcessor() {
        super(2,1);
    }

    @Override
    protected boolean compute(Object[] inputs, Queue<Object[]> output) {

//        Object[] o = {("["+inputs[0]+","+inputs[1]+"]")};
        double[] temp = new double[2];


        try{
            temp[0] = (new Double((float)inputs[0]));
        }
        catch (ClassCastException e){
            try{
                temp[0] = (new Double((double)inputs[0]));
            }
            catch (ClassCastException e2){
                temp[0] = (new Double((int)inputs[0]));
            }
        }

        try{
            temp[1] = (new Double((float)inputs[1]));
        } catch (ClassCastException e){
            try{
                temp[1] = (new Double((double)inputs[1]));
            } catch (ClassCastException e2){
                temp[1] = (new Double((int)inputs[1]));
            }
        }


//                {(new Double((float)inputs[0])),(new Double((float)inputs[1]))};



        DoublePoint out = new DoublePoint(temp);

        Object[] o = {out};

        output.add(o);

        return true;
    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
