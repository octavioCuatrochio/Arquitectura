package DAO.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.FacturaProductoDAO;
import interfaces.ProductoDAO;

public class DerbyDAOFactory extends DAOFactory{

	public static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
	public static final String URI="jdbc:derby:MyDerbyDb;create=true";

	public DerbyDAOFactory() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	@Override
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(URI);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public FacturaDAO getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FacturaProductoDAO getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}



}
