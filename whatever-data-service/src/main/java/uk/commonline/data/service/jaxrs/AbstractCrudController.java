package uk.commonline.data.service.jaxrs;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import uk.commonline.data.access.Dao;
import uk.commonline.data.model.EI;
import uk.commonline.data.model.ListWrapper;

/**
 */
public abstract class AbstractCrudController<T extends EI<T>> extends AbstractController {
	public static final String MK_FORM_DATA = "formData";
	public static final String MK_ENTITY = "entity";
	public static final String MK_HAS_ERRORS = "hasErrors";
	public static final String MK_FORM_METHOD = "formMethod";
	public static final String MK_SUBMIT_PATH = "submitPath";
	public static final String MK_CANCEL_PATH = "cancelPath";
		
	// IMPORTANT: Only getCiClass() should access this directly!
	Class<T> eiClass;
	
	public Class<T> getEiClass(){
		return eiClass;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractCrudController() {
		ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
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
	
	@POST
	@Consumes({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
	@Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML })
	public T create(T ei) {
		return getService().create(ei);
	}

	@PUT @Path("{id}")
	@Consumes({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML})
	@Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML })
	public T update(T ei) {
		return getService().update(ei);
	}

	@GET
	@Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML })
	public List<T> findAll() {
		return getSortedList();
	}
	
	/**
	 * @return list of CIs
	 */
	//@RequestMapping(value = "", method = RequestMethod.GET, params = "format=json", produces = "application/json")
	//@ResponseBody
	//public List<T> getListAsJson() { return getSortedList(); }
	
	/**
	 * @return list of CIs
	 * @throws Exception
	 */
	//@SuppressWarnings("unchecked")
	//@RequestMapping(value = "", method = RequestMethod.GET, params = "format=xml", produces = "application/xml")
	//@ResponseBody
	public ListWrapper<T> getListAsXml() throws Exception {
		
		// IMPORTANT: We can't access ciClass directly (need to call getCiClass() to guarantee initialization)
		String wrapperClassName = eiClass.getName() + "$" + eiClass.getSimpleName() + "ListWrapper";
		@SuppressWarnings("unchecked")
		Class<ListWrapper<T>> wrapperClass = (Class<ListWrapper<T>>) Class.forName(wrapperClassName);
		ListWrapper<T> wrapper = wrapperClass.newInstance();
		wrapper.setList(getSortedList());
		return wrapper;
	}
	
	private List<T> getSortedList() { return getService().getAll();/*??return getService().findAll();*/}
	
	/**
	 * @param id
	 * @param out
	 * @throws IOException
	 */
	// From the reference docs: "Furthermore, use of the produces condition ensures the actual content type used to
	// generate the response respects the media types specified in the produces condition."
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=json", produces = "application/json")
	//@ResponseBody
	public T getDetailsAsJson(@PathParam("id") Long id) { return getDetails(id); }
	
	/**
	 * @param id
	 * @return
	 */
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET, params = "format=xml", produces = "application/xml")
	//@ResponseBody
	public T getDetailsAsXml(@PathParam("id") Long id) { return getDetails(id); }
	
	@GET @Path("{id}")
	@Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML })
	private T getDetails(@PathParam("id") Long id) { 
		return getService().get(id); /*??return getService().findOne(id);*/
	}
	
	@DELETE @Path("{id}")
	@Produces({ javax.ws.rs.core.MediaType.APPLICATION_JSON, javax.ws.rs.core.MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") Long id) {
		//notNull(id);
		//??getService().delete(id);
		getService().deleteById(id);
	}
}
