package acdsee.io.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ProxyConnection {

    private URLConnection connection;
    private ProxySettings proxySettings;

    public ProxyConnection(URLConnection connection) {
        super();
        this.connection = connection;
    }

    public ProxyConnection(URLConnection connection, ProxySettings proxySettings) {
        this(connection);
        this.proxySettings = proxySettings;
    }

    public ProxySettings getProxySettings() {
        return proxySettings;
    }

    public InputStream getInputStream() throws IOException {
        if (getProxySettings() != null && getProxySettings().isProxyEnabled()) {
            setupProxy();
        }
        return connection.getInputStream();
    }

    private void setupAuthorization() {
        if (connection != null) {
            String encodedCredentials = java.util.Base64.getEncoder().encodeToString((getProxySettings().getProxyUsername() + ":" + getProxySettings().getProxyPassword()).getBytes());
            connection.setRequestProperty("Proxy-Authorization", "Basic " + encodedCredentials);
        }
    }

    private void setupProxy() {
        System.setProperty("proxySet", "true");
        System.setProperty("proxyPort", getProxySettings().getProxyPort());
        System.setProperty("proxyHost", getProxySettings().getProxyHost());

        if (getProxySettings().getProxyUsername() != null
                && getProxySettings().getProxyPassword() != null
                && getProxySettings().isProxyAuthenticationEnabled()) {
            setupAuthorization();
        }
    }
}
