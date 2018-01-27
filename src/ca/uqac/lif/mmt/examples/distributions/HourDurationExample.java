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
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.*;
import ca.uqac.lif.mmt.processors.*;

/**
 * An example of BeepBeep's usage in data mining: an application of improved K-Means algorithm on
 * connection duration per hour in a connection log.
 * <p>
 *  The time of connection and the duration for each analyzed connection go through the K-Means clustering algorithm,
 *  a PNG image is then generated to display the resulting clusters on a graph.
 * </p>
 * <p>
 *  This example illustrates the use of {@link KMeansSmartFunction} and {@link ScatterPlotGenerator} objects.
 * </p>
 * <p>
 *  Represented graphically, this example corresponds to the following chain of processors:
 * </p>
 * <p>
 *  <img src="{@docRoot}/img/MMT-DurationHour.png" alt="Processor graph">
 * </p>
 */
public class HourDurationExample {

    private static int k = 5;
    private static int refreshInterval = 100;
    private static float limit = 10000.0f;

    public static void main(String args[]) {

        /* Reading connections from source file and filter the data */
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");
        BytesFilterProcessor filter = new BytesFilterProcessor(limit);
        Fork fork = new Fork(2);
        Connector.connect(source, filter, fork);

        /* Extraction of the parameters of interest */
        FunctionProcessor connectionTime = new FunctionProcessor(new GetConnectionHourMinute());
        FunctionProcessor duration= new FunctionProcessor(new GetDuration());
        Connector.connect(fork, 0, connectionTime,0);
        Connector.connect(fork, 1, duration,0);

        /* Building a pair/coordinates object */
        PairBuilderProcessor pairBuilder = new PairBuilderProcessor();
        Connector.connect(connectionTime, 0, pairBuilder, 0);
        Connector.connect(duration, 0, pairBuilder, 1);

        /* Building a data set that will be usable by KMeans function */
        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k, refreshInterval);
        Connector.connect(pairBuilder, setBuilder);

        /* Applying KMeans function */
        KMeansSmartFunction kmf = new KMeansSmartFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);

        Connector.connect(setBuilder, fp);

        ClustersDataFormatter dataFormatter = new ClustersDataFormatter();
        Connector.connect(fp,dataFormatter);

        Pump pump = new Pump();
        ScatterPlotGenerator plotGeneratorFunction = new ScatterPlotGenerator("Duration&Hour", "Hour", "Duration", "hourDuration.png");
        FunctionProcessor printer = new FunctionProcessor(plotGeneratorFunction);

        Connector.connect(dataFormatter, pump, printer);

        while(true){
            pump.run();
        }
    }
}
