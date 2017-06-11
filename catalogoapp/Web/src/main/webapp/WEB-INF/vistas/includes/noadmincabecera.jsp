<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>tienda</title>
<link rel="stylesheet" href="/css/estilo.css" />
<script src="js/funciones.js"></script>
</head>
<body>
	<header>
		<h1><IMG SRC="/img/danilogo.png" width="500" height="100px" /></h1>
			</header>
	<h4>Bienvenido ${usuario.nombre} </h4>
	<nav>
		
			<li><a href="/login?opcion=logout">Salir</a></li>
			
		</ul>
	</nav>
