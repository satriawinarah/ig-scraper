package id.co.scrapper.instagram.config;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Configuration
public class RestTemplateConfiguration {
	
	@Bean
	public RestTemplate restTemplateConfig() {
		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        SSLContext sslContext;
        try {
            final TrustStrategy acceptingTrustStrategy = (final X509Certificate[] chain, final String authType) -> true;
            sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, null, null,
                    new NoopHostnameVerifier());
            final CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            requestFactory.setHttpClient(httpClient);
        } catch (final Exception e) {
            //LOG.error("Error during RestTemplate initialization", e);
        }
        return new RestTemplate(requestFactory);
	}
	
	@Bean
	public OkHttpClient okHttp() {
		OkHttpClient httpClient = new OkHttpClient();
		return httpClient;
	}

}
