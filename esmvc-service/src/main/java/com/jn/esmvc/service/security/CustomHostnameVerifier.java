package com.jn.esmvc.service.security;

import com.jn.esmvc.service.security.searchguard.propertis.SearchGuardSSLProperties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class CustomHostnameVerifier implements HostnameVerifier {
        private final SearchGuardSSLProperties searchGuardSSLProperties;

        public CustomHostnameVerifier(SearchGuardSSLProperties searchGuardSSLProperties) {
            this.searchGuardSSLProperties = searchGuardSSLProperties;
        }

        @Override
        public boolean verify(String hostNameOrIp, SSLSession sslSession) {
            return true;
            /* List<String> sslHostnameVerifiers = searchGuardSSLProperties.getSSLHostnameVerifiers();
            if(Emptys.isEmpty(sslHostnameVerifiers)){
                return false;
            }
            return sslHostnameVerifiers.contains(hostNameOrIp);*/
        }
    }