package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="tipo_cliente")
public class TipoCliente
{	
   @Id
   @Column(name="id_tipo_cliente")
   private int idTipoCliente;
   
   @Column(name="descripcion")
   private String Descripcion;

public int getIdTipoCliente()
{
	return idTipoCliente;
}

public void setIdTipoCliente(int idTipoCliente)
{
	this.idTipoCliente=idTipoCliente;
}

public String getDescripcion()
{
	return Descripcion;
}

public void setDescripcion(String descripcion)
{
	Descripcion=descripcion;
}



}
