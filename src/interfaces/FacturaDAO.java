package interfaces;

import java.util.ArrayList;
import clases.Factura;

public interface FacturaDAO {

	boolean startTable();
	
	boolean insert(Factura f);
	
	ArrayList<Factura> getAllFacturas();

}
