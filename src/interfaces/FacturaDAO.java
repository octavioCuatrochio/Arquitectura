package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.Factura;

public interface FacturaDAO {

	Factura getFactura(int id) throws SQLException;
	
	ArrayList<Factura> getAllFacturas() throws SQLException;

}
