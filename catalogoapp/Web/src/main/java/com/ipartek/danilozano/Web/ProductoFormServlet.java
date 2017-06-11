package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.DALException;
import com.ipartek.danilozano.DAL.IdProductoYaExistenteDALException;
import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/admin/productoform")
public class ProductoFormServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// definir ruteo
		RequestDispatcher rutaListado = request
				.getRequestDispatcher(ProductoCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request
				.getRequestDispatcher(ProductoCRUDServlet.RUTA_FORMULARIO);
		// recojer la opcion que se carga en la url
		String op = request.getParameter("opform");
		// variables del objeto Producto
		int id;
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		double precio;
		// recoger valores para inicializar variables
		if (request.getParameter("id") == null
				|| request.getParameter("id") == "") {
			id = 0;
		} else {
			id = Integer.parseInt(request.getParameter("id"));// pasar de String
																// a int
		}
		if (request.getParameter("precio") == null
				|| request.getParameter("precio") == "") {
			precio = 0;
		} else {
			precio = Double.parseDouble(request.getParameter("precio"));// pasar
																		// de
																		// String
																		// a
																		// double

		}
		// recoger datos de la TiendaDAL cargada en contex
		ServletContext application = getServletContext();
		TiendaDAL tiendaDAL = (TiendaDAL) application.getAttribute("dal");

		// crear objeto Pproducto
		Producto producto = new Producto(nombre, descripcion, precio);
		producto.setId(id);

		// actuar en consecuencia de la opcion recogida anteriormente
		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}

		switch (op) {
		case "alta":

			if (id == 0 || (nombre == null || nombre == "")
					|| (descripcion == null || descripcion == "")
					|| precio == 0) {
				log.info("alta de producto con id '" + id + "' erronea");

				producto.setErrores("- Los campos deben estar rellenados </br> - ID y precio deben ser numericos y no tener valor 0");

				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else {
				try {
					log.info("producto con id '" + id + "' dado de alta");
					tiendaDAL.alta(producto);
					rutaListado.forward(request, response);
				} catch (IdProductoYaExistenteDALException a) {
					log.info("producto con id '" + id + "' repetida, el alta no ha sido finalizada");
					producto.setErrores("ID ya existente");
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);

				}
			}

			break;
		case "modificar":
			if (id == 0 || (nombre == null || nombre == "")
					|| (descripcion == null || descripcion == "")
					|| precio == 0) {
				log.info("modificacion de producto con id '" + id + "' erronea");
				producto.setErrores("Los campos deben estar rellenados y no deben tener valor 0");
				request.setAttribute("producto", producto);
				rutaFormulario.forward(request, response);

			} else
				try {
					log.info("producto con id '" + id + "' modificado");
					tiendaDAL.modificar(producto);
				} catch (DALException de) {
					log.info("Error al modificar producto con id '" + id + "', la modificacion no ha sido finalizada");

					producto.setErrores(de.getMessage());
					request.setAttribute("producto", producto);
					rutaFormulario.forward(request, response);
					return;
				}
			rutaListado.forward(request, response);

			break;
		case "borrar":
			tiendaDAL.borrar(producto);
			rutaListado.forward(request, response);
			log.info("producto con id '" + id + "' borrado");
			break;
		}
	}

}
