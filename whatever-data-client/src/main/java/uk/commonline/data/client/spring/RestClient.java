package uk.commonline.data.client.spring;

import javax.annotation.PostConstruct;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import uk.commonline.data.client.RestClientProperties;

public class RestClient {

    //final Logger logger = LoggerFactory.getLogger(getClass());

    private final RestTemplate template;
    private final RestClientProperties clientProperties;
    private final DefaultHttpClient httpClient;
    private final HttpClientBuilder httpClientBuilder;

    @Autowired
    public RestClient(RestTemplate template, RestClientProperties clientProperties,
                      DefaultHttpClient httpClient) {
        this.template = template;
        this.clientProperties = clientProperties;
        this.httpClient = httpClient;
        this.httpClientBuilder = null;
    }

    @PostConstruct
    public void init() {
        setCredentials(clientProperties.getUsername(), clientProperties.getPassword());
    }

    /**
     * Gets rest template.
     */
    public RestTemplate getRestTemplate() {
        return template;
    }

    /**
     * Creates URL based on the URI passed in.
     */
    public String createUrl(String uri) {
        StringBuilder sb = new StringBuilder();

        sb.append(clientProperties.getUrl());
        sb.append(clientProperties.getApiPath());
        sb.append(uri);

        //logger.debug("URL is '{}'.", sb.toString());

        return sb.toString();
    }

    /**
     * Set default credentials on HTTP client.
     */
    public void setCredentials(String userName, String password) {
        UsernamePasswordCredentials creds =
                new UsernamePasswordCredentials(clientProperties.getUsername(), clientProperties.getPassword());
        AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);

        httpClient.getCredentialsProvider().setCredentials(authScope, creds);
    }

}