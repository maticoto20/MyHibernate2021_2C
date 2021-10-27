package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="promocion_producto")
public class PromocionProducto
{
    @Id@Column(name="id_promocion_producto")
	private int idPromocionProducto;
	   
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Proveedor idProducto;
	
    @ManyToOne
    @JoinColumn(name="id_promocion_vigencia")
    private int idPromocionVigencia;

    @Column(name="descuento")
	private int descuento;

	public int getIdPromocionProducto()
	{
		return idPromocionProducto;
	}

	public void setIdPromocionProducto(int idPromocionProducto)
	{
		this.idPromocionProducto=idPromocionProducto;
	}

	public Proveedor getIdProducto()
	{
		return idProducto;
	}

	public void setIdProducto(Proveedor idProducto)
	{
		this.idProducto=idProducto;
	}

	public int getIdPromocionVigencia()
	{
		return idPromocionVigencia;
	}

	public void setIdPromocionVigencia(int idPromocionVigencia)
	{
		this.idPromocionVigencia=idPromocionVigencia;
	}

	public int getDescuento()
	{
		return descuento;
	}

	public void setDescuento(int descuento)
	{
		this.descuento=descuento;
	}

	
}
