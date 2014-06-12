package uk.commonline.data.client.jaxrs;

import java.io.Serializable;
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
public abstract class AbstractCrudClient<T extends EI<T>> extends
		AbstractDataClient implements Dao<T> {

	Class<T> eiClass;

	protected RestClient restClient;

	public Class<T> getEiClass() {
		return eiClass;
	}

	@SuppressWarnings("unchecked")
	public void setRestClient(RestClient restClient) {
		ParameterizedType paramType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.eiClass = (Class<T>) paramType.getActualTypeArguments()[0];
		this.restClient = restClient;
	}

	public RestClient getRestClient() {
		return restClient;
	}

	abstract protected String getPath();

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

	public List<T> getAll() {

		GenericType<List<T>> list = new GenericType<List<T>>() {
		};

		List<T> entities = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/"))
				.path(getPath()).request(MediaType.APPLICATION_JSON).get(list);
		return entities;
	}

	public T create(T t) {
		T o = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/"))
				.path(getPath())
				.request()
				.post(Entity.entity(t, MediaType.APPLICATION_JSON), eiClass);
		return o;
	}

	public T update(T t) {
		WebTarget target = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/")).path(getPath());
		System.out.println("!!Update target ="+target);
		T o = target
				.request()
				.post(Entity.entity(t, MediaType.APPLICATION_JSON), eiClass);
		return o;
	}

	public T get(Long id) {
		T o = getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/"))
				.path(getPath()).path("/{id}").resolveTemplate("id", id)
				.request(MediaType.APPLICATION_JSON).get(eiClass);
		if (o == null) {
			o = newCiInstance();
		}
		return o;
	};

	public T load(Long id) {
		return null;
	};

	public void delete(T t) {
		deleteById(t.getId());
	};

	public void deleteById(Long id) {
		getRestClient()
				.getClient()
				.target(getRestClient().createUrl(
						"http://localhost:8080/wtwbase/webresources/"))
				.path(getPath()).path("/{id}").resolveTemplate("id", id)
				.request().delete();
	};

	public void deleteAll() {

	};

	public long count() {
		return 0;
	};

	public boolean exists(Long id) {
		T o = get(id);
		return o == null ? false : true;
	}
}
