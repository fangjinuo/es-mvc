package com.jn.esmvc.service.security.searchguard;

import com.jn.esmvc.service.config.rest.DefaultRestClientBuilderCustomizer;
import com.jn.esmvc.service.security.CustomHostnameVerifier;
import com.jn.esmvc.service.security.searchguard.propertis.SearchGuardSSLProperties;
import com.jn.langx.io.resource.ClassPathResource;
import com.jn.langx.io.resource.FileResource;
import com.jn.langx.io.resource.Resources;
import com.jn.langx.util.Emptys;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;

public class SearchGuardRestClientBuilderCustomizer extends DefaultRestClientBuilderCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(SearchGuardRestClientBuilderCustomizer.class);

    private SearchGuardSSLProperties searchGuardSSLProperties;

    public void setSearchGuardSSLProperties(SearchGuardSSLProperties searchGuardSSLProperties) {
        this.searchGuardSSLProperties = searchGuardSSLProperties;
    }

    @Override
    public void customize(HttpAsyncClientBuilder builder) {
        super.customize(builder);
        if(searchGuardSSLProperties != null && searchGuardSSLProperties.isEnabled()){
            builder.setSSLHostnameVerifier(new CustomHostnameVerifier(searchGuardSSLProperties));
            builder.setSSLContext(build(searchGuardSSLProperties));
        }
    }

    static SSLContext build(SearchGuardSSLProperties searchGuardSSLProperties){
        String pemkeyFilepath = searchGuardSSLProperties.getPemKeyFilepath();
        String pemcertFilepath = searchGuardSSLProperties.getPemCertFilepath();
        String pemkeyPassword = searchGuardSSLProperties.getPemKeyPassword();
        String pemtrustedcasFilepath = searchGuardSSLProperties.getPemTrustedCAsFilepath();
        pemkeyFilepath = getContent(pemkeyFilepath);
        pemcertFilepath = getContent(pemcertFilepath);
        pemtrustedcasFilepath = getContent(pemtrustedcasFilepath);
        try {
            return SgSSLContexts.buildSSLContext(pemcertFilepath, pemkeyFilepath, pemkeyPassword, pemtrustedcasFilepath);
        } catch (Exception e) {
            logger.error("build SSLContext error:", e);
            throw new RuntimeException(e);
        }
    }

    private static String getContent(String key){
        if(Emptys.isEmpty(key)){
            return null;
        }
        if(key.startsWith("classpath:")){
            ClassPathResource classPathResource = Resources.loadClassPathResource(key);
            return classPathResource.getAbsolutePath();
        }else if(key.startsWith("file:")){
            FileResource fileResource = Resources.loadFileResource(key);
            return fileResource.getAbsolutePath();
        }else{
            return key;
        }

    }
}
