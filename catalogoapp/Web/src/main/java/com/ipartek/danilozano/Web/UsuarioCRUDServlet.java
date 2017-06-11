package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/admin/usuariocrud")
public class UsuarioCRUDServlet extends HttpServlet {
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/usuarioform.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";
	static final String RUTA_SERVLET_LISTADO = "/admin/usuariocrud";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recoger datos de la TiendaDAL cargada en contex

		ServletContext application = request.getServletContext();
		TiendaDAL dal = (TiendaDAL) application.getAttribute("dal");

		// recojer la opcion que se carga en la url

		String op = request.getParameter("op");

		// actuar en consecuencia de la opcion
		{
			if (op == null) {// si op el null se cargan los productos
				// si op el null se cargan los productos
				Usuario[] usuarios = dal.buscarTodosLosUsuarios();

				request.setAttribute("usuarios", usuarios);

				request.getRequestDispatcher(RUTA_LISTADO).forward(request,
						response);
			} else {
				String id = request.getParameter("id");

				Usuario usuario;

				switch (op) {
				case "modificar":
				case "borrar":
					usuario = dal.buscarPorId(id);
					request.setAttribute("usuario", usuario);
				case "alta":
					request.getRequestDispatcher(RUTA_FORMULARIO).forward(
							request, response);
					break;
				default:
					request.getRequestDispatcher(RUTA_LISTADO).forward(request,
							response);
				}
			}
		}

	}
}
