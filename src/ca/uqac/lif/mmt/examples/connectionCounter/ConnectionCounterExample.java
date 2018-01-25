package ca.uqac.lif.mmt.examples.connectionCounter;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetConnectionHour;
import ca.uqac.lif.mmt.processors.BarChartGenerator;
import ca.uqac.lif.mmt.processors.BucketCounter;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.StackerProcessor;

public class ConnectionCounterExample {

    public static void main(String args[]){



        // Extracting data from source file
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        // Getting hour of connection
        FunctionProcessor hourGetter = new FunctionProcessor(new GetConnectionHour());

        // Counting occurrences of hours
        FunctionProcessor counter = new FunctionProcessor(new BucketCounter(25));

        StackerProcessor stacker = new StackerProcessor(100);

        Pump pump = new Pump();

        BarChartGenerator chartGenerator = new BarChartGenerator();


        Connector.connect(source, hourGetter, counter, stacker, pump, chartGenerator);


        while(true){
            pump.run();
        }







    }
}
