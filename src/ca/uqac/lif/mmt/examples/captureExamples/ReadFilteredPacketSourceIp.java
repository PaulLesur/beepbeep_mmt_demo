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

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.Ip4PacketFilter;
import netp.PacketSource;
import netp.PayloadFilter;
import netp.functions.GetSourceIp;

/**
 * Displays the source IP only from UDP packets containing byte 24 and 99
 * This example uses PacketFilter and PayloadFilter classes
 */
public class ReadFilteredPacketSourceIp {
	
	public static void main(String[] args) throws ConnectorException {

		// outputs packets from a pcap file
		PacketSource source = new PacketSource("test.pcap");

		// only keep UDP packets
		Ip4PacketFilter packetFilter = new Ip4PacketFilter();
		packetFilter.setProtocol(Ip4PacketFilter.UDP);
		Connector.connect(source, packetFilter);

		// only keep packets containing bytesClustering 24 99 in payload
		PayloadFilter payloadFilter = new PayloadFilter();
		payloadFilter.setFilter("2499");
		Connector.connect(packetFilter, payloadFilter);

		// extract source IP address of packet
		FunctionProcessor sourceIp = new FunctionProcessor(new GetSourceIp());
		Connector.connect(payloadFilter, sourceIp);

		// retrieve results
		QueueSink sink = new QueueSink(1);
		Connector.connect(sourceIp, sink);

		// compute the first 15 packets
		for (int i = 0; i < 15; i++) {
			source.push();
			String output = (String) sink.remove()[0];
			if (output != null) // only display the packets that went through
								// the filters
				System.out.println(i + ": " + output);
		}
	}

}
