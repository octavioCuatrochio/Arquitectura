import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Cliente;
import clases.Factura;
import clases.Producto;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.ProductoDAO;
import scv.reader.FileSCVReader;

public class Main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		
	DAOFactory sqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		
		ProductoDAO prodDAO = sqlFactory.getProductoDAO();
		prodDAO.startTable();
		
		ClienteDAO clientDAO = sqlFactory.getClienteDAO();
		clientDAO.startTable();
		
		FacturaDAO factDAO = sqlFactory.getFacturaDAO();
		factDAO.startTable();
		
		FileSCVReader r = new FileSCVReader();
		ArrayList<Cliente> clientes = r.getClientesFromFile();
		ArrayList<Producto> prods = r.getProductosFromFile();
		ArrayList<Factura> facturas = r.getFacturasFromFile();
		
		prods.forEach((p) -> {
			prodDAO.insert(p);
		});
		
		clientes.forEach((c) -> {
			clientDAO.insert(c);
		});
		
		facturas.forEach((f) -> {
			factDAO.insert(f);
		});
	}
}
