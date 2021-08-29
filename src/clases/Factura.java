package clases;

public class Factura {
	private int idFactura;
	private int idCliente;
	
	public Factura(int idFactura, int idCliente) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}
	public int getIdCliente() {
		return idCliente;
	}

	public int getIdFactura() {
		return idFactura;
	}
	
}
