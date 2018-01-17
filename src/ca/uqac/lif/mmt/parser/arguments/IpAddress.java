package ca.uqac.lif.mmt.parser.arguments;

public class IpAddress {

    private String ipAddress;
    private IpVersion version;

    public IpAddress(String s) {
        if (s.contains(":")) {
            this.version = IpVersion.IPV6;
        } else if (s.contains(".")) {
            this.version = IpVersion.IPV4;
        } else {
            throw new IllegalArgumentException("Unknown IP address format");
        }
        this.ipAddress = s;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public IpVersion getIpVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return "parser.argumentsAddress{" +
                "ipAddress='" + ipAddress + '\'' +
                ", version=" + version +
                '}';
    }
}
