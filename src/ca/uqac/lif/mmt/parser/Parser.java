package ca.uqac.lif.mmt.parser;

import ca.uqac.lif.mmt.parser.arguments.Flag;
import ca.uqac.lif.mmt.parser.arguments.IpAddress;
import ca.uqac.lif.mmt.parser.arguments.Rate;

public class Parser {

    public Parser(){}

    public static Connection parseConnection(String line){
        String[] splittedLine = line.split("\t");

        try {

            return (
                    new Connection(
                            Float.parseFloat(splittedLine[0]),
                            splittedLine[1],
                            Integer.parseInt(splittedLine[2]),
                            Integer.parseInt(splittedLine[3]),
                            Integer.parseInt(splittedLine[4]),
                            new Rate(splittedLine[5]),
                            new Rate(splittedLine[6]),
                            new Rate(splittedLine[7]),
                            Integer.parseInt(splittedLine[8]),
                            Integer.parseInt(splittedLine[9]),
                            new Rate(splittedLine[10]),
                            new Rate(splittedLine[11]),
                            new Rate(splittedLine[12]),
                            new Flag(splittedLine[13]),
                            Integer.parseInt(splittedLine[14]),
                            Integer.parseInt(splittedLine[15]),
                            Integer.parseInt(splittedLine[16]),
                            Integer.parseInt(splittedLine[17]),
                            new IpAddress(splittedLine[18]),
                            Integer.parseInt(splittedLine[19]),
                            new IpAddress(splittedLine[20]),
                            Integer.parseInt(splittedLine[21]),
                            new String(splittedLine[22]),
                            splittedLine[23]
                    )
            );
        }
        catch(NumberFormatException nfe){
            System.err.println("NumberFormatException");
            return new Connection();
        }
    }

}
