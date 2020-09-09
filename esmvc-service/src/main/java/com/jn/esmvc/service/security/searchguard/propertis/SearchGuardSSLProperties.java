package com.jn.esmvc.service.security.searchguard.propertis;

import java.util.List;

public class SearchGuardSSLProperties {
    private boolean enable;
    private String pemcertFilepath;
    private String pemkeyFilepath;
    private String pemkeyPassword;
    private String pemtrustedcasFilepath;

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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
