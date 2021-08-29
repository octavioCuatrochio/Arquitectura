package scv.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import clases.Cliente;
import clases.Producto;

public class FileSCVReader {

	public FileSCVReader(){}

	public ArrayList<Producto> getProductosFromFile(){
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/productos.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Producto> productos = new ArrayList<>();
		
		for(CSVRecord row: parser) {

			//En la documentacion, idProducto se guarda en la db como int, asi que me arriesgo a castearlo
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
	
	public ArrayList<Cliente> getClientesFromFile(){
		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("files/clientes.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Cliente> clientes = new ArrayList<>();
		
		for(CSVRecord row: parser) {

			//En la documentacion, idProducto se guarda en la db como int, asi que me arriesgo a castearlo
			String aux = row.get("idCliente");
			int idCliente = Integer.parseInt(aux);

			String nombre = row.get("nombre");
			
			String email = row.get("email");
			
			Cliente newClient = new Cliente(idCliente, nombre, email);
			clientes.add(newClient);
		}
		
		return clientes;
	}
	
}
