package com.ipartek.danilozano.Web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.danilozano.DAL.TiendaDAL;
import com.ipartek.danilozano.DAL.TiendaDALFactory;
import com.ipartek.danilozano.Tipos.Producto;
import com.ipartek.danilozano.Tipos.Usuario;

@WebListener
public class ListenerUsuario implements ServletContextListener, HttpSessionListener {
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sc) {

		// cargar la configuracion de log4j en el context
		PropertyConfigurator.configure(ListenerUsuario.class.getClassLoader().getResource("log4j.properties"));
		// cargar usuarios y productos en el context
		log.info("productos y usuarios creados ");
		ServletContext application = sc.getServletContext();
		TiendaDAL tiendaDAL = (TiendaDAL) application.getAttribute("dal");

		tiendaDAL = TiendaDALFactory.getProductosDAL();
		tiendaDAL = TiendaDALFactory.getUsuariosDAL();
		tiendaDAL.alta(new Producto("Judas", "Ale", 1.8));
		tiendaDAL.alta(new Producto("Affligem Triple", "Abadia", 1.95));
		tiendaDAL.alta(new Producto("Paulaner", "Trigo", 1.6));
		tiendaDAL.alta(new Producto("Guinnes", "Negra", 1.59));
		tiendaDAL.alta(new Producto("Murphys", "Roja", 1.53));
		tiendaDAL.alta(new Producto("Chimay roja", "Abadia", 2.1));
		tiendaDAL.alta(new Producto("Super Bock", "Lager ", 0.80));
		tiendaDAL.alta(new Usuario("admin", "pass"));
		tiendaDAL.alta(new Usuario("usuario1", "pass1"));
		application.setAttribute("dal", tiendaDAL);

	}

	private ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

}