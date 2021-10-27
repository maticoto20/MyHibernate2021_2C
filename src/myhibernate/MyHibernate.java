package myhibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import myhibernate.ann.Column;
import myhibernate.ann.JoinColumn;
import myhibernate.ann.Table;
import myhibernate.demo.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyHibernate
{
	// Recibe el class del Mapping sobre el que queremos 
	// buscar la fila identificada por id
	public static <T> T find(Class<T> clazz, int id)
	{
		// 1. generar el SQL dinamico
		// 		1.1. Sin JOIN (o relacion) => 1 semana
		// 		1.2. Considerando relaciones ManyToOne
		// API: Java Reflection (o introspeccion)
		
		// 2. Invocar o ejecutar el SQL generado en (1)
		// para obtener el ResultSet 
		// API: JDBC (acceso a DB desde Java)

		// 3. Leer los datos obtenidos en (2), instanciar 
		// el objeto y retornarlo.
		
		String SQLQuery = generateQuery(clazz, id);
		System.out.println(SQLQuery);  
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		// Creo el objeto buscado
		T newClass = null;
		try
		{
			newClass=clazz.newInstance();
		}
		catch(InstantiationException|IllegalAccessException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try
		{
			Connection connection = getConnection();
			statement = connection.prepareStatement(SQLQuery);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			// Obtengo todos los metodos de la clase.
			Method[] methods = clazz.getMethods();

			while(resultSet.next()) //deberia haber uno solo
			{
				Field[] fields = clazz.getDeclaredFields();
				int i = 0;
				for(Field f : fields)
				{ 
					Class<T> valueType = (Class<T>)f.getType();

					// Elijo el setter de este valor
					Method setter = null;
					for(Method method : methods)
					{
						if(method.getName().startsWith("set") && method.getName().toLowerCase().contains(f.getName().toLowerCase()))	
						{
							setter = method;
						}
					}	
					
					// Cuando son valores basicos, se asignan directamente
					Column c = (Column)f.getDeclaredAnnotation(Column.class);
					if (c != null && setter != null) 
					{
						if(valueType == int.class)
						{
							// Le agrego +1 al index porque las columnas del resultSet empiezan en 1
							setter.invoke(newClass, resultSet.getInt(i+1));
						}
						else
						{
							// Le agrego +1 al index porque las columnas del resultSet empiezan en 1
							setter.invoke(newClass, valueType.cast(resultSet.getObject(i+1)));
						}
					}
					
					// Cuando el valor es un objeto que hay que instanciar primero, se busca de manera recursiva
					JoinColumn jc = (JoinColumn)f.getDeclaredAnnotation(JoinColumn.class);
					if (jc != null && setter != null) 
					{
						int valueID = resultSet.getInt(i+1);
						setter.invoke(newClass, valueType.cast(find(valueType, valueID)));
					}
					
					i++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
		return (T)newClass;
	}

	
	public static <T> List<T> findAll(Class<T> clazz)
	{
		// PROGRAMAR AQUI
		return null;
	}
	
	public static Query createQuery(String hql)
	{
		// PROGRAMAR AQUI
		return null;
	}
	
	public static <T> String generateQuery(Class<T> clazz, int id)
	{
		// Obtengo los annotations de la clase, me quedo con el @Table
		Table table = (Table)clazz.getDeclaredAnnotation(Table.class);

		// Obtengo los atributos de la clase
		Field[] fields = clazz.getDeclaredFields();
		List<String> columns = new ArrayList<String>();
		
		// Itero sobre los atributos y consigo sus annotations (los que tengan @Column o @JoinColumn)y agrego el nombre del 
		// annotation a la lista de columnas
		for(Field f:fields)
		{ 
			Column c = (Column)f.getDeclaredAnnotation(Column.class);
			if (c != null) 
				columns.add(c.name());
			JoinColumn jc = (JoinColumn)f.getDeclaredAnnotation(JoinColumn.class);
			if (jc != null) 
				columns.add(jc.name());
		}
		
		// Agrego cada columna al query
		String SQLQuery = "select ";
		for(int i = 0; i < columns.size(); i++)
		{
			if(i != 0) SQLQuery += ", ";
			SQLQuery += columns.get(i);
		}
		SQLQuery += " from " + table.name();
		SQLQuery += " where "+ columns.get(0) + "=?";
		return SQLQuery;
		
	}
	
	public static Connection getConnection()
	{
		String usr = "sa";
		String pwd = "";
		String drv = "org.hsqldb.jdbcDriver";
		String url = "jdbc:hsqldb:hsql://localhost/xdb";
		
		Connection con = null;
		try
		{
			Class.forName(drv);
			con = DriverManager.getConnection(url, usr, pwd);
			return con;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);	
		}
	}
	
	  private static boolean isSetter(Method method)
	  {
	    // identify set methods
	    if(method.getName().startsWith("set") && method.getParameterCount() == 1 
	        && method.getReturnType().equals(void.class)){
	      return true;
	    }
	    return false;    
	  }
}
