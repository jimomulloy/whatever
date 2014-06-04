package uk.commonline.data.model;

import java.util.List;

/**
 */
public interface ListWrapper<T extends EI<T>> {
	
	List<T> getList();
	
	void setList(List<T> list);
}
