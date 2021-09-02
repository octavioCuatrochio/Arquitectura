import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.factory.DAOFactory;
import clases.Cliente;
import clases.Factura;
import clases.FacturaProducto;
import clases.Producto;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.FacturaProductoDAO;
import interfaces.ProductoDAO;
import scv.reader.FileSCVReader;

public class Main {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.DERBY);

		ProductoDAO prodDAO = factory.getProductoDAO();
		prodDAO.startTable();

		ClienteDAO clientDAO = factory.getClienteDAO();
		clientDAO.startTable();

		FacturaDAO factDAO = factory.getFacturaDAO();
		factDAO.startTable();

		FacturaProductoDAO fpDAO = factory.getFacturaProductoDAO();
		fpDAO.startTable();
		System.out.println(prodDAO.getMasVendido());

		FileSCVReader r = new FileSCVReader();
		ArrayList<Cliente> clientes = r.getClientesFromFile();
		ArrayList<Producto> prods = r.getProductosFromFile();
		ArrayList<Factura> facturas = r.getFacturasFromFile();
		ArrayList<FacturaProducto> fps = r.getFacturaProductosFromFile();

		prods.forEach((p) -> {
			prodDAO.insert(p);
		});

		clientes.forEach((c) -> {
			clientDAO.insert(c);
		});

		facturas.forEach((f) -> {
			factDAO.insert(f);
		});

		fps.forEach((fp) -> {
			fpDAO.insert(fp);
		});
		
	}
}
