package uk.commonline.data.model;

import java.io.Serializable;


/**
 * Entity (EI) interface.
 * 
 */
public interface EI extends Serializable{

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
