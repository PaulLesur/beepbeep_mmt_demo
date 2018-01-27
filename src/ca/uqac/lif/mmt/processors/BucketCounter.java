/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2018 Sylvain Hallé
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.
    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.FunctionException;
import ca.uqac.lif.cep.functions.SimpleFunction;

import java.util.HashMap;
import java.util.Set;

public class BucketCounter extends SimpleFunction{

    private HashMap<String, Integer> buckets;

    public BucketCounter(){
        this.buckets = new HashMap<>();
    }

    @Override
    public void compute(Object[] inputs, Object[] outputs) throws FunctionException {

        String bucket = String.valueOf((int) inputs[0]);


        if (this.buckets.get(bucket)!=null){
            this.buckets.put(bucket, this.buckets.get(bucket) + 1);
        } else {
            this.buckets.put(bucket, 1);
        }



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
