package com.tradevan.ptrs.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

public class TrustSelfSignedCertHttpClientFactory implements
		FactoryBean<HttpClient> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${trust.store.file}") 
	private String trustStoreFile;
	
	@Value("${trust.store.pass}") 
	private String trustStorePass;
	
	@Value("${key.store.file}") 
	private String keyStoreFile;
	
	@Value("${key.store.pass}") 
	private String keyStorePass;
	
	@Value("${key.pass}") 
	private String keyPass;
	
	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public Class<?> getObjectType() {
		return HttpClient.class;
	}

	@Override
	public HttpClient getObject() throws Exception {
		
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File(trustStoreFile));
        try {
            trustStore.load(instream, trustStorePass.toCharArray());
        } finally {
            instream.close();
        }
        
        
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instreamKey = new FileInputStream(new File(keyStoreFile));
        try {
            keyStore.load(instreamKey, keyStorePass.toCharArray());
        } finally {
        	instreamKey.close();
        }

		// provide SSLContext that allows self-signed certificate
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore,
				new TrustSelfSignedStrategy()).loadKeyMaterial(keyStore, keyPass.toCharArray()).build();

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
				sslContext, new String[]{"TLSv1.2"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

		// based on HttpClients.createSystem()
		return HttpClientBuilder.create().useSystemProperties()
				.setSSLSocketFactory(sslConnectionSocketFactory) // add custom																	
				.build();
	}
}
