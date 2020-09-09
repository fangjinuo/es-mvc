package com.jn.esmvc.service.security.searchguard.propertis;

public class SearchGuardSSLProperties {
    private boolean enabled;
    private String pemCertFilepath;
    private String pemKeyFilepath;
    private String pemKeyPassword;
    private String pemTrustedCAsFilepath;

    public String getPemCertFilepath() {
        return pemCertFilepath;
    }

    public void setPemCertFilepath(String pemCertFilepath) {
        this.pemCertFilepath = pemCertFilepath;
    }

    public String getPemKeyFilepath() {
        return pemKeyFilepath;
    }

    public void setPemKeyFilepath(String pemKeyFilepath) {
        this.pemKeyFilepath = pemKeyFilepath;
    }

    public String getPemKeyPassword() {
        return pemKeyPassword;
    }

    public void setPemKeyPassword(String pemKeyPassword) {
        this.pemKeyPassword = pemKeyPassword;
    }

    public String getPemTrustedCAsFilepath() {
        return pemTrustedCAsFilepath;
    }

    public void setPemTrustedCAsFilepath(String pemTrustedCAsFilepath) {
        this.pemTrustedCAsFilepath = pemTrustedCAsFilepath;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
