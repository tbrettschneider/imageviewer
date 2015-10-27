package acdsee.io.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import com.Ostermiller.util.Base64;

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
            connection.setRequestProperty("Proxy-Authorization", "Basic "
                    + Base64.encode(getProxySettings().getProxyUsername() + ":" + getProxySettings().getProxyPassword()));
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
