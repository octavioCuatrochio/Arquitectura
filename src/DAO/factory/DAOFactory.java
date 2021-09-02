package DAO.factory;

import java.sql.Connection;

import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.FacturaProductoDAO;
import interfaces.ProductoDAO;

/**
 * @author Octavio Cuatrochio
 * 
 */

public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int DERBY = 2;

	/**
	 * Getter de Conexiones a la DataBase.
	 * 
	 * @return Connection: devuelve conexión ya creada hacia la DB.
	 * @code Siempre cerrar la conexión. Connection c.close();
	 */
	public abstract Connection getConnection();

	public abstract FacturaDAO getFacturaDAO();

	public abstract ClienteDAO getClienteDAO();

	public abstract ProductoDAO getProductoDAO();

	public abstract FacturaProductoDAO getFacturaProductoDAO();

	/**
	 * Getter de Factory dependiente del driver de DB que se quiere usar.
	 * 
	 * @param selectedFactory : Int con la tecnología seleccionada.
	 * @return DAOFactory: Creador de DAOs dependiente del driver.
	 * @exception null En caso de que no este tal tecnología en el switch.
	 */

	public static DAOFactory getDAOFactory(int selectedFactory) {

		switch (selectedFactory) {
			case MYSQL:
				return new MysqlDAOFactory();
			case DERBY:
				return new DerbyDAOFactory();
			default:
				return null;
		}
	}
}