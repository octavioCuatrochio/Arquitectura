package DAO.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DAO.cliente.DerbyClienteDAO;
import DAO.factura.DerbyFacturaDAO;
import DAO.facturaProducto.DerbyFacturaProductoDAO;
import DAO.producto.DerbyProductoDAO;
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
		return new DerbyFacturaDAO(this);
	}
	@Override
	public ClienteDAO getClienteDAO() {
		return new DerbyClienteDAO(this);
	}
	@Override
	public ProductoDAO getProductoDAO() {
		return new DerbyProductoDAO(this);
	}
	@Override
	public FacturaProductoDAO getFacturaProductoDAO() {
		return new DerbyFacturaProductoDAO(this);
	}



}
