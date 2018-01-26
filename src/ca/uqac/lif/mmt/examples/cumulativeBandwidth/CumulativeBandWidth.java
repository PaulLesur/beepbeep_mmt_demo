package ca.uqac.lif.mmt.examples.cumulativeBandwidth;

import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.functions.*;
import ca.uqac.lif.cep.numbers.Addition;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.Slicer;
import ca.uqac.lif.cep.tuples.MergeTuples;
import ca.uqac.lif.cep.tuples.ScalarIntoTuple;
import netp.PacketSource;
import netp.functions.GetSourceIp;

public class CumulativeBandWidth {

    public static void main(String args[]){

        //TODO arrivée du flux



        PacketSource source = new PacketSource("test.pcap");

        Fork fork = new Fork(2);

        GroupProcessor groupProcessor = new BandWidthProcessors();

        Slicer slicerSrc = new Slicer(new GetSourceIp(), groupProcessor);

        ConstantProcessor constantProcessor = new ConstantProcessor(new Constant(1));
        CumulativeProcessor cumulativeProcessorAddition = new CumulativeProcessor(new CumulativeFunction<Number>(Addition.instance));
        FunctionProcessor tTupleBuilderProcessor = new FunctionProcessor(new ScalarIntoTuple("t"));

        FunctionProcessor mergeTuples = new FunctionProcessor(new MergeTuples());

//        CumulativeProcessor cumulativeProcessorUnion = new CumulativeProcessor(new CumulativeFunction<>())






    }

}
