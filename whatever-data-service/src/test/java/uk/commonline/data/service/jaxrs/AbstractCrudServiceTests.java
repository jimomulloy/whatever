package uk.commonline.data.service.jaxrs;

import static org.junit.Assert.assertNotNull;

import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Paths;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.commonline.data.model.EI;

/**
 */
public abstract class AbstractCrudServiceTests<T extends EI> {
	protected static final String VN_CREATE_ENTITY_FORM = "createEntityForm";
	protected static final String VN_DELETE_ENTITY_SUCCESS = "deleteEntitySuccess";
	protected static final String VN_EDIT_ENTITY_FORM = "editEntityForm";
	protected static final String VN_EDIT_ENTITY_SUCCESS = "editEntitySuccess";
	protected static final String VN_ENTITY_DETAILS = "entityDetails";
	protected static final String VN_ENTITY_LIST = "entityList";
	protected static final String VN_PUT_EDIT_ENTITY_FORM_SUCCESS = "putEditEntityFormSuccess";

	private static final String CONTROLLER_PACKAGE = "com.springinpractice.ch11.web.controller";

	// Class under test
	@InjectMocks
	protected AbstractCrudService<T> controller;

	// Dependencies
	@Mock
	protected Paths paths;
	@Mock
	protected ObjectMapper objectMapper;

	// Test objects
	@Mock
	protected T entity;
	@Mock
	protected Writer out;

	protected Class<T> getEiClass() {
		return controller.eiClass;
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.controller = newControllerInstance();
		MockitoAnnotations.initMocks(this);

		// when(sitemap.getNode((String) any())).thenReturn(node);
		// when(sitemap.getEntityListViewId(getCiClass())).thenReturn(VN_ENTITY_LIST);
		// when(sitemap.getCiDetailsViewId(getCiClass())).thenReturn(VN_ENTITY_DETAILS);
		// when(sitemap.getCreateFormId(getCiClass())).thenReturn(VN_CREATE_ENTITY_FORM);
		// when(sitemap.getEditFormId(getCiClass())).thenReturn(VN_EDIT_ENTITY_FORM);
		// when(viewNames.putEditFormSuccessViewName(getCiClass(),
		// 1L)).thenReturn(VN_EDIT_ENTITY_SUCCESS);

		// when(viewNames.putEditFormSuccessViewName(getCiClass(),
		// 1L)).thenReturn(VN_PUT_EDIT_ENTITY_FORM_SUCCESS);
		// when(viewNames.deleteSuccessViewName(getCiClass())).thenReturn(VN_DELETE_ENTITY_SUCCESS);
		// when(viewNames.putEditFormSuccessViewName(getCiClass(),
		// 1L)).thenReturn(VN_EDIT_ENTITY_SUCCESS);

		doSetUp();
	}

	@SuppressWarnings("unchecked")
	private Class<AbstractCrudService<T>> getControllerClass() {
		ParameterizedType paramType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Class<T> entityClass = (Class<T>) paramType.getActualTypeArguments()[0];
		String ecName = entityClass.getSimpleName();
		String pkgName = ecName.toLowerCase();

		String ccName = CONTROLLER_PACKAGE + "." + pkgName + "." + ecName
				+ "CrudController";
		try {
			return (Class<AbstractCrudService<T>>) Class.forName(ccName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private AbstractCrudService<T> newControllerInstance() {
		try {
			return (AbstractCrudService<T>) getControllerClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void doSetUp() throws Exception {
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		doTearDown();
	}

	protected void doTearDown() throws Exception {
	}

	@Test
	public void testGetEntityListAsJson() {
		List<T> entities = controller.getAll();
		assertNotNull(entities);
	}

	// @Test
	// public void testGetEntityListAsXml() throws Exception {
	// ListWrapper<T> wrapper = controller.getListAsXml();
	// assertNotNull(wrapper);

	// List<T> list = wrapper.getList();
	// assertNotNull(list);
	// }

	// @Test
	// public void testGetDetailsAsJson() throws IOException {
	// controller.getDetailsAsJson(1L, out);
	// verify(objectMapper, times(1)).writeValue(eq(out), anyObject());
	// }

	// @Test
	// public void testGetDetailsAsXml() {
	// T entity = controller.getDetailsAsXml(1L);
	// assertNotNull(entity);
	// }

	/**
	 * Confirms that we handle validation errors properly.
	 */
	// @Test
	// public void testPutEditEntityFormWithErrors() {
	// when(result.hasErrors()).thenReturn(true);
	// String viewName = controller.putEditForm(1L, entity, result, model);
	// assertEquals(VN_EDIT_ENTITY_FORM, viewName);
	// }

	// =================================================================================================================
	// Delete
	// =================================================================================================================

	// @Test
	// public void testDeleteEntity() {
	// controller.remove(1L);
	// assertEquals(VN_DELETE_ENTITY_SUCCESS, viewName);
	// }
}
