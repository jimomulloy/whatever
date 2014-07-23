package uk.commonline.data.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>
 * . Used as a base class for objects needing these properties.
 */
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @Column(name = "NAME")
    private String name;

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
    }

    @Override
    public String toString() {
	return this.getName();
    }

}
