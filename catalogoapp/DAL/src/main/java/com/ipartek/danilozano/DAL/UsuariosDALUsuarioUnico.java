package com.ipartek.danilozano.DAL;

import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

public class UsuariosDALUsuarioUnico implements TiendaDAL {

	private Usuario usuario;

	public void alta(Usuario usuario) {
		if (this.usuario.getNombre().equals(usuario.getNombre()))
			throw new DALException("Ya existe un usuario con ese nombre: " + usuario);

		this.usuario = usuario;
	}

	public boolean validar(Usuario usuario) {
		return this.usuario != null && this.usuario.equals(usuario);
	}

	public void modificar(Usuario usuario) {
		if (!this.usuario.getNombre().equals(usuario.getNombre()))
			throw new DALException("No se ha encontrado el usuario a modificar " + usuario);
		this.usuario = usuario;

	}

	public void borrar(Usuario usuario) {
		if (this.usuario.equals(usuario))
			this.usuario = null;
	}

	public Usuario buscarPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario[] buscarTodosLosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto[] buscarTodosLosProductos() {
		// TODO Auto-generated method stub
		return null;
	}

}