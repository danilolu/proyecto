package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/noadmin/login")
public class NoadminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(NoadminLoginServlet.class);
	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_CATALOGO = "/WEB-INF/vistas/catalogo.jsp";
	public static final int TIEMPO_INACTIVIDAD = 30 * 60;
	static final String USUARIOS_DAL = "dal";
	static final String RUTA_FORMULARIO = "/WEB-INF/vistas/nuevousuario.jsp";
	static final String RUTA_LISTADO = "/WEB-INF/vistas/usuariocrud.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recoger datos del usuario en session
		String nombresesion = request.getParameter("nombre");
		HttpSession session = request.getSession();
		session.setAttribute("nombre", nombresesion);
		nombresesion = (String) session.getAttribute("nombre");
		// crear e iniciar variables
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		// cargar base de datos de productos cargada en context
		ServletContext application = request.getServletContext();
		TiendaDAL tiendaDAL = (TiendaDAL) application.getAttribute("dal");
		Producto[] productos = tiendaDAL.buscarTodosLosProductos();
		request.setAttribute("productos", productos);

		// establecer tienmpo de inactividad de sesion
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);
		// crear un objeto de usuario
		Usuario usuario = new Usuario(nombre, pass);
		// crear opciones de estado
		boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;
		boolean sinParametros = usuario.getNombre() == null;

		// Redirigir a una nueva vista
		if (esUsuarioYaRegistrado) {
			log.info("Usuario enviado a catalogo sin permisos de administracion  ");
			request.getRequestDispatcher(RUTA_CATALOGO).forward(request, response);

		} else if (sinParametros) {
			log.info(nombresesion + " pasa por sinparametros  ");
			request.getRequestDispatcher("/WEB-INF/vistas/nuevousuarioform.jsp").forward(request, response);
		} else {
			log.info("usuario: '" + nombre + "' pasa por no registrado");

		}
	}

}