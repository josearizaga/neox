package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "status", catalog = DBUtils.catalog)
public class Status {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String status;
	@Type(type="date")
	@Column(name = "creation_date")
	private Date creationDate;
	@Type(type="time")
	@Column(name = "creation_time")
	private Date creationTime;
	private boolean active;
	private boolean end;
	private boolean approval;
	
	public Status() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}
}
