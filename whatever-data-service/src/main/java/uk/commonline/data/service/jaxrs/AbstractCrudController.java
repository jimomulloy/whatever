package uk.commonline.data.service.jaxrs;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;

/**
 */
public abstract class AbstractCrudController<T extends EI<T>> extends
		AbstractController {
	
	Class<T> eiClass;

	public Class<T> getEiClass() {
		return eiClass;
	}

	@SuppressWarnings("unchecked")
	public AbstractCrudController() {
		ParameterizedType paramType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.eiClass = (Class<T>) paramType.getActualTypeArguments()[0];
	}

	protected abstract Dao<T> getService();

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
	}

	protected List<T> getSortedList() {
		System.out.println("!!B Service is:"+getService());
		return getService().getAll();/* ??return getService().findAll(); */
	}

	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<T> findAll() {
		return getSortedList();
	}

	@GET @Path("{id}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public T getDetails(@PathParam("id") Long id) { 
		T object = getService().get(id);
		if(object==null){
			object = newCiInstance();
		}
		return object; 
	}

}
