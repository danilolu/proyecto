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
	<nav>
		<ul>
			
			<li><a href="productoform?op=alta">Alta productos</a></li>
			
			<li><a href="productocrud">Mantenimiento Productos</a></li>
	
			
			
			<li><a href="/login?opcion=logout">Salir</a></li>
			
		</ul>
	
		<ul>
			
			<li><a href="usuarioform?op=alta">Alta usuarios</a></li>
			
			<li><a href="usuariocrud">Mantenimiento usuarios</a></li>
		</ul>
	</nav>
	<h5 >Bienvenido ${usuario.nombre} </h5>