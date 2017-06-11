<%@ include file="includes/noadmincabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<nav>
	<ul>
	<li> ${sessionScope.numeroProductos} producto/s en el carrito</li>
	<li><a href="/finpedido">Finalizar pedido</a></li>
	<li><a href="/finpedido?op=vaciar">Vaciar carrito</a></li>
	</ul>
</nav>
<h2>Catalogo</h2>
<table border="1" style="margin: 0 auto;
   
">
	<thead>
		<tr>
			
			<th>ID</th>
			<th>Nombre</th>
			<th>Imagen</th>
			<th>Descripcion</th>
			<th>Precio</th>
			<th>Meter al carrito</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.productos}" var="producto">
			<tr>
				
				<td>${producto.id}</td>
				<td>${producto.nombre}</td>
				<td><IMG SRC="/img/${producto.nombre}.jpg" width="100" height="100px" /></td>
				<td>${producto.descripcion}</td>
				<td>${producto.precio} &euro;/Kg</td>
				<td><a href="/carrito?op=anadir&id=${producto.id}"><img src="/img/anadir.png" width="100" height="100px" ></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<nav>
	<ul>
	<li>${sessionScope.numeroProductos} productos en el carrito</li>
	<li><a href="/finpedido">Finalizar pedido</a></li>
	<li><a href="/finpedido?op=vaciar">Vaciar carrito</a></li>
	</ul>
</nav>
			
			
<%@ include file="includes/pie.jsp"%>
