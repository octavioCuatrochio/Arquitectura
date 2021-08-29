package DAO.factory;

import java.sql.Connection;

import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.FacturaProductoDAO;
import interfaces.ProductoDAO;

public abstract class DAOFactory {

	  // List of DAO types supported by the factory
	  public static final int MYSQL = 1;
	  public static final int DERBY = 2;

	  // There will be a method for each DAO that can be 
	  // created. The concrete factories will have to 
	  // implement these methods.
	  public abstract Connection getConnection();
	  public abstract FacturaDAO getFacturaDAO();
	  public abstract ClienteDAO getClienteDAO();
	  public abstract ProductoDAO getProductoDAO();
	  public abstract FacturaProductoDAO getFacturaProductoDAO();

	  public static DAOFactory getDAOFactory(int selectedFactory) {
	  
	    switch (selectedFactory) {
	      case MYSQL: 
	          return new MysqlDAOFactory();
	      case DERBY    : 
	          return new DerbyDAOFactory();      
	      default		 : 
	          return null;
	    }
	  }
	}