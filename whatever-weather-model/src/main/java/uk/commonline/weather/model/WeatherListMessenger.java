package uk.commonline.weather.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherListMessenger implements MessageBodyWriter<List<Weather>>, MessageBodyReader<List<Weather>> {

    private static final String UTF_8 = "UTF-8";

    JsonSerializer<Date> ser = new JsonSerializer<Date>() {
	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
	    return src == null ? null : new JsonPrimitive(src.getTime());
	}
    };

    JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	    return json == null ? null : new Date(json.getAsLong());
	}
    };

    Gson gson = new GsonBuilder().registerTypeAdapter(java.sql.Date.class, ser).registerTypeAdapter(Date.class, ser).registerTypeAdapter(java.sql.Date.class, deser).registerTypeAdapter(Date.class, deser).create();

    private Gson getGson() {
	if (gson == null) {
	    // final GsonBuilder gsonBuilder = new GsonBuilder();
	    gson = new GsonBuilder().registerTypeAdapter(Date.class, ser).registerTypeAdapter(Date.class, deser).create();
	    // gson = gsonBuilder.create();
	}
	return gson;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	return true;
    }

    @Override
    public long getSize(List<Weather> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	return -1;
    }

    @Override
    public void writeTo(List<Weather> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
	    MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
	OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF_8);
	try {
	    Type jsonType;
	    if (type.equals(genericType)) {
		jsonType = type;
	    } else {
		jsonType = genericType;
	    }
	    for (Weather w : t) {
		w.clearBackReferences();
	    }
	    System.out.println("!!writeTo:"+t);
	    getGson().toJson(t, jsonType, writer);
	    
	} finally {
	    writer.close();
	}

    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
	return true;
    }

    @Override
    public List<Weather> readFrom(Class<List<Weather>> type, Type genericType, Annotation[] annotations, MediaType mediaType,
	    MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
	InputStreamReader streamReader = new InputStreamReader(entityStream, UTF_8);
	try {
	    Type jsonType;
	    if (type.equals(genericType)) {
		jsonType = type;
	    } else {
		jsonType = genericType;
	    }
	    List<Weather> weather = getGson().fromJson(streamReader, jsonType);
	    for (Weather w : weather) {
		w.setBackReferences();
	    }
	    return weather;
	} catch (Exception ex) {
	    System.out.println("!!readFrom"); 
	    ex.printStackTrace();
	    return null;
	} finally {
	    streamReader.close();
	}
    }
}
