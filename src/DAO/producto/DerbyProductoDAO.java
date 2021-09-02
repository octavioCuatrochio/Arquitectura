package DAO.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Producto;
import interfaces.ProductoDAO;

public class DerbyProductoDAO implements ProductoDAO {

	private DAOFactory connectionGetter;

	public DerbyProductoDAO(DAOFactory d) {
		connectionGetter = d;
	}


	public boolean startTable(){
		try {	
			Connection c = connectionGetter.getConnection();

			String table = "CREATE TABLE Producto(" +
					"idProducto INT," +
					"nombre VARCHAR(45)," +
					"valor FLOAT," +
					"PRIMARY KEY (idProducto))";

			c.prepareStatement(table).execute();
			c.commit();
			c.close();

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public ArrayList<Producto> getAllProductos() {
		try {
			Connection c = connectionGetter.getConnection();
			
			ArrayList<Producto> productos = new ArrayList<Producto>();
			
			String select = "SELECT * FROM Producto";
			
			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Producto aux = new Producto(rs.getInt(1), rs.getString(2),rs.getFloat(3));
				productos.add(aux);
			}
		
			c.close();
			return productos;
			
		} catch (SQLException e) {
			return new ArrayList<Producto>();
		}
	}


	@Override
	public boolean insert(Producto p) {
		try {
			Connection c = connectionGetter.getConnection();
			
			String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(insert);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			
			ps.executeUpdate();
			ps.close();
			c.commit();
			c.close();
			
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	

	public Producto getMasVendido() {
		try {
			Connection c = connectionGetter.getConnection();

			Producto retorno = null;

			String select = "SELECT p.idProducto, p.nombre, SUM(p.valor * fp.cantidad) FROM producto p JOIN factura_producto fp ON p.idProducto = fp.idProducto GROUP BY p.idProducto ORDER BY SUM(p.valor * fp.cantidad) DESC LIMIT 1";

			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();

			int id = 0;

			// Necesario mover el puntero para conseguir el objeto.
			rs.next();

			id = rs.getInt(1);

			retorno = this.get(id);
			System.out.println(retorno);
			System.exit(0);

			c.close();
			return retorno;

		} catch (SQLException e) {
			return null;
		}
	}

	public Producto get(int id) {
		try {
			Connection c = connectionGetter.getConnection();

			Producto retorno = null;

			String select = "SELECT * FROM Producto WHERE idProducto = ?";
			PreparedStatement ps = c.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			retorno = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));

			c.close();
			return retorno;

		} catch (SQLException e) {
			return null;
		}
	}
}
