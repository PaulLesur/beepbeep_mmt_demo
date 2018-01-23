package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;
import ca.uqac.lif.cep.mtnp.UpdateTable;
import ca.uqac.lif.cep.sets.Multiset;
import ca.uqac.lif.mtnp.table.HardTable;
import ca.uqac.lif.mtnp.table.TableEntry;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import java.util.HashSet;
import java.util.Queue;

public class MultisetToTableProcessor extends UpdateTable {

    public MultisetToTableProcessor(int in_arity, String... column_names) {
        super(in_arity, column_names);
    }

    @Override
    protected boolean compute(Object[] inputs, Object[] outputs) {


        this.m_table = new HardTable("x","y");

        HashSet m = (HashSet) inputs[0];

        Object[] a = m.toArray();

//        System.out.println(((DoublePoint)(a[0])).getPoint()[0]);

        for (int i = 0; i<a.length; i++){
            DoublePoint temp = (DoublePoint) a[i];

            Object[] o = {temp.getPoint()[0], temp.getPoint()[1]};

            this.m_table.add(this.createEntry(o));
//            System.out.println("Add : "+this.createEntry(o).toString());

        }

        outputs[0] = this.m_table;
        return true;




    }

    @Override
    public Processor duplicate() {
        return null;
    }
}
