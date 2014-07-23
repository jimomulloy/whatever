package uk.commonline.weather.model;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Provider
public class WeatherReaderInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
	// final InputStream originalInputStream = context.getInputStream();
	// context.setInputStream(new GZIPInputStream(originalInputStream));
	return context.proceed();
    }
}