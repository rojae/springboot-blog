<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/webjars/origoni-startbootstrap-clean-blog/1.0.3/css/clean-blog.min.css">
    <title>Login</title>
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/post/list">SpringBlog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/post/write">Write Post</a>
                </li>
                <li>
                    <a href="/connect">Connections</a>
                </li>
                <li>
                    <a href="http://millky.com/@origoni/folder/30/post/list">자바 블로그 개발하기</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<header class="intro-header" style="background-image: url('https://cdn.pixabay.com/photo/2019/09/17/18/48/computer-4484282_960_720.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="page-heading">
                    <h1>Login</h1>
                    <hr class="small">
                    <span class="subheading">Connect to Facebook</span>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <h3>Connect to Facebook API v2.3</h3>

    <!-- appId -->
    <c:set var="client_id" value="305102013820244" />
    <!-- redirct url -->
    <c:set var="redirect_url" value="https://localhost:8443/post/list" />
    <!-- serect -->
    <c:set var="secret_code" value="" />

    <form action="https://www.facebook.com/v7.0/dialog/oauth?app_id=${client_id}&redirect_uri=${redirect_url}&&client_secret=${secret_code}&response_type=token" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}"></input>
        <div class="formInfo">
            <p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
        </div>
        <p>
            <button type="submit">Connect to Facebook</button>
        </p>
    </form>
        <div class="fb-login-button" data-size="large" data-button-type="login_with" data-layout="default" data-auto-logout-link="false" data-use-continue-as="false" data-width=""></div>
</div>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <ul class="list-inline text-center">
                    <li>
                        <a href="https://www.facebook.com/millkyc">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                        </a>
                    </li>
                    <li>
                        <a href="https://github.com/origoni">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                        </a>
                    </li>
                    <li>
                        <a href="https://twitter.com/origoni">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">Powered By <a href="https://redcoder.tistory.com/">rojae</a> | Skin designed by <a href="http://startbootstrap.com/template-overviews/clean-blog/">Start Bootstrap</a></p>
            </div>
        </div>
    </div>
</footer>

<!-- facebook -->
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v7.0&appId=313124872955260&autoLogAppEvents=1"></script>

<script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script></body>
</html>