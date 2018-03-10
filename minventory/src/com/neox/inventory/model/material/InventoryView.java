package com.neox.inventory.model.material;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.neox.inventory.service.MaterialProviderPriceService;
import com.neox.inventory.service.MaterialProviderPriceViewService;
import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "inventoryview", catalog = DBUtils.catalog)
public class InventoryView implements Serializable {
	
	@Id
	private Integer id;
	private Integer idMaterial;
	private Integer idLocation;
	private Integer idProvider;
	private String material;
	private String location;
	private String provider;
	private Double price;
	private Double qty;
	private Double minQty;
	private Double orderPoint;
	@Type(type="date")
	private Date creationDate;
	@Type(type="time")
	private Date creationTime;
	@Type(type="date")
	private Date modificationDate;
	@Type(type="time")
	private Date modificationTime;
	private String matdesc;
	private String locdesc;
	
	@Transient
	List <MaterialProviderPriceView> materialProviderPriceList;
	
	public InventoryView() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getMinQty() {
		return minQty;
	}

	public void setMinQty(Double minQty) {
		this.minQty = minQty;
	}

	public Double getOrderPoint() {
		return orderPoint;
	}

	public void setOrderPoint(Double orderPoint) {
		this.orderPoint = orderPoint;
	}

	public String getMatdesc() {
		return matdesc;
	}

	public void setMatdesc(String matdesc) {
		this.matdesc = matdesc;
	}

	public String getLocdesc() {
		return locdesc;
	}

	public void setLocdesc(String locdesc) {
		this.locdesc = locdesc;
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "\tid:"+id+",\n"
				+ "\tmaterial:"+material+",\n"
				+ "\tlocation:"+location+",\n"
				+ "\tqty:"+qty+",\n"
				+ "\tminQty:"+minQty+",\n"
				+ "\torderPoint:"+orderPoint+"\n"
				+ "}";
	}

	public Integer getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Integer idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public String getRowStyleClass() {
		if(qty < minQty) {
			return "red";
		}
		if(qty <= orderPoint) {
			return "yellow";
		}
		return null;
	}
	
	public String getStatus() {
		if(qty < minQty) {
			return "Crítico";
		}
		if(qty <= orderPoint) {
			return "Alerta";
		}
		return "OK";
	}
	
	public void add(Double qty) {
		this.qty += qty;
	}

	public Integer getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Integer idLocation) {
		this.idLocation = idLocation;
	}

	public Integer getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public List<MaterialProviderPriceView> getMaterialProviderPriceList() {
		if(materialProviderPriceList == null) {
			materialProviderPriceList = MaterialProviderPriceViewService.getListByIdMaterial(idMaterial);
		}
		return materialProviderPriceList;
	}
}
