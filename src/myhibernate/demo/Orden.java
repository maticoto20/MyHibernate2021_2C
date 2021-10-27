package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="orden")
public class Orden
{	
   @Id
   @Column(name="id_orden")
   private int idOrden;
   
   @ManyToOne
   @JoinColumn(name="id_cliente")
   private String idCliente;

   @ManyToOne
   @JoinColumn(name="id_empleado")
   private String idEmpleado;

   @ManyToOne
   @JoinColumn(name="id_tipo_cliente")
   private String idTipoCliente;
   
   @Column(name="fecha_generada")
   private String fechaGenerada;
   
   @Column(name="fecha_entregada")
   private String fechaEntregada;

public int getIdOrden()
{
	return idOrden;
}

public void setIdOrden(int idOrden)
{
	this.idOrden=idOrden;
}

public String getIdCliente()
{
	return idCliente;
}

public void setIdCliente(String idCliente)
{
	this.idCliente=idCliente;
}

public String getIdEmpleado()
{
	return idEmpleado;
}

public void setIdEmpleado(String idEmpleado)
{
	this.idEmpleado=idEmpleado;
}

public String getIdTipoCliente()
{
	return idTipoCliente;
}

public void setIdTipoCliente(String idTipoCliente)
{
	this.idTipoCliente=idTipoCliente;
}

public String getFechaGenerada()
{
	return fechaGenerada;
}

public void setFechaGenerada(String fechaGenerada)
{
	this.fechaGenerada=fechaGenerada;
}

public String getFechaEntregada()
{
	return fechaEntregada;
}

public void setFechaEntregada(String fechaEntregada)
{
	this.fechaEntregada=fechaEntregada;
}
   
   
   
}
