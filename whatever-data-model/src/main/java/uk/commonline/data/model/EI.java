package uk.commonline.data.model;


/**
 * Entity (EI) interface.
 * 
 */
public interface EI<T extends EI<T>> extends Comparable<T> {

	/**
	 * @return CI ID
	 */
	Long getId();

	/**
	 * @param id
	 *            CI ID
	 */
	void setId(Long id);

}
