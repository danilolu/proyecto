package com.ipartek.danilozano.DAL;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class TiendaDALColeccion implements TiendaDAL {

	//Dalcoleccion de Productos
		private Map<Integer, Producto> productos = new TreeMap<Integer, Producto>();

		public void alta(Producto producto) {
			if (productos.containsKey(producto.getId()))

				throw new IdProductoYaExistenteDALException("Ya existe el producto con esta ID:  " + producto.getId());

			productos.put(producto.getId(), producto);
			Producto.cont++;
		}

		public void modificar(Producto producto) {
			if (!productos.containsKey(producto.getId()))
				throw new DALException("Intento de modificar producto no existente ID: " + producto);

			productos.put(producto.getId(), producto);

		}

		public void borrar(Producto producto) {
			productos.remove(producto.getId());

		}

		public Producto[] buscarTodosLosProductos() {

			return productos.values().toArray(new Producto[productos.size()]);
		}

		
		@Override
		public Producto buscarPorId(Integer idmap) {
			// TODO Auto-generated method stub
			return productos.get(idmap);
		}

	//Dalcoleccion de Usuarios
	private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();

	public void alta(Usuario usuario) {
		if (usuarios.containsKey(usuario.getNombre()))
			throw new UsuarioYaExistenteDALException("Ya existe el usuario " + usuario.getNombre());

		usuarios.put(usuario.getNombre(), usuario);
		

	}

	public boolean validar(Usuario usuario) {
		return usuarios.containsValue(usuario);
	}

	public void modificar(Usuario usuario) {
		if (!usuarios.containsKey(usuario.getNombre()))
			throw new DALException("Intento de modificar usuario no existente " + usuario);

		usuarios.put(usuario.getNombre(), usuario);
	}

	public void borrar(Usuario usuario) {
		usuarios.remove(usuario.getNombre());
	}

	public Usuario buscarPorId(String id) {
		return usuarios.get(id);
	}

	public Usuario[] buscarTodosLosUsuarios() {

		return usuarios.values().toArray(new Usuario[usuarios.size()]);
	}

	
	
	
	
}
