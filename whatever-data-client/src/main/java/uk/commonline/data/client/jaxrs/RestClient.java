package uk.commonline.data.client.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class RestClient {

    public String getUrl() {
        if(url == null || url.isEmpty()){
            return "http://localhost:8080";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private ClientConfig cc;
    private Client client;
    private String url;

    public RestClient() {
        this.cc = new ClientConfig();
        cc.register(new JacksonFeature());
        this.client = ClientBuilder.newClient(cc);
    }

    /**
     * Creates URL based on the URI passed in.
     */
    public String createUrl(String uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(getUrl());
        sb.append("/");
        sb.append(uri);

        return sb.toString();
    }

    /**
     * Gets rest template.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Gets rest template.
     */
    public ClientConfig getClientConfig() {
        return cc;
    }

    // @PostConstruct
    public void init() {
        // setCredentials(clientProperties.getUsername(),
        // clientProperties.getPassword());
    }

    /**
     * Gets rest template.
     */
    public void registerProvider(final Object provider) {
        cc.register(provider);
    }

    public void resetClient() {
        this.client = ClientBuilder.newClient(cc);
    }

    /**
     * Set default credentials on HTTP client.
     */
    public void setCredentials(String userName, String password) {
        // UsernamePasswordCredentials creds =
        // new UsernamePasswordCredentials(clientProperties.getUsername(),
        // clientProperties.getPassword());
        // AuthScope authScope = new AuthScope(AuthScope.ANY_HOST,
        // AuthScope.ANY_PORT, AuthScope.ANY_REALM);

        // httpClient.getCredentialsProvider().setCredentials(authScope, creds);
    }

}