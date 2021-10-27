package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="detalle_orden")
public class DetalleOrden
{	
   @Id
   @Column(name="id_detalle_orden")
   private int idDetalleOrden;
   
   @ManyToOne
   @JoinColumn(name="id_orden")
   private String idOrden;

   @ManyToOne
   @JoinColumn(name="id_producto")
   private String idProducto;

   
   @Column(name="cantidad")
   private int cantidad;


public int getIdDetalleOrden()
{
	return idDetalleOrden;
}


public void setIdDetalleOrden(int idDetalleOrden)
{
	this.idDetalleOrden=idDetalleOrden;
}


public String getIdOrden()
{
	return idOrden;
}


public void setIdOrden(String idOrden)
{
	this.idOrden=idOrden;
}


public String getIdProducto()
{
	return idProducto;
}


public void setIdProducto(String idProducto)
{
	this.idProducto=idProducto;
}


public int getCantidad()
{
	return cantidad;
}


public void setCantidad(int cantidad)
{
	this.cantidad=cantidad;
}

}
