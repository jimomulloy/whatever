package uk.commonline.data.model;

//import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for
 * objects needing this property.
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements EI {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    protected Long id;

    public void setId(Long id) {
	this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

    @Transient
    @XmlTransient
    public boolean isNew() {
	return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	BaseEntity that = (BaseEntity) o;

	if (id != null ? !id.equals(that.id) : that.id != null)
	    return false;

	return true;
    }

    @Override
    public int hashCode() {
	return id != null ? id.hashCode() : 0;
    }

}
