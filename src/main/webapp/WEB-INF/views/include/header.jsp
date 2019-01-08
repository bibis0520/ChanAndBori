<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chan & Bori's Lifelog</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- Font Awesome CDN -->
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
    <!-- style.css -->
    <link rel="stylesheet" href="../../../../resources/style.css">
	<!-- jQuery CDN -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <!-- bootstrap CDN -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<!-- common.js -->
    <script type="text/javascript" src="../../../../resources/common/common.js" ></script>
    <!-- favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="../../../resources/favicon.ico" />
    <!-- NaverSmartEditer -->
	<script type="text/javascript" src="../../../resources/plugins/naverSmartEditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<!-- naverSmartEditor.js -->
	<script type="text/javascript" src="../../../resources/naverSmartEditor.js"></script>
</head>

<body>
    <div class="wrapper">

        <div class="header">

            <div class="title">
				<div class="tr">
                    <h1>Chan & Bori's Lifelog</h1>
					<div class="icons">
						<a href="https://www.youtube.com/channel/UCkskqwtod2vp2UzgBs_aQIg?view_as=subscriber" target="_blank">
							<i class="fab fa-youtube icon"></i>
						</a>
						<a href="https://github.com/bibis0520" target="_blank">
							<i class="fab fa-github icon"></i>
						</a>
					</div>
				</div>
            </div><!-- /title -->

            <div class="navigation">
                <ul class="fl">
                    <li><a href="/"><i class="fas fa-home"></i></a></li>
                    <li><a href="/board/listPage"><i class="fas fa-clipboard-list"></i></a></li>
                    <li><a href="/photo"><i class="fas fa-camera-retro"></i></a></li>
                </ul>

                <ul class="fr">
                    <li><a href="/login"><i class="fas fa-sign-in-alt"></i></a></li>
                    <!-- <li><a href="/signup"><i class="fas fa-user-plus"></i></a></li> -->
                </ul>
            </div><!-- /navigation -->

        </div>