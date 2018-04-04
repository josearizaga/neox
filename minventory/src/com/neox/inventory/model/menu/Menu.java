package com.neox.inventory.model.menu;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "menu", catalog = DBUtils.catalog)
public class Menu implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Integer idMenu;
	private String label;
	private String value;
	private String outcome;
	
	public Menu() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public String toString() {
		return "["
				+ "id:"+id+",\n"
				+ "\tidMenu:"+idMenu+",\n"
				+ "\tlabel:"+label+",\n"
				+ "\toutcome:"+outcome+",\n"
				+ "\tvalue:"+value+",\n"
		+ "]";
	}
}
