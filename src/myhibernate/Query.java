package myhibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import myhibernate.demo.Proveedor;

public class Query
{
	private String pName;
	private Object pValue;
	private String hql;
	private String pValueString;
	public void setParameter(String pName,Object pValue)
	{
		this.pName = pName;
		this.pValue = pValue;
		this.pValueString = (String)pValue;
	}	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultList()
	{
		Class<T> newClass = null;
		try
		{
			newClass=(Class<T>)Class.forName(getClassName(hql.toLowerCase().split(" ")[1]));
		}
		catch(ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return MyHibernate.findAllWithCondition(newClass, createJoinCondition(hql), pValue);
	}

	
	public void setHql(String hql)
	{
		this.hql = hql;
	}
	
	private String[] getArguments(String hql)
	{
		// Separo el hql y me quedo con los argumentos despues del WHERE
		return hql.toLowerCase().split("where")[1].trim().split("=:")[0].split("\\.");
	}
	
	public String getClassName(String hqlClassName)
	{
		return "myhibernate.demo." + hqlClassName.substring(0, 1).toUpperCase() + hqlClassName.substring(1);
	}

	public <T> String createJoinCondition(String hql)
	{
		String args1[] = getArguments(hql);
		
		// le agrego el nombre de la variable que se le haya asignado en el hql -> Producto p
		String queryJoins = " " + args1[0];
		
		// Le agrego los joins necesarios
		for(int i = 1; i < args1.length-1; i++)
		{
//			System.out.println(args1[i]);
			queryJoins += " join " + args1[i] + " on " + args1[i-1] + ".id_" + args1[i] + " = " + args1[i] + ".id_" + args1[i];
		}
		
		queryJoins += " where " + args1[args1.length - 2] + "." + args1[args1.length - 1] + " = ";
		queryJoins += "?";
		return queryJoins;
	}
}
