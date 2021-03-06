package DAO.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Cliente;
import interfaces.ClienteDAO;

public class DerbyClienteDAO implements ClienteDAO {

	private DAOFactory connectionGetter;

	public DerbyClienteDAO(DAOFactory d) {
		connectionGetter = d;
	}
	
	@Override
	public boolean startTable() {
		try {	
			Connection c = connectionGetter.getConnection();

			String table = "CREATE TABLE Cliente(" +
					"idCliente INT," +
					"nombre VARCHAR(500)," +
					"email VARCHAR(150)," +
					"PRIMARY KEY (idCliente))";

			c.prepareStatement(table).execute();
			c.commit();
			c.close();

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean insert(Cliente c) {
		try {
			Connection conn = connectionGetter.getConnection();
			
			String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, c.getIdCliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());
			
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
	public ArrayList<Cliente> getAllClientes() {
		try {
			Connection c = connectionGetter.getConnection();
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			String select = "SELECT * FROM Cliente";
			
			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente aux = new Cliente(rs.getInt(1), rs.getString(2),rs.getString(3));
				clientes.add(aux);
			}
		
			c.close();
			return clientes;
			
		} catch (SQLException e) {
			return new ArrayList<Cliente>();
		}
	}
	
	public ArrayList<Cliente> getClientesByRecaudacion() {
		try {
			Connection c = connectionGetter.getConnection();

			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			String select = "SELECT c.*, SUM(p.valor * fp.cantidad) AS suma FROM cliente AS c JOIN factura AS f ON (c.idCliente = f.idCliente) JOIN factura_producto AS fp ON (f.idFactura = fp.idFactura) JOIN producto AS p ON fp.idProducto = p.idProducto GROUP BY c.idCliente ORDER BY suma DESC ";

			PreparedStatement ps = c.prepareStatement(select);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente aux = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				clientes.add(aux);
			}

			c.close();
			return clientes;

		} catch (SQLException e) {
			return new ArrayList<Cliente>();
		}
	}
	
}
