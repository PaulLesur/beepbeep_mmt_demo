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
package ca.uqac.lif.mmt.examples.duration;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Pullable;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.peg.ml.KMeansFunction;
import ca.uqac.lif.cep.tmf.Pump;
import ca.uqac.lif.mmt.functions.GetDuration;
import ca.uqac.lif.mmt.functions.KMeansSmartFunction;
import ca.uqac.lif.mmt.processors.ClustersDataFormatter;
import ca.uqac.lif.mmt.processors.FileSourceProcessor;
import ca.uqac.lif.mmt.processors.ScatterPlotGenerator;
import ca.uqac.lif.mmt.processors.SetBuilderProcessor;


import java.io.FileNotFoundException;

/**
 * An example of BeepBeep's usage in data mining: an application of K-Means algorithm on the duration of each connection event in a log file.
 * <p>
 *  The duration of each connection goes through the chain of processors;
 *  a PNG image is then generated to display the resulting distribution on a graph.
 * </p>
 * <p>
 *  This example illustrates the use of {@link KMeansSmartFunction} and {@link ScatterPlotGenerator} objects.
 * </p>
 * <p>
 *  Represented graphically, this example corresponds to the following chain of processors:
 * </p>
 * <p>
 *  <img src="{@docRoot}/img/MMT-DurationExample.png" alt="Processor graph">
 * </p>
 */

public class DurationExample {

    private static int k = 5;
    private static int refreshInterval = 100;

    public static void main(String args[]) throws FileNotFoundException {

        /* Extracting data from source file */
        FileSourceProcessor source = new FileSourceProcessor("./data/2006/11/20061101.txt");

        /* Retrieving duration for each Connection object */
        FunctionProcessor durationExtractor = new FunctionProcessor(new GetDuration());

        /* Building set, needed for K-means algorithm */
        SetBuilderProcessor setBuilder = new SetBuilderProcessor(k, refreshInterval);

        /* Creating K-means function processor */
        KMeansFunction kmf = new KMeansSmartFunction(k);
        FunctionProcessor fp = new FunctionProcessor(kmf);


        /* Setting up a printer to print data */
        ClustersDataFormatter dataFormatter = new ClustersDataFormatter();
        Pump pump = new Pump();
        ScatterPlotGenerator plotGenerator = new ScatterPlotGenerator("Durations", "Duration of the connection", "", "duration.png");
        FunctionProcessor printer = new FunctionProcessor(plotGenerator);


        /* Building the pipeline */
        Connector.connect(source, durationExtractor, setBuilder, fp, dataFormatter, pump, printer);


        while (true){
            pump.run();
        }
    }
}
