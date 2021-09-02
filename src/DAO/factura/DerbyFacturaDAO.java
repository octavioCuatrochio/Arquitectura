package DAO.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Factura;
import interfaces.FacturaDAO;

public class DerbyFacturaDAO implements FacturaDAO {

	private DAOFactory connectionGetter;

	public DerbyFacturaDAO(DAOFactory d) {
		connectionGetter = d;
	}
	
	@Override
	public boolean startTable() {
		try {	
			Connection c = connectionGetter.getConnection();

			String table = "CREATE TABLE Factura(" +
					"idFactura INT," +
					"idCliente INT," +
					"PRIMARY KEY (idFactura))";

			c.prepareStatement(table).execute();
			c.commit();
			c.close();

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean insert(Factura f) {
		try {
			Connection conn = connectionGetter.getConnection();
			
			String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, f.getIdCliente());
			
			ps.executeUpdate();
			ps.close();
			conn.commit();
			conn.close();
			
			return true;
		} catch(SQLException e) {
			return false;
		}
	}

	@Override
	public ArrayList<Factura> getAllFacturas() {
		try {
			Connection c = connectionGetter.getConnection();

			ArrayList<Factura> facturas = new ArrayList<Factura>();

			String select = "SELECT * FROM Factura";
			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//Again, Esto teoricamente anda
				Factura aux = new Factura(rs.getInt(1), rs.getInt(2));
				facturas.add(aux);
			}
			c.close();
			return facturas;
		} catch (SQLException e) {
			return null;
		}
	}
}
