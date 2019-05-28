package co.com.cyxtera.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "Auditoria" database table.
 * 
 */
@Entity
@Table(name="\"Auditoria\"", schema="\"operaciones\"")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String CONSULTAR_AUDITORIA = "Auditoria.CONSULTAR_AUDITORIA";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"idAuditoria\"")
	private Integer idAuditoria;

	@Temporal(TemporalType.DATE)
	@Column(name="\"FechaCreacion\"")
	private Date fechaCreacion;

	@Column(name="\"Servicio\"")
	private String servicio;

	@Column(name="\"Transaccion\"")
	private String transaccion;

	@Column(name="\"Usuario\"")
	private String usuario;

	public Auditoria() {
	}

	public Integer getIdAuditoria() {
		return this.idAuditoria;
	}

	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getServicio() {
		return this.servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTransaccion() {
		return this.transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}