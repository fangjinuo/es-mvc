package com.jn.esmvc.service.config.rest;

import com.jn.esmvc.service.security.CustomHostnameVerifier;
import com.jn.esmvc.service.util.CustomSSLContext;
import com.jn.langx.io.resource.ClassPathResource;
import com.jn.langx.io.resource.FileResource;
import com.jn.langx.io.resource.Resources;
import com.jn.langx.util.Emptys;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

import javax.net.ssl.SSLContext;
import java.util.Map;


public class DefaultRestClientBuilderCustomizer implements RestClientBuilderCustomizer {

    private EsmvcRestClientProperties esmvcRestClientProperties;

    public DefaultRestClientBuilderCustomizer(){}

    public DefaultRestClientBuilderCustomizer(EsmvcRestClientProperties restClientProperties) {
        this.esmvcRestClientProperties = restClientProperties;
    }

    @Override
    public void customize(RestClientBuilder builder) {

    }

    @Override
    public void customize(HttpAsyncClientBuilder builder) {
        String username = esmvcRestClientProperties.getUsername();
        if (Emptys.isNotEmpty(username)) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            String password = esmvcRestClientProperties.getPassword();
            Credentials credentials = new UsernamePasswordCredentials(username, password);
            credentialsProvider.setCredentials(AuthScope.ANY, credentials);
            builder.setDefaultCredentialsProvider(credentialsProvider);
        }
        HttpsClientProperties httpsClientProperties = esmvcRestClientProperties.getHttpsClientProperties();
        if(httpsClientProperties != null){
            builder.setSSLHostnameVerifier(new CustomHostnameVerifier(httpsClientProperties));
            builder.setSSLContext(build(httpsClientProperties));
        }
    }

    static SSLContext build(HttpsClientProperties httpsClientProperties){
        String pemkeyFilepath = httpsClientProperties.getPemkeyFilepath();
        String pemcertFilepath = httpsClientProperties.getPemcertFilepath();
        String pemkeyPassword = httpsClientProperties.getPemkeyPassword();
        String pemtrustedcasFilepath = httpsClientProperties.getPemtrustedcasFilepath();
        pemkeyFilepath = getContent(pemkeyFilepath);
        pemcertFilepath = getContent(pemcertFilepath);
        pemtrustedcasFilepath = getContent(pemtrustedcasFilepath);
        try {
           return CustomSSLContext.buildSSLContext(pemcertFilepath, pemkeyFilepath, pemkeyPassword, pemtrustedcasFilepath);
        } catch (Exception e) {
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

    @Override
    public void customize(RequestConfig.Builder builder) {
        builder.setConnectTimeout(esmvcRestClientProperties.getConnectTimeout());
        builder.setSocketTimeout(esmvcRestClientProperties.getReadTimeout());
    }

    public void setEsmvcRestClientProperties(EsmvcRestClientProperties esmvcRestClientProperties) {
        this.esmvcRestClientProperties = esmvcRestClientProperties;
    }
}
