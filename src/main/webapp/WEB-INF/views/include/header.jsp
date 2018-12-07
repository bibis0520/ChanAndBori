<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    

<!DOCTYPE html>
<html>
<head>
	<title>Chan & Bori's Lifelog</title>
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
    <link rel="stylesheet" href="../../resources/style.css">
</head>

<body>
	<header>
        <h1>Chan & Bori's Lifelog</h1>
        <div class="icons">
            <a href="https://www.youtube.com/channel/UCkskqwtod2vp2UzgBs_aQIg?view_as=subscriber" target="_blank">
                <i class="fab fa-youtube icon"></i>
            </a>
            <a href="https://github.com/bibis0520" target="_blank">
                <i class="fab fa-github icon"></i>
            </a>
        </div>
    </header>
    
     <nav>
        <ul class="fl">
            <li><a href="/">Home</a></li>
            <li><a href="/board">Board</a></li>
            <li><a href="/photo">Photo</a></li>
        </ul>

        <ul class="fr">
            <li><a href="/login"><i class="fas fa-sign-in-alt"></i></a></li>
            <li><a href="/signup"><i class="fas fa-user-plus"></i></a></li>
        </ul>
    </nav>