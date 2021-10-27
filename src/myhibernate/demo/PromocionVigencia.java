package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="promocion_vigencia")
public class PromocionVigencia
{
    @Id@Column(name="id_promocion_vigencia")
	private int idPromocionVigencia;
	   
    @ManyToOne
    @JoinColumn(name="id_promocion")
    private Proveedor idPromocion;
	
    @Id@Column(name="fecha_inicio")
	private int fechaInicio;

    @Id@Column(name="fecha_fin")
	private int fechaFin;

	public int getIdPromocionVigencia()
	{
		return idPromocionVigencia;
	}

	public void setIdPromocionVigencia(int idPromocionVigencia)
	{
		this.idPromocionVigencia=idPromocionVigencia;
	}

	public Proveedor getIdPromocion()
	{
		return idPromocion;
	}

	public void setIdPromocion(Proveedor idPromocion)
	{
		this.idPromocion=idPromocion;
	}

	public int getFechaInicio()
	{
		return fechaInicio;
	}

	public void setFechaInicio(int fechaInicio)
	{
		this.fechaInicio=fechaInicio;
	}

	public int getFechaFin()
	{
		return fechaFin;
	}

	public void setFechaFin(int fechaFin)
	{
		this.fechaFin=fechaFin;
	}

}
