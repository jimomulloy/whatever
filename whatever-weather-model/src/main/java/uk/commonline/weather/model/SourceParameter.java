package uk.commonline.weather.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uk.commonline.data.model.ListWrapper;
import uk.commonline.data.model.NamedEntity;

@XmlRootElement
@SuppressWarnings("serial")
@Table(name = "SOURCE_PARAMETER")
@Entity
@NamedQueries({ @NamedQuery(name = "SourceParameter.getValueByName", query = "from SourceParameter sp where sp.source = :source and sp.name = name") })
public class SourceParameter extends NamedEntity<SourceParameter> {

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
    public Source getSource() {
	return source;
    }

    public void setSource(Source source) {
	this.source = source;
    }

    @XmlRootElement(name = "sourceparameters")
    public static class LocationListWrapper implements ListWrapper<SourceParameter> {
	private List<SourceParameter> list;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springinpractice.ch11.model.ListWrapper#getList()
	 */
	@Override
	@XmlElement(name = "location")
	public List<SourceParameter> getList() {
	    return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springinpractice.ch11.model.ListWrapper#setList(java.util.List)
	 */
	@Override
	public void setList(List<SourceParameter> list) {
	    this.list = list;
	}
    }

    public String toString() {
	return "WTW Source Parameter name:" + getName() + ", value:" + value;
    }
}