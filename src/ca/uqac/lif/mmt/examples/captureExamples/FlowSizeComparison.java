/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2016 Sylvain Hallé

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

package ca.uqac.lif.mmt.examples.captureExamples;

import org.jnetpcap.packet.JFlow;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.functions.UnaryFunction;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.FlowTransmitter;
import netp.PacketSource;

/**
 * Return true if a flow has two packets or more
 * In this example we create a new FlowFunction
 */
public class FlowSizeComparison {
	
	public static void main(String[] args) throws ConnectorException {
		
		/**
		 * FlowFunction to know if a packet has two (or more) packets, or not
		 */
		class HasTwoPacketsOrMore extends UnaryFunction<JFlow, Boolean> {
			
			public HasTwoPacketsOrMore() {
				super(JFlow.class, Boolean.class);
			}

			/**
			 * @param flow The flow we want to compare the size
			 */
			@Override
			public Boolean getValue(JFlow flow) {
				Integer size = flow.size();
				if (size >= 2) {
					return true;
				}
				return false;
			}
		}
		
		PacketSource source = new PacketSource("test.pcap");

		FlowTransmitter flow = new FlowTransmitter();
		try {
			Connector.connect(source, flow);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}
		
		FunctionProcessor hasFiveOrMore = new FunctionProcessor(new HasTwoPacketsOrMore());
		try {
			Connector.connect(flow, hasFiveOrMore);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(hasFiveOrMore, sink);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}
		
		// compute the first 15 packets
		for (int i = 0; i < 15; i++) {
			source.push();
			Boolean output = (Boolean) sink.remove()[0];
			System.out.println(i + ": " + output);
		}
	}

}
