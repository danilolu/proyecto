package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.UsuarioYaExistenteDALException;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/noadmin/usuarioform")
public class noadminUsuarioFormServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(ProductoFormServlet.class);
	static final String RUTA_NUEVO_FORMULARIO = "/WEB-INF/vistas/nuevousuarioform.jsp";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger datos
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession();

		// definir ruteo
		RequestDispatcher rutaListado = request.getRequestDispatcher(UsuarioCRUDServlet.RUTA_SERVLET_LISTADO);
		RequestDispatcher rutaFormulario = request.getRequestDispatcher(RUTA_NUEVO_FORMULARIO);

		// recojer la opcion que se carga en la url
		String op = request.getParameter("opform");

		// variables del objeto Usuario
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		// recoger datos de la TiendaDAL cargada en contex
		ServletContext application = request.getServletContext();
		TiendaDAL dal = (TiendaDAL) application.getAttribute("dal");

		// crear objeto Usuario
		Usuario usuario = new Usuario(nombre, pass);

		// actuar en consecuencia de la opcion recogida anteriormente

		if (op == null) {
			rutaListado.forward(request, response);
			return;
		}
		switch (op) {
		case "alta":

			if ((nombre == null || nombre == "") || (pass == null || pass == "") || (pass2 == null || pass2 == "")) {
				log.info("alta de usuario con id '" + nombre + "' erronea");

				usuario.setErrores("- Todos campos deben estar rellenados ");

				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			} else if (pass.equals(pass2)) {
				log.info("usuario: '" + nombre + "' dado de alta por usuario no admin");

				try {
					log.info("producto con id '" + nombre + "' dado de alta");
					dal.alta(usuario);
					session.invalidate();
					response.sendRedirect("/login");
				} catch (UsuarioYaExistenteDALException a) {
					log.info("usuario con  '" + nombre + "' repetido, el alta no ha sido finalizada");
					usuario.setErrores("ID ya existente");
					request.setAttribute("usuario", usuario);
					rutaFormulario.forward(request, response);
				}

			} else {
				log.info("error al crear el usuario: '" + nombre + "' , las contraseñas no coinciden");

				usuario.setErrores("Las contraseñas no coinciden");
				request.setAttribute("usuario", usuario);
				rutaFormulario.forward(request, response);
			}
		}

	}
}