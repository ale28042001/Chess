public class ClientConfig {
    private String serverHost = "192.168.10.11";
    private int serverPort = 8082;

    public ClientConfig() {
    }

    public ClientConfig(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }
}