package DAO.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Factura;
import interfaces.FacturaDAO;

public class MysqlFacturaDAO implements FacturaDAO{

	private DAOFactory connectionGetter;

	public MysqlFacturaDAO(DAOFactory d) {
		connectionGetter = d;
	}

	@Override
	public Factura getFactura(int id) throws SQLException {
		Connection c = connectionGetter.getConnection();
		c.setAutoCommit(false);

		try {
			String select = "SELECT * FROM Factura WHERE idFactura = ?";
			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery("" + id);

			//TEORICAMENTEEE esto anda
			c.close();
			return new Factura(rs.getInt(1), rs.getInt(2));

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public ArrayList<Factura> getAllFacturas() throws SQLException {
		try {
			Connection c = connectionGetter.getConnection();
			c.setAutoCommit(false);

			ArrayList<Factura> facturas = new ArrayList<Factura>();

			String select = "SELECT * FROM persona";
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
