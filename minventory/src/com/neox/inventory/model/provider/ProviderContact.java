package com.neox.inventory.model.provider;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "providercontact", catalog = DBUtils.catalog)
public class ProviderContact {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idProvider;
	private String name;
	private String phone;
	private String mail;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	@Type(type="date")
	private Date modificationDate;
	@Type(type="time")
	private Date modificationTime;
	private String creationUser;
	private String modificationUser;
	private boolean active = true;
	
	public ProviderContact() {}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public Date getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
	public String getModificationUser() {
		return modificationUser;
	}
	public void setModificationUser(String modificationUser) {
		this.modificationUser = modificationUser;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "\tid:"+id+"\n,"
				+ "\tidProvider:"+idProvider+",\n"
				+ "\tname:"+name+",\n"
				+ "\tphone:"+phone+",\n"
				+ "\tmail:"+mail+"\n"
				+ "}";
	}
}
