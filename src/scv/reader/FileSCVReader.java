package scv.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Cliente;
import clases.Factura;
import clases.FacturaProducto;
import clases.Producto;

/**
 * Se encarga de leer, parsear y devolver en listas de objetos varios archivos
 * .csv.
 * 
 * @author Octavio Cuatrochio
 */

public class FileSCVReader {

	public FileSCVReader() {
	}

	/**
	 * Lee, obtiene y parsea un archivo a Producto.
	 * 
	 * @return Un ArrayList con objetos Producto
	 * @exception IOException Archivo no encontrado o no accesible.
	 * @see Clase Producto
	 */
	public ArrayList<Producto> getProductosFromFile() {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/productos.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Producto> productos = new ArrayList<>();

		for (CSVRecord row : parser) {

			// En la documentacion, idProducto se guarda en la db como int, asi que me
			// arriesgo a castearlo
			String aux = row.get("idProducto");
			int idProducto = Integer.parseInt(aux);

			String nombre = row.get("nombre");

			String aux2 = row.get("valor");
			float valor = Float.parseFloat(aux2);

			Producto newProd = new Producto(idProducto, nombre, valor);
			productos.add(newProd);
		}

		return productos;
	}

	/**
	 * Lee, obtiene y parsea un archivo a Factura.
	 * 
	 * @return Un ArrayList con objetos Factura.
	 * @exception IOException Archivo no encontrado o no accesible.
	 * @see Clase Factura
	 */
	public ArrayList<Factura> getFacturasFromFile() {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/facturas.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Factura> facturas = new ArrayList<>();

		for (CSVRecord row : parser) {

			// En la documentacion, idProducto se guarda en la db como int, asi que me
			// arriesgo a castearlo
			String aux = row.get("idFactura");
			int idFactura = Integer.parseInt(aux);

			String aux2 = row.get("idCliente");
			int idCliente = Integer.parseInt(aux2);

			Factura newFac = new Factura(idFactura, idCliente);
			facturas.add(newFac);
		}

		return facturas;
	}

	/**
	 * Lee, obtiene y parsea un archivo a Cliente.
	 * 
	 * @return Un ArrayList con objetos Cliente.
	 * @exception IOException Archivo no encontrado o no accesible.
	 * @see Clase Cliente
	 */

	public ArrayList<Cliente> getClientesFromFile() {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/clientes.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Cliente> clientes = new ArrayList<>();

		for (CSVRecord row : parser) {

			// En la documentacion, idProducto se guarda en la db como int, asi que me
			// arriesgo a castearlo
			String aux = row.get("idCliente");
			int idCliente = Integer.parseInt(aux);

			String nombre = row.get("nombre");

			String email = row.get("email");

			Cliente newClient = new Cliente(idCliente, nombre, email);
			clientes.add(newClient);
		}

		return clientes;
	}

	/**
	 * Lee, obtiene y parsea un archivo a FacturaProducto.
	 * 
	 * @return Un ArrayList con objetos FacturaProducto.
	 * @exception IOException Archivo no encontrado o no accesible.
	 * @see Clase FacturaProducto
	 */

	public ArrayList<FacturaProducto> getFacturaProductosFromFile() {
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/facturas-productos.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<FacturaProducto> fps = new ArrayList<>();

		for (CSVRecord row : parser) {

			// private int idFactura;
			// private int idProducto;
			// private int cantidad;

			// En la documentacion, idProducto se guarda en la db como int, asi que me
			// arriesgo a castearlo
			String aux = row.get("idFactura");
			int idFactura = Integer.parseInt(aux);

			String aux2 = row.get("idProducto");
			int idProducto = Integer.parseInt(aux2);

			String aux3 = row.get("cantidad");
			int cantidad = Integer.parseInt(aux3);

			FacturaProducto newFP = new FacturaProducto(idFactura, idProducto, cantidad);
			fps.add(newFP);
		}

		return fps;
	}

}
