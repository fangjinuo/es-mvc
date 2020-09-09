package com.jn.esmvc.service.config.rest;

import java.util.List;

public class HttpsClientProperties {

    private String pemcertFilepath;
    private String pemkeyFilepath;
    private String pemkeyPassword;
    private String pemtrustedcasFilepath;
    private List<String> SSLHostnameVerifiers;

    public String getPemcertFilepath() {
        return pemcertFilepath;
    }

    public void setPemcertFilepath(String pemcertFilepath) {
        this.pemcertFilepath = pemcertFilepath;
    }

    public String getPemkeyFilepath() {
        return pemkeyFilepath;
    }

    public void setPemkeyFilepath(String pemkeyFilepath) {
        this.pemkeyFilepath = pemkeyFilepath;
    }

    public String getPemkeyPassword() {
        return pemkeyPassword;
    }

    public void setPemkeyPassword(String pemkeyPassword) {
        this.pemkeyPassword = pemkeyPassword;
    }

    public String getPemtrustedcasFilepath() {
        return pemtrustedcasFilepath;
    }

    public void setPemtrustedcasFilepath(String pemtrustedcasFilepath) {
        this.pemtrustedcasFilepath = pemtrustedcasFilepath;
    }

    public List<String> getSSLHostnameVerifiers() {
        return SSLHostnameVerifiers;
    }

    public void setSSLHostnameVerifiers(List<String> SSLHostnameVerifiers) {
        this.SSLHostnameVerifiers = SSLHostnameVerifiers;
    }
}
