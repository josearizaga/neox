package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.neox.inventory.service.MaterialService;
import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "materialmovement", catalog = DBUtils.catalog)
public class MaterialMovement {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private Integer idHeader;
	private Integer position;
	private Integer idMaterial;
	private Integer idMovement;
	private Integer idStatus;
	private Double qty;
	private Double dqty;
	private Integer idUOM;
	private String workOrder;
	private String purchaseOrder;
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
	
	@Transient
	private Material material;
	
	public MaterialMovement() {
		creationDate = new Date();
		creationTime = new Date();
		modificationDate = new Date();
		modificationTime = new Date();
		qty = new Double(0);
		dqty = new Double(0);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdHeader() {
		return idHeader;
	}
	public void setIdHeader(Integer idHeader) {
		this.idHeader = idHeader;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getIdMovement() {
		return idMovement;
	}
	public void setIdMovement(Integer idMovement) {
		this.idMovement = idMovement;
	}
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Integer getIdUOM() {
		return idUOM;
	}
	public void setIdUOM(Integer idUOM) {
		this.idUOM = idUOM;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
	public String getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
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

	public Material getMaterial() {
		if(material == null) {
			material = MaterialService.getById(idMaterial);
		}
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Double getDqty() {
		return dqty;
	}

	public void setDqty(Double dqty) {
		this.dqty = dqty;
	}
	
	@Override
	public String toString() {
		return "{id:"+id+",idHeader:"+idHeader+",position:"+position+", qty:"+qty+",dqty:"+dqty+"}";
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
	
}
