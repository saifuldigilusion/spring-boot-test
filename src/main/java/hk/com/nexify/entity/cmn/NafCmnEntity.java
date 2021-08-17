package hk.com.nexify.entity.cmn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "NafCmnEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@MappedSuperclass
public abstract class NafCmnEntity implements Serializable {

	@XmlTransient
	@Column(name = "CREATE_BY", nullable = false, length = 50)
	protected String createBy;

	@XmlTransient
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "CREATE_ON", nullable = false)
	protected Date createOn;
	
	@XmlTransient
	@Column(name = "UPDATE_BY", nullable = false, length = 50)
	protected String updateBy;
	
	@XmlTransient
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_ON", nullable = false)
	protected Date updateOn;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
}
