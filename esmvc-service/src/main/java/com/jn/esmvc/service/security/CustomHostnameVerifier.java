package com.jn.esmvc.service.security;

import com.jn.esmvc.service.config.rest.HttpsClientProperties;
import com.jn.langx.util.Emptys;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.List;

public class CustomHostnameVerifier implements HostnameVerifier {
        private final HttpsClientProperties httpsClientProperties;

        public CustomHostnameVerifier(HttpsClientProperties httpsClientProperties) {
            this.httpsClientProperties = httpsClientProperties;
        }

        @Override
        public boolean verify(String hostNameOrIp, SSLSession sslSession) {
            List<String> sslHostnameVerifiers = httpsClientProperties.getSSLHostnameVerifiers();
            if(Emptys.isEmpty(sslHostnameVerifiers)){
                return false;
            }
            return sslHostnameVerifiers.contains(hostNameOrIp);
        }
    }