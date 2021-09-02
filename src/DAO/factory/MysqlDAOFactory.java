package DAO.factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DAO.cliente.MysqlClienteDAO;
import DAO.factura.MysqlFacturaDAO;
import DAO.facturaProducto.MysqlFacturaProductoDAO;
import DAO.producto.MysqlProductoDAO;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.FacturaProductoDAO;
import interfaces.ProductoDAO;

public class MysqlDAOFactory extends DAOFactory {

	public static final String DRIVER="com.mysql.cj.jdbc.Driver";
	public static final String URI="jdbc:mysql://localhost:3306/bd_metodologias";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public MysqlDAOFactory() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(URI, USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public FacturaDAO getFacturaDAO() {
		return new MysqlFacturaDAO(this);
	}

	@Override
	public ClienteDAO getClienteDAO() {
		return new MysqlClienteDAO(this);
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new MysqlProductoDAO(this);
	}

	@Override
	public FacturaProductoDAO getFacturaProductoDAO() {
		return new MysqlFacturaProductoDAO(this);
	}
}
