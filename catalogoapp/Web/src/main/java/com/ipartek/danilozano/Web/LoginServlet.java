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
import com.ipartek.danilozano.DAL.TiendaDALFactory;
import com.ipartek.danilozano.Tipos.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	/* package */static final String RUTA = "/WEB-INF/vistas/";
	private static final String RUTA_PRINCIPAL = "/admin/productocrud";
	private static final String RUTA_LOGIN = RUTA + "/login.jsp";
	static final String RUTA_SERVLET_LISTADO = "/login";
	public static final int TIEMPO_INACTIVIDAD = 30 * 60;

	/* package */static final int MINIMO_CARACTERES = 4;
	static final String USUARIOS_DAL = "dal";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recoger datos del usuario en session
		String nombresesion = request.getParameter("nombre");
		HttpSession session = request.getSession();
		session.setAttribute("nombre", nombresesion);
		nombresesion = (String) session.getAttribute("nombre");

		// Recoger datos de vistas
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		String opcion = request.getParameter("opcion");

		// Crear modelos en base a los datos
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPass(pass);

		// Llamada a lógica de negocio
		ServletContext application = request.getServletContext();
		TiendaDAL usuariosDAL = (TiendaDAL) application.getAttribute(USUARIOS_DAL);

		if (usuariosDAL == null) {
			usuariosDAL = TiendaDALFactory.getUsuariosDAL();
		}

		// establecer tienmpo de inactividad de sesion
		session.setMaxInactiveInterval(TIEMPO_INACTIVIDAD);
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(TIEMPO_INACTIVIDAD);
		response.addCookie(cookie);

		// crear opciones de estado
		boolean esValido = usuariosDAL.validar(usuario);
		boolean sinParametros = usuario.getNombre() == null;
		boolean esUsuarioYaRegistrado = session.getAttribute("usuario") != null;
		boolean quiereSalir = "logout".equals(opcion);
		boolean nombreValido = usuario.getNombre() != null && usuario.getNombre().length() >= MINIMO_CARACTERES;
		boolean passValido = !(usuario.getPass() == null || usuario.getPass().length() < MINIMO_CARACTERES);

		// Redirigir a una nueva vista
		if (quiereSalir) {
			session.invalidate();// para hacer el logout
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
			log.info(" sesion finalizada");

		} else if (esUsuarioYaRegistrado) {
			log.info(nombresesion + " pasa por es usuarioregistrado  ");
			request.getRequestDispatcher(RUTA_PRINCIPAL).forward(request, response);
		} else if (sinParametros) {
			log.info(nombresesion + " pasa por sinparametros  ");
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (!nombreValido || !passValido) {
			log.info(nombresesion + " pasa por nombre o pass no valido  ");
			usuario.setErrores("El nombre y la pass deben tener como mínimo " + MINIMO_CARACTERES + " caracteres y son ambos requeridos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);
		} else if (esValido) {
			log.info(nombresesion + " ha iniciado sesion ");
			session.setAttribute("usuario", usuario);
			response.sendRedirect(RUTA_PRINCIPAL);
		} else {
			log.info("inicio de sesion erronea");
			usuario.setErrores("El usuario y contraseña introducidos no son válidos");
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher(RUTA_LOGIN).forward(request, response);

		}
	}
}
