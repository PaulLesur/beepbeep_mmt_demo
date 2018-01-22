package ca.uqac.lif.mmt.processors;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.GroupProcessor;
import ca.uqac.lif.cep.io.LineReader;

import java.io.File;
import java.io.FileNotFoundException;



public class FileSourceProcessor extends GroupProcessor{


    /**
     * The default constructor listen on every network interfaces and feed the output with received packages.
     */
//    public SourceProcessor(){
//        super(0,1);
//        try {
//            // Processor that catches network packages
//            NetworkInterfaceSource source = new NetworkInterfaceSource("any");
//
//
//
//
//        } catch (Exception e) {
//            System.err.print("Error while opening network interfaces !");
//            e.printStackTrace();
//        }
//    }



    /**
     * Constructor for a file based source.
     * @param filePath The file where are located the packages traces.
     */
    public FileSourceProcessor(String filePath) {
        super(0,1);

        LineReader source = null;
        try {
            source = new LineReader(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.print("File not found : " + filePath);
            e.printStackTrace();
        }
        ConnectionBuilder cc = new ConnectionBuilder();

        Connector.connect(source, cc);

        associateOutput(0, cc, 0);

    }
}
