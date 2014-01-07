<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="carrinhoCompras">
    <head>
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <link href="./css/login.css" rel="stylesheet">
        <script src="./js/login-ctrls.js"></script>    
        <title>Login</title>
    </head>
    <body ng-controller="loginCtrl">
        <div class="container">
            <div class="loading" ng-show="loading"></div>
            <form class="form-signin">
                <div class="alert" ng-class="messageType" ng-show="showMessage">
                    {{message}}
                </div>
                <h2 class="form-signin-heading">Login</h2>
                <input ng-model="login.user" type="text" class="form-control" placeholder="Usuário" required="" autofocus="">
                <input ng-model="login.password" type="password" class="form-control" placeholder="Senha" required="">
                <a class="link" ng-click="cadUsuario()">Registrar-se</a>
                <button class="btn btn-lg btn-primary btn-block" ng-click="submit()" type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
