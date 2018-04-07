package com.neox.inventory.model.provider;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "provider", catalog = DBUtils.catalog)
public class Provider implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
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
	private String delivery;
	private String payment;
	private String list;
	private boolean active = true;
	
	@Transient
	private List<String> chips;
	@Transient
	private List<String> mailChips;
	
	public Provider() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "{"
				+ "\tid:"+id+",\n"
				+ "\tname:"+name+",\n"
				+ "\tphone:"+phone+",\n"
				+ "\tmail:"+mail+"\n"
				+ "}";
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public List<String> getChips() {
		return chips;
	}

	public void setChips(List<String> chips) {
		this.chips = chips;
	}
	
	public void fromStringToList() {
		System.out.println("List: " + list);
		if(list != null) {
			String[] array = list.split(",");
			chips = new ArrayList<String>();
			for(String s:array) {
				chips.add(s);
			}
		}
		if(mail != null) {
			String[] array = mail.split(",");
			mailChips = new ArrayList<String>();
			for(String s:array) {
				mailChips.add(s);
			}
		}
		
	}

	public List<String> getMailChips() {
		return mailChips;
	}

	public void setMailChips(List<String> mailChips) {
		this.mailChips = mailChips;
	}
}
