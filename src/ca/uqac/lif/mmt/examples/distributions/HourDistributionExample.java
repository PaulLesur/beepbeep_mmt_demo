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
package ca.uqac.lif.mmt.examples.distributions;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.tmf.CountDecimate;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetConnectionHour;
import ca.uqac.lif.mmt.processors.BarChartGenerator;
import ca.uqac.lif.mmt.processors.BucketCounter;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;

/**
 * An example of BeepBeep's usage: a distribution of the hour of each connection event of a log file.
 * <p>
 *  The hour of each connection goes through the chain of processors.
 *  a PNG image is then generated to display the resulting distribution on a graph.
 * </p>
 * <p>
 *  This example illustrates the use of {@link BarChartGenerator} processor.
 * </p>
 * <p>
 *  Represented graphically, this example corresponds to the following chain of processors:
 * </p>
 * <p>
 *  <img src="{@docRoot}/img/MMT-hourDistribution.png" alt="Processor graph">
 * </p>
 */
public class HourDistributionExample {

    public static void main(String args[]){
        /* Extracting data from source file */
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        /* Getting hour of connection */
        FunctionProcessor hourGetter = new FunctionProcessor(new GetConnectionHour());

        /* Counting occurrences of hours */
        FunctionProcessor counter = new FunctionProcessor(new BucketCounter());

        CountDecimate decimate = new CountDecimate(100);

        Pump pump = new Pump();

        BarChartGenerator chartGenerator = new BarChartGenerator("Number of connection per hour", "Hours", "Number of connections", "hours.png", 1920,1080);

        Connector.connect(source, hourGetter, counter, decimate, pump, chartGenerator);

        while(true){
            pump.run();
        }
    }
}
