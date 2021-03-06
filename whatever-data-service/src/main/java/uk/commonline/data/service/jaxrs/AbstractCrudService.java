package uk.commonline.data.service.jaxrs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

/**
 */
public abstract class AbstractCrudService<T extends EI> /*
                                                         * extends
                                                         * AbstractDataService
                                                         * implements Dao<T>
                                                         */{

    Class<T> eiClass;

    @SuppressWarnings("unchecked")
    public AbstractCrudService() {
        // ParameterizedType paramType = (ParameterizedType) getClass()
        // .getGenericSuperclass();
        // this.eiClass = (Class<T>) paramType.getActualTypeArguments()[0];
    }

    public long count() {
        return 0;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public T create(T t) {
        T o = getService().create(t);
        if (o == null) {
            o = newCiInstance();
        }
        return o;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(T t) {
        getService().delete(t);
        deleteById(t.getId());
    }

    public void deleteAll() {

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteById(@PathParam("id") Long id) {
        T o = getService().get(id);
        if (o == null) {
            return;
        }
        getService().delete(o);
    }

    public boolean exists(Long id) {
        T o = getService().get(id);
        return o == null ? false : true;
    }

    @GET
    @Path("{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public T get(@PathParam("id") Long id) {
        T o = getService().get(id);
        if (o == null) {
            o = newCiInstance();
        }
        // GenericEntity<T> entity = new GenericEntity<T>(o, getEiClass());
        return o;
    };

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<T> getAll() {
        return getService().getAll();
    };

    public Class<T> getEiClass() {
        return eiClass;
    };

    protected abstract Dao<T> getService();;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public T update(T t) {
        T o = getService().update(t);
        if (o == null) {
            o = newCiInstance();
        }
        return o;
    }
}
