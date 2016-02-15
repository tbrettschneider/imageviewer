package com.tommybrettschneider.imageviewer.io.http;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProxySettings implements Serializable {

    private static ProxySettings proxySettings;

    public static final ProxySettings getInstance() {
        if (proxySettings == null) {
            proxySettings = new ProxySettings();
        }
        return proxySettings;
    }

    private boolean proxyEnabled;
    private String proxyHost;
    private String proxyPort;

    private boolean proxyAuthenticationEnabled;
    private String proxyUsername;
    private String proxyPassword;

    private List<String> nonProxyHostnames;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public boolean isProxyEnabled() {
        return proxyEnabled;
    }

    public void setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
    }

    public boolean isProxyAuthenticationEnabled() {
        return proxyAuthenticationEnabled;
    }

    public void setProxyAuthenticationEnabled(boolean proxyAuthenticationEnabled) {
        this.proxyAuthenticationEnabled = proxyAuthenticationEnabled;
    }

    public List<String> getNonProxyHostnames() {
        return nonProxyHostnames;
    }

    public void setNonProxyHostnames(List<String> nonProxyHostnames) {
        this.nonProxyHostnames = nonProxyHostnames;
    }

    public ProxySettings(String proxyUsername, String proxyPassword,
            String proxyHost, String proxyPort) {
        this();
        this.proxyUsername = proxyUsername;
        this.proxyPassword = proxyPassword;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyEnabled = true;
        this.proxyAuthenticationEnabled = true;
    }

    public ProxySettings() {
        super();
        nonProxyHostnames = new ArrayList<>();
    }
}