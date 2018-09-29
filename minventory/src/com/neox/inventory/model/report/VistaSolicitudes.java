package com.neox.inventory.model.report;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neox.inventory.util.DBUtils;

@Entity
@Table(name = "vista_solicitudes", catalog = DBUtils.catalog)
public class VistaSolicitudes {


	@Id
	private Integer id;
	private Integer cabecera;
	private Integer posicion;
	private String material;
	private String movimiento;
	private Double porSurtir;
	private Double surtido;
	private String ordenTrabajo;
	private String usuario;
	private String estatus;
	private String fechaEntrega;
	private String horaEntrega;
	private String fechaSolicitud;
	private String horaSolicitud;
	
	public VistaSolicitudes() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCabecera() {
		return cabecera;
	}

	public void setCabecera(Integer cabecera) {
		this.cabecera = cabecera;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Double getPorSurtir() {
		return porSurtir;
	}

	public void setPorSurtir(Double porSurtir) {
		this.porSurtir = porSurtir;
	}

	public Double getSurtido() {
		return surtido;
	}

	public void setSurtido(Double surtido) {
		this.surtido = surtido;
	}

	public String getOrdenTrabajo() {
		return ordenTrabajo;
	}

	public void setOrdenTrabajo(String ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getHoraSolicitud() {
		return horaSolicitud;
	}

	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}
	
}
