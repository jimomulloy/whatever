package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import uk.commonline.data.model.NamedEntity;

//@XmlRootElement
@SuppressWarnings("serial")
@Table(name = "SOURCE_PARAMETER")
@Entity
@NamedQueries({ @NamedQuery(name = "SourceParameter.getValueByName", query = "from SourceParameter sp where sp.source = :source and sp.name = name") })
public class SourceParameter extends NamedEntity {

    private String value;

    private Source source;

    public SourceParameter() {
    }

    @Column(name = "VALUE")
    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SOURCE_ID", nullable = false)
    @XmlTransient
    public Source getSource() {
	return source;
    }

    public void setSource(Source source) {
	this.source = source;
    }

    @Override
    public String toString() {
	return "SourceParameter [value=" + value + ", source=" + source + ", id=" + id + "]";
    }
}