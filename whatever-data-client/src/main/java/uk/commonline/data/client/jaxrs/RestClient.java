package uk.commonline.data.client.jaxrs;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class RestClient {
	
	//final Logger logger = LoggerFactory.getLogger(getClass());
    
    private ClientConfig cc;
    private Client client;
    
    public RestClient() {
    	this.cc = new ClientConfig();
    	cc.register(new JacksonFeature());
    	this.client = ClientBuilder.newClient(cc);
    }

    //@PostConstruct
    public void init() {
        //setCredentials(clientProperties.getUsername(), clientProperties.getPassword());
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
    public void registerProvider(final Object provider) {
	cc.register(provider);
    }
    
    public void resetClient() {
	this.client = ClientBuilder.newClient(cc);
    }
    
    /**
     * Gets rest template.
     */
    public ClientConfig getClientConfig() {
        return cc;
    }

    /**
     * Creates URL based on the URI passed in.
     */
    public String createUrl(String uri) {
        StringBuilder sb = new StringBuilder();

       // sb.append(clientProperties.getUrl());
       // sb.append(clientProperties.getApiPath());
        sb.append(uri);

        //logger.debug("URL is '{}'.", sb.toString());

        return sb.toString();
    }

    /**
     * Set default credentials on HTTP client.
     */
    public void setCredentials(String userName, String password) {
       // UsernamePasswordCredentials creds =
       //         new UsernamePasswordCredentials(clientProperties.getUsername(), clientProperties.getPassword());
       // AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);

       // httpClient.getCredentialsProvider().setCredentials(authScope, creds);
    }

}