package ca.uqac.lif.mmt.examples.connectionCounter;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.tmf.CountDecimate;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetConnectionHour;
import ca.uqac.lif.mmt.functions.GetConnectionPort;
import ca.uqac.lif.mmt.processors.BarChartGenerator;
import ca.uqac.lif.mmt.processors.BucketCounter;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;

/**
 * An example of BeepBeep's usage in data mining: a distribution of the duration of each connection event of a log file.
 * <p>
 *  The duration of each connection goes through the chain of processors.
 *  a PNG image is then generated to display the resulting distribution on a graph.
 * </p>
 * <p>
 *  This example illustrates the use of {@link BarChartGenerator} processor.
 * </p>
 * <p>
 *  Represented graphically, this example corresponds to the following chain of processors:
 * </p>
 * <p>
 *  <img src="{@docRoot}/img/MMT-BytesKMeansExample.png" alt="Processor graph">
 * </p>
 */
public class PortExample {

    public static void main(String args[]){
        /* Extracting data from source file */
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        /* Getting hour of connection */
        FunctionProcessor hourGetter = new FunctionProcessor(new GetConnectionPort());

        /* Counting occurrences of hours */
        FunctionProcessor counter = new FunctionProcessor(new BucketCounter(25));


        CountDecimate decimate = new CountDecimate(100);

        Pump pump = new Pump();

        BarChartGenerator chartGenerator = new BarChartGenerator("Ports distribution", "Port", "Number of connections", "ports.png", 4000, 1080);


        Connector.connect(source, hourGetter, counter, decimate, pump, chartGenerator);


        while(true){
            pump.run();
        }
    }
}
