package interfaces;

import java.util.ArrayList;

import clases.Producto;

public interface ProductoDAO{

	boolean startTable();
	
	boolean insert(Producto p);
	
	ArrayList<Producto> getAllProductos();
}
