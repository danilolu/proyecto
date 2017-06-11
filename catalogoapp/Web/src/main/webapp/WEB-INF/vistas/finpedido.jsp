<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
	<ul>
	
	<li><a href="/carrito">Volver al catalogo</a></li>
	<li><a href="?op=vaciar">Vaciar carrito</a></li>
	</ul>
	</ul>
</nav>
	<h2>Finalizar pedido</h2>


<table>
	<thead>
		<tr>
			
			<th>Nombre producto</th>
			<th>Descripción</th>
			<th>Imagen</th>
			<th>Precio</th>
			<th>Quitar de carrito</th>
			
			</tr>
	</thead>
	<tbody>
		<c:forEach items="${sessionScope.productosArr}" var="producto">
			<tr>
				<td>${producto.nombre}</td>
				<td>${producto.descripcion}</td>
				<td><IMG SRC="/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.precio} &euro;</td>
				<td><a href="?op=quitar&id=${producto.id}"><img src="/img/quitarcarrito.png" width="100" height="100px" ></a></td>
			</tr>
		</c:forEach>
		<table>
	<thead>
		<tr>
			
			<th>Numero de productos ${sessionScope.numeroProductos}</th>
			<th>Total a pagar:${sessionScope.precioTotal} &euro;</a></th>
			<th><a href="pagado.jsp" target="_blank" onclick="window.open(this.href, this.target, 'width=600,height=200'); return false;">Pagar</a> </a></th>
	</tbody>
</table>



					

<%@ include file="includes/pie.jsp"%>
