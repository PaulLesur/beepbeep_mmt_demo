package ca.uqac.lif.mmt.parser;

import ca.uqac.lif.mmt.parser.arguments.Flag;
import ca.uqac.lif.mmt.parser.arguments.IpAddress;
import ca.uqac.lif.mmt.parser.arguments.Rate;

public class Connection {

    private float   connectionDuration;
    private String service;
    private int   sourceBytes;
    private int   destinationBytes;
    private int   countSameDestinationSameSource;
    private Rate sameServiceRate;
    private Rate synErrorRateCSDSS;
    private Rate  serviceSynErrorRate;
    private int   countSameDestination;
    private int   countSameDestinationSameService;
    private Rate  sameSourcePortRate;
    private Rate synErrorRateCSD;
    private Rate  synErrorRateServiceCSD;
    private Flag connectionStatus;
    private int intrusionType;
    private int malwareType;
    private int shellcodeType;
    private int attackIndicator;
    private IpAddress sourceIpAddress;
    private int sourcePortNumber;
    private IpAddress destinationIpAddress;
    private int destinationPortNumber;
    private String startTime;
    private String protocol;

    public Connection(){

    }

    public Connection(float connectionDuration, String service, int sourceBytes, int destinationBytes, int countSameDestinationSameSource, Rate sameServiceRate, Rate synErrorRateCSDSS, Rate serviceSynErrorRate, int countSameDestination, int countSameDestinationSameService, Rate sameSourcePortRate, Rate synErrorRateCSD, Rate synErrorRateServiceCSD, Flag connectionStatus, int intrusionType, int malwareType, int shellcodeType, int attackIndicator, IpAddress sourceIpAddress, int sourcePortNumber, IpAddress destinationIpAddress, int destinationPortNumber, String startTime, String protocol) {
        this.connectionDuration = connectionDuration;
        this.service = service;
        this.sourceBytes = sourceBytes;
        this.destinationBytes = destinationBytes;
        this.countSameDestinationSameSource = countSameDestinationSameSource;
        this.sameServiceRate = sameServiceRate;
        this.synErrorRateCSDSS = synErrorRateCSDSS;
        this.serviceSynErrorRate = serviceSynErrorRate;
        this.countSameDestination = countSameDestination;
        this.countSameDestinationSameService = countSameDestinationSameService;
        this.sameSourcePortRate = sameSourcePortRate;
        this.synErrorRateCSD = synErrorRateCSD;
        this.synErrorRateServiceCSD = synErrorRateServiceCSD;
        this.connectionStatus = connectionStatus;
        this.intrusionType = intrusionType;
        this.malwareType = malwareType;
        this.shellcodeType = shellcodeType;
        this.attackIndicator = attackIndicator;
        this.sourceIpAddress = sourceIpAddress;
        this.sourcePortNumber = sourcePortNumber;
        this.destinationIpAddress = destinationIpAddress;
        this.destinationPortNumber = destinationPortNumber;
        this.startTime = startTime;
        this.protocol = protocol;
    }

    public float getConnectionDuration() {
        return connectionDuration;
    }

    public String getService() {
        return service;
    }

    public int getSourceBytes() {
        return sourceBytes;
    }

    public int getDestinationBytes() {
        return destinationBytes;
    }

    public int getCountSameDestinationSameSource() {
        return countSameDestinationSameSource;
    }

    public Rate getSameServiceRate() {
        return sameServiceRate;
    }

    public Rate getSynErrorRateCSDSS() {
        return synErrorRateCSDSS;
    }

    public Rate getServiceSynErrorRate() {
        return serviceSynErrorRate;
    }

    public int getCountSameDestination() {
        return countSameDestination;
    }

    public int getCountSameDestinationSameService() {
        return countSameDestinationSameService;
    }

    public Rate getSameSourcePortRate() {
        return sameSourcePortRate;
    }

    public Rate getSynErrorRateCSD() {
        return synErrorRateCSD;
    }

    public Rate getSynErrorRateServiceCSD() {
        return synErrorRateServiceCSD;
    }

    public Flag getConnectionStatus() {
        return connectionStatus;
    }

    public int getIntrusionType() {
        return intrusionType;
    }

    public int getMalwareType() {
        return malwareType;
    }

    public int getShellcodeType() {
        return shellcodeType;
    }

    public int getAttackIndicator() {
        return attackIndicator;
    }

    public IpAddress getSourceIpAddress() {
        return sourceIpAddress;
    }

    public int getSourcePortNumber() {
        return sourcePortNumber;
    }

    public IpAddress getDestinationIpAddress() {
        return destinationIpAddress;
    }

    public int getDestinationPortNumber() {
        return destinationPortNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getProtocol() {
        return protocol;
    }
}
