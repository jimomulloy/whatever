package uk.commonline.weather.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uk.commonline.data.model.ListWrapper;
import uk.commonline.data.model.NamedEntity;

@XmlRootElement
@SuppressWarnings("serial")
@Table(name = "SOURCE")
@Entity
@NamedQueries({ @NamedQuery(name = "Source.uniqueByName", query = "from Source s where s.name = :name") })
public class Source extends NamedEntity<Source> {

    private String url;

    public Source() {
    }

    @Column(name = "URL")
    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    @XmlRootElement(name = "sources")
    public static class SourceListWrapper implements ListWrapper<Source> {
	private List<Source> list;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springinpractice.ch11.model.ListWrapper#getList()
	 */
	@Override
	@XmlElement(name = "location")
	public List<Source> getList() {
	    return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springinpractice.ch11.model.ListWrapper#setList(java.util.List)
	 */
	@Override
	public void setList(List<Source> list) {
	    this.list = list;
	}
    }

    public String toString() {
	return "WTW Source:" + getName();
    }
}