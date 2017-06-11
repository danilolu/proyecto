package com.ipartek.danilozano.Tipos;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class Carrito {

	private static Logger log = Logger.getLogger(Carrito.class);

	TreeMap<Integer, Producto> carritoLista;

	public Carrito() {
		super();
		this.carritoLista = new TreeMap<>();
		log.info("Creado carrito vacio");

	}

	public Carrito(TreeMap<Integer, Producto> carritoLista) {
		super();
		this.carritoLista = carritoLista;
	}

	public Producto buscarPorId(Integer id) {

		return carritoLista.get(id);

	}

	public void anadirAlCarrito(Producto producto) {

		carritoLista.put(producto.getId(), producto);
	}

	public void quitarDelCarrito(Integer id) {

		carritoLista.remove(id);

	}

	public Producto[] buscarTodosLosProductos() {
		return carritoLista.values().toArray(new Producto[carritoLista.size()]);
	}

	public Double precioTotal() {

		Producto[] carritoArr = this.buscarTodosLosProductos();

		Double precioTotal = 0.0;

		for (Producto p : carritoArr) {

			precioTotal += p.getPrecio();

		}

		return precioTotal;
	}

	public TreeMap<Integer, Producto> getListaProductos() {
		return carritoLista;
	}

	public void setListaProductos(TreeMap<Integer, Producto> listaProductos) {
		this.carritoLista = listaProductos;
	}

	@Override
	public String toString() {
		return "carritoLista=" + carritoLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carritoLista == null) ? 0 : carritoLista.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		if (carritoLista == null) {
			if (other.carritoLista != null)
				return false;
		} else if (!carritoLista.equals(other.carritoLista))
			return false;
		return true;
	}

}