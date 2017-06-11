package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.danilozano.Tipos.Carrito;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/finpedido")
public class FinPedidoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(Carrito.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String op = request.getParameter("op");
		Carrito carrito = (Carrito) session.getAttribute("carrito");

		Producto producto;
		Producto[] carritoArr = null;
		Integer numeroProductos = 0;
		Double precioTotal = 0.0;

		try {
			carritoArr = carrito.buscarTodosLosProductos();
			numeroProductos = carritoArr.length;
			precioTotal = carrito.precioTotal();

		} catch (NullPointerException npe) {
			request.getRequestDispatcher("/login");
		}

		session.setAttribute("productosArr", carritoArr);

		session.setAttribute("numeroProductos", numeroProductos);

		session.setAttribute("precioTotal", precioTotal);

		if (op == null) {

			try {
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
			} catch (NullPointerException npe) {
				carrito = new Carrito();
				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
			}

			request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);

		} else {

			switch (op) {
			case "vaciar":
				log.info("Pedido pagado, Carrito reseteadoo");
				carrito = new Carrito();
				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
				request.getRequestDispatcher("/carrito").forward(request, response);
				break;
			case "quitar":
				log.info("Producto  quitado del carrito");
				int id = Integer.parseInt(request.getParameter("id"));
				producto = carrito.buscarPorId(id);
				carrito.quitarDelCarrito(id);
				session.setAttribute("carrito", carrito);
				session.setAttribute("productosArr", carrito.buscarTodosLosProductos());
				session.setAttribute("numeroProductos", carrito.buscarTodosLosProductos().length);
				session.setAttribute("precioTotal", carrito.precioTotal());
			default:
				request.getRequestDispatcher("/WEB-INF/vistas/finpedido.jsp").forward(request, response);
			}

		}

	}

}