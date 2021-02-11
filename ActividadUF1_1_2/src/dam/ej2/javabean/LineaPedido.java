package dam.ej2.javabean;

import java.io.Serializable;

public class LineaPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private Producto producto;
	private int cantidad;
	private double precioTotal;
	private String fechaPedido;
	
	
	public LineaPedido(int idPedido, Producto producto, int cantidad,
			double precioTotal, String fechaPedido) {
		this.idPedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.fechaPedido = fechaPedido;
	}
	
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}


	@Override
	public String toString() {
		return "Linea pedido - ID: " + idPedido + ", Producto " + producto
				+ " Cantidad: " + cantidad + " Precio Total: " + precioTotal
				+ " - Fecha del pedido: " + fechaPedido;
	}
	
	
	
	
}
