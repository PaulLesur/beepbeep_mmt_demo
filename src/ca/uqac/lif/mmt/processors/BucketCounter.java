package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.FunctionException;
import ca.uqac.lif.cep.functions.SimpleFunction;

import java.util.Set;

public class BucketCounter extends SimpleFunction{

    private int numberOfBuckets;
    private int[] buckets;

    public BucketCounter(int numberOfBuckets){
        this.numberOfBuckets = numberOfBuckets;
        this.buckets = new int[numberOfBuckets];
    }

    @Override
    public void compute(Object[] inputs, Object[] outputs) throws FunctionException {

        int bucket = (int) inputs[0];

        this.buckets[bucket]++;

        outputs[0] = this.buckets;


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

    }

    @Override
    public Class<?> getOutputTypeFor(int i) {
        return null;
    }

    @Override
    public Function duplicate() {
        return null;
    }
}
