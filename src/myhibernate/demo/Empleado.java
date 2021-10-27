package myhibernate.demo;

import myhibernate.ann.Column;
import myhibernate.ann.Entity;
import myhibernate.ann.Id;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.ManyToOne;
import myhibernate.ann.Table;

@Entity
@Table(name="empleado")
public class Empleado
{
   
@Id
@Column(name="id_empleado")
private int idEmpleado;

@Column(name="nombre")
private String nombre;

@Column(name="id_jefe")
private String idJefe;

   
public int getIdEmpleado()
{
	return idEmpleado;
}

public void setIdEmpleado(int idEmpleado)
{
	this.idEmpleado=idEmpleado;
}

public String getNombre()
{
	return nombre;
}

public void setNombre(String nombre)
{
	this.nombre=nombre;
}

public String getIdJefe()
{
	return idJefe;
}

public void setIdJefe(String idJefe)
{
	this.idJefe=idJefe;
}




   
   

}
