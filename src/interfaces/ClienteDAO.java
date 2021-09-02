package interfaces;

import java.util.ArrayList;

import clases.Cliente;

public interface ClienteDAO {

		boolean startTable();
		
		boolean insert(Cliente c);
		
		ArrayList<Cliente> getAllClientes();
		
		ArrayList<Cliente> getClientesByRecaudacion();
}
