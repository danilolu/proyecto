package com.ipartek.danilozano.DAL;

public class TiendaDALFactory {
	
	
	//Dal factory de productos
	public static TiendaDAL getProductosDAL() {

		return new TiendaDALColeccion();
	}
	//Dal factory de Usuarios
	public static TiendaDAL getUsuariosDAL() {

		return new TiendaDALColeccion();
	}
}
