package interfaces;

import java.util.ArrayList;

import clases.FacturaProducto;

public interface FacturaProductoDAO {

	boolean startTable();
	
	boolean insert(FacturaProducto fp);
	
	ArrayList<FacturaProducto> getAllFacturaProductos();

}
