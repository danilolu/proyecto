<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="css/estilo.css" />
<script src="js/funciones.js"></script>
</head>
<body>
	
	<h1 style="text-align:center">Bienvenido a Cervezas <IMG SRC="/img/danilogo.png" width="200" height="100px" /></h1>
	
	<A href="/login"><p style="text-align:center">
<IMG  src="/img/index.png" ></a>
	
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.danilozano.Tipos.Usuario" />

	
	
	<footer>
		<p>&copy;2017 Dani Lozano</p>
	</footer>
</body>
</html>