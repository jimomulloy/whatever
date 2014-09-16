package uk.commonline.weather.model;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider
public class WeatherWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext ctx) throws IOException, WebApplicationException {
        // GZIPOutputStream os = new GZIPOutputStream(ctx.getOutputStream());
        // try {
        // ctx.setOutputStream(os);
        ctx.proceed();
        // } finally {
        // os.finish();
        // }
    }
}