package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="proveedor_categoria")
public class ProveedorCategoria
{
   @Id
   @Column(name="id_proveedor_categoria")
   private int idProveedorCategoria;
   
   @ManyToOne
   @JoinColumn(name="id_proveedor")
   private Proveedor idProveedor;
	
   @ManyToOne
   @JoinColumn(name="id_categoria")
   private int idCategoria;

public int getIdProveedorCategoria()
{
	return idProveedorCategoria;
}

public void setIdProveedorCategoria(int idProveedorCategoria)
{
	this.idProveedorCategoria=idProveedorCategoria;
}

public Proveedor getIdProveedor()
{
	return idProveedor;
}

public void setIdProveedor(Proveedor idProveedor)
{
	this.idProveedor=idProveedor;
}

public int getIdCategoria()
{
	return idCategoria;
}

public void setIdCategoria(int idCategoria)
{
	this.idCategoria=idCategoria;
}

   

}
