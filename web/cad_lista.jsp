<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="carrinhoApp">
    <head>
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <script src="./js/cad-lista-ctrls.js"></script> 
        <link href="./css/style.css" rel="stylesheet">       
        <title>Cadastro de usuários</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <div class="container content" ng-controller="cadListaCtrl">
            <div class="loading" ng-show="loading"></div>
            <form role="form">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Cadastro de listas</h3>
                    </div>
                    <div class="panel-body">
                        <%@include file="WEB-INF/jspf/navigation-bar.jspf" %>
                        <div class="alert" ng-class="messageType" ng-show="showMessage" id="message">
                            {{message}}
                        </div>
                        <div class="form-group">
                            <label for="nome">Código</label>
                            <input ng-model="lista.iLista" type="number" class="form-control" id="nome" placeholder="Código" disabled="true">
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input ng-model="lista.descricao" type="text" class="form-control" id="nome" placeholder="Nome" required="" ng-change="change()">
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="btn-group">
                                    <a class="btn btn-default" ng-click="addItem()" ng-disabled="lista == undefined" title="Adicionar item"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;&nbsp;Adicionar</a>
                                </div>
                            </div>
                            <div class="panel-body">
                                <ul class="lista">
                                    <li class="listaItem" ng-repeat="item in lista.listasItensCollection">
                                        <a class="link excludeItem" ng-click="removeItem(item)" title="Excluir item" style="color: #d43f3a"><span class="glyphicon glyphicon-remove"></span></a>
                                        <div class="form-group">
                                            <label for="nomeItem">Nome</label>
                                            <input ng-model="item.descricao" type="text" class="form-control" id="nomeItem" placeholder="Nome">
                                        </div>
                                        <div class="rowItem">
                                            <div class="form-group col-md-6 col-sm-6 not-padding-left col-xs-not-padding">
                                                <label for="valor">Valor</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">R$</span>
                                                    <input ng-model="item.valor" type="number" class="form-control" id="valor" placeholder="Valor">
                                                </div>
                                            </div>
                                            <div class="form-group col-md-6 col-sm-6 not-padding-right col-xs-not-padding">
                                                <label for="unidade">Unidade</label>
                                                <input ng-model="item.unidade" type="text" class="form-control" id="unidade" placeholder="Unidade">
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="btn-group">
                            <button ng-click="new()" type="reset" class="btn btn-default" title="Criar um novo registro">Novo</button>
                            <button ng-click="create()" class="btn btn-default" title="Salvar o registro">Salvar</button>
                            <button ng-click="exclude()" class="btn btn-default" title="Excluir o registro">Excluir</button>
                            <a href="main" class="btn btn-default" title="Voltar à página inicial">Voltar</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>       
    </body>
</html>
