<%@ include file="includes/cabecera.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<h2>${fn:toUpperCase(param.op)} PRODUCTOS </h2>
	
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.danilozano.Tipos.Producto" />

	<form action="productoform" method="post">
		<fieldset>
			<label for="id">ID</label> 
			<input id="id"	name="id" required="required"  value="${producto.id}"
			  	Style=background-color:lightgray; readonly="readonly" 
			  		   />
			
		
		</fieldset>
		<fieldset>
			<label for="nombre">Nombre</label>
			 <input id="nombre" name="nombre"	/>
		</fieldset>
		<fieldset>
			<label for="descripcion">Descripcion</label> <input type="descripcion" id="descripcion"
				name="descripcion" >
		</fieldset>
		<fieldset>
			<label for="precio">Precio</label> <input type="number" step="any" id="precio"
				name="precio" />
		</fieldset>
		<fieldset>
				<input type="submit" value="${fn:toUpperCase(param.op)}" 
				<c:if test="${param.op == null or param.op == ''}">
			  	Style="display:none;"
			  </c:if>  
				
				/>
			<p class="errores" >${producto.errores}</p>
			
			<input type="hidden" name="opform" value="${param.op}" />
		</fieldset>
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		
	</c:if>
	
<%@ include file="includes/pie.jsp" %>>