package DAO.facturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.FacturaProducto;
import interfaces.FacturaProductoDAO;

public class DerbyFacturaProductoDAO implements FacturaProductoDAO {

	private DAOFactory connectionGetter;

	public DerbyFacturaProductoDAO(DAOFactory d) {
		connectionGetter = d;
	}

	@Override
	public boolean startTable() {
		try {
			Connection c = connectionGetter.getConnection();

			String table = "CREATE TABLE Factura_Producto(" + "idFactura INT," + "idProducto INT," + "cantidad INT)";

			c.prepareStatement(table).execute();
			c.commit();
			c.close();

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean insert(FacturaProducto fp) {
		try {
			Connection conn = connectionGetter.getConnection();

			String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, fp.getIdFactura());
			ps.setInt(2, fp.getIdProducto());
			ps.setInt(3, fp.getCantidad());

			ps.executeUpdate();
			ps.close();
			conn.commit();
			conn.close();

			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public ArrayList<FacturaProducto> getAllFacturaProductos() {
		try {
			Connection c = connectionGetter.getConnection();

			ArrayList<FacturaProducto> facturas = new ArrayList<FacturaProducto>();

			String select = "SELECT * FROM Factura_Producto";
			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Again, Esto teoricamente anda
				FacturaProducto aux = new FacturaProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				facturas.add(aux);
			}
			c.close();
			return facturas;
		} catch (SQLException e) {
			return null;
		}
	}

}
