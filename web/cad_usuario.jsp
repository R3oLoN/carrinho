<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="carrinhoCompras">
    <head>
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <script src="./js/cad-usuarios-ctrls.js"></script>   
        <link href="./css/style.css" rel="stylesheet">  
        <title>Cadastro de usuários</title>
    </head>
    <body ng-controller="cadUsuarioCtrl">
        <div class="loading" ng-show="loading"></div>
        <div class="container">
            <div class="panel panel-default" style="margin-top: 5%;">
                <div class="panel-heading">
                    <h3 class="panel-title">Cadastro de usuários</h3>
                </div>
                <div class="panel-body">
                    <form class="form-signin col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                        <div class="alert" ng-class="messageType" ng-show="showMessage">
                            {{message}}
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input ng-model="user.nome" type="text" class="form-control" id="nome" placeholder="Nome" required="">
                        </div>
                        <div class="form-group">
                            <label for="nome">Usuário</label>
                            <input ng-model="user.login" type="text" class="form-control" id="login" placeholder="Usuário" required="">
                        </div>
                        <div class="form-group">
                            <label for="nome">Senha</label>
                            <input ng-model="user.senha" type="password" class="form-control" id="password" placeholder="Senha" required="">
                        </div>
                        <div class="form-group">
                            <label for="nome">Confirmar senha</label>
                            <input ng-model="passwordConfirm" type="password" class="form-control" id="password-confirm" placeholder="Confirmar senha" required="">
                        </div>
                        <div class="btn-group">
                            <button ng-click="create()" class="btn btn-primary">Criar</button>
                            <a href="login" class="btn btn-default">Voltar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
