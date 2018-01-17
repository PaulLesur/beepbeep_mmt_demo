package ca.uqac.lif.mmt.chaine1;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.functions.CumulativeFunction;
import ca.uqac.lif.cep.functions.CumulativeProcessor;
import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.numbers.Addition;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.Slicer;
import ca.uqac.lif.cep.tuples.ExpandAsColumns;
import ca.uqac.lif.cep.tuples.JoinSet;
import ca.uqac.lif.cep.tuples.MergeTuples;
import ca.uqac.lif.cep.tuples.ScalarIntoTuple;
import netp.functions.GetFlowSize;
import netp.functions.GetSourceIp;

public class BandWidthProcessors extends GroupProcessor {
    public BandWidthProcessors() {
        super(1, 1);


        Fork fork = new Fork(2);

        // Branch 1
        FunctionProcessor getSize = new FunctionProcessor(new GetFlowSize());
        CumulativeProcessor sumBandWidthProcessor = new CumulativeProcessor(new CumulativeFunction<Number>(Addition.instance));
        FunctionProcessor sizeTupleBuilderProcessor = new FunctionProcessor(new ScalarIntoTuple("size"));


        // Branch 2
        FunctionProcessor getSrcIp = new FunctionProcessor(new GetSourceIp());
        FunctionProcessor srcIpTupleBuilderProcessor = new FunctionProcessor(new ScalarIntoTuple("srcIp"));


        FunctionProcessor mergeSizeSrcIpProcessor = new FunctionProcessor(new MergeTuples());
        FunctionProcessor eac = new FunctionProcessor(new ExpandAsColumns("srcIp", "size"));

        // Connector
        associateInput(0, fork, 0);
        Connector.connect(fork, 0, getSize, 0);
        Connector.connect(getSize, sumBandWidthProcessor);
        Connector.connect(sumBandWidthProcessor, sizeTupleBuilderProcessor);

        Connector.connect(fork, 1, getSrcIp, 0);
        Connector.connect(getSrcIp, srcIpTupleBuilderProcessor);

        Connector.connect(sizeTupleBuilderProcessor, 0, mergeSizeSrcIpProcessor, 0);
        Connector.connect(srcIpTupleBuilderProcessor, 0, mergeSizeSrcIpProcessor, 1);

        Connector.connect(mergeSizeSrcIpProcessor, eac);

        associateOutput(0, eac, 0);


    }
}
