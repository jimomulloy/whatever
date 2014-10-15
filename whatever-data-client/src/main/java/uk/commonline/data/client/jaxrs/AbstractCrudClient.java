package uk.commonline.data.client.jaxrs;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

/**
 */
public abstract class AbstractCrudClient<T extends EI> extends AbstractDataClient implements Dao<T> {

    Class<T> eiClass;

    protected RestClient restClient;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public T create(T t) {
        T o = getRestClient().getClient().target(getRestClient().createUrl(getPath())).request()
                .put(Entity.entity(t, MediaType.APPLICATION_JSON), eiClass);
        return o;
    }

    @Override
    public void delete(T t) {
        deleteById(t.getId());
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(Long id) {
        getRestClient().getClient().target(getRestClient().createUrl(getPath())).path("/{id}").resolveTemplate("id", id).request().delete();
    }

    @Override
    public boolean exists(Long id) {
        T o = get(id);
        return o == null ? false : true;
    }

    @Override
    public T get(Long id) {
        T o = getRestClient().getClient().target(getRestClient().createUrl(getPath())).path("/{id}").resolveTemplate("id", id)
                .request(MediaType.APPLICATION_JSON).get(eiClass);
        if (o == null) {
            o = newCiInstance();
        }
        return o;
    }

    @Override
    public List<T> getAll() {

        GenericType<List<T>> list = new GenericType<List<T>>() {
        };

        List<T> entities = getRestClient().getClient().target(getRestClient().createUrl(getPath())).request(MediaType.APPLICATION_JSON).get(list);
        return entities;
    }

    public Class<T> getEiClass() {
        return eiClass;
    };

    abstract protected String getPath();

    public RestClient getRestClient() {
        return restClient;
    };

    @Override
    public T load(Long id) {
        return null;
    };

    protected T newCiInstance() {
        try {
            return eiClass.newInstance();
        } catch (InstantiationException e) {
            // Shouldn't happen
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            // Shouldn't happen
            throw new RuntimeException(e);
        }
    };

    @SuppressWarnings("unchecked")
    public void setRestClient(RestClient restClient) {
        ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
        this.eiClass = (Class<T>) paramType.getActualTypeArguments()[0];
        this.restClient = restClient;
    };

    @Override
    public T update(T t) {
        WebTarget target = getRestClient().getClient().target(getRestClient().createUrl(getPath()));
        T o = target.request().post(Entity.entity(t, MediaType.APPLICATION_JSON), eiClass);
        return o;
    }
}
