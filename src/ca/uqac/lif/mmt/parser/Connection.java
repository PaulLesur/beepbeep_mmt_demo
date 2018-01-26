package ca.uqac.lif.mmt.parser;

import ca.uqac.lif.mmt.parser.arguments.Flag;
import ca.uqac.lif.mmt.parser.arguments.IpAddress;
import ca.uqac.lif.mmt.parser.arguments.Rate;

public class Connection {

    private float   connectionDuration;
    private String service;
    private float   sourceBytes;
    private float   destinationBytes;
    private Float   countSameDestinationSameSource;
    private Rate sameServiceRate;
    private Rate synErrorRateCSDSS;
    private Rate  serviceSynErrorRate;
    private float   countSameDestination;
    private float   countSameDestinationSameService;
    private Rate  sameSourcePortRate;
    private Rate synErrorRateCSD;
    private Rate  synErrorRateServiceCSD;
    private Flag connectionStatus;
    private String intrusionType;
    private String malwareType;
    private float shellcodeType;
    private float attackIndicator;
    private IpAddress sourceIpAddress;
    private int sourcePortNumber;
    private IpAddress destinationIpAddress;
    private int destinationPortNumber;
    private String startTime;
    private String protocol;

    public Connection(){

    }

    public Connection(float connectionDuration, String service, float sourceBytes, float destinationBytes, Float countSameDestinationSameSource, Rate sameServiceRate, Rate synErrorRateCSDSS, Rate serviceSynErrorRate, float countSameDestination, float countSameDestinationSameService, Rate sameSourcePortRate, Rate synErrorRateCSD, Rate synErrorRateServiceCSD, Flag connectionStatus, String intrusionType, String malwareType, float shellcodeType, float attackIndicator, IpAddress sourceIpAddress, int sourcePortNumber, IpAddress destinationIpAddress, int destinationPortNumber, String startTime, String protocol) {
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

    public float getSourceBytes() {
        return sourceBytes;
    }

    public float getDestinationBytes() {
        return destinationBytes;
    }

    public Float getCountSameDestinationSameSource() {
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

    public float getCountSameDestination() {
        return countSameDestination;
    }

    public float getCountSameDestinationSameService() {
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

    public String getIntrusionType() {
        return intrusionType;
    }

    public String getMalwareType() {
        return malwareType;
    }

    public float getShellcodeType() {
        return shellcodeType;
    }

    public float getAttackIndicator() {
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

    public int getHour(){
        String hour = this.getStartTime().split(":")[0];
        return Integer.parseInt(hour);
    }
    public double getMinute(){
        String minute = this.getStartTime().split(":")[1];
        return Double.parseDouble(minute);
    }
}
