<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Rojae Blog</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/origoni-startbootstrap-clean-blog/1.0.3/css/clean-blog.min.css">
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
            <a class="navbar-brand" href="#">로재의 개발일기</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/blog/index.html">Home</a>
                </li>
                <li>
                    <a href="#about.html">최신글</a>
                </li>
                <li>
                    <a href="/post">인기글</a>
                </li>
                <li>
                    <a href="/post/write">글쓰기</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('https://raw.githubusercontent.com/rojae/startbootstrap-clean-blog/master/img/home-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h2>Rlog</h2>
                    <hr class="small">
                    <span class="subheading">SpringBoot Rojae Blog</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">

    <div class="social_login_form">

        <a href="javascript:;" class="btn_social" data-social="facebook">페이스북 로그인</a><br/>
        <a href="javascript:;" class="btn_social" data-social="google">구글 로그인</a><br/>
        <a href="javascript:;" class="btn_social" data-social="kakao">카카오톡 로그인</a><br/>
        <a href="javascript:;" class="btn_social" data-social="naver">네이버 로그인</a><br/>

    </div>



    <hr>

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <ul class="list-inline text-center">
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-muted">Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </div>
    </footer>

    <script>
        let socials = document.getElementsByClassName("btn_social");
        for(let social of socials) {
            social.addEventListener('click', function(){
                let socialType = this.getAttribute('data-social');
                location.href="/oauth2/authorization/" + socialType;
            })
        }
    </script>
    <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
    <script src="/webjars/origoni-startbootstrap-clean-blog/1.0.3/js/clean-blog.min.js"></script>
</body>
</html>


© 2020 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About
© 2020 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About
