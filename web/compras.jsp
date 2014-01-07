<%-- 
    Document   : cad_compras
    Author     : R3oLoN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="carrinhoApp">
    <head>
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <script src="./js/compras-ctrls.js"></script>
        <link href="./css/style.css" rel="stylesheet">
        <title>Compras</title>
    </head>
    <body>        
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <div class="container content" ng-controller="comprasCtrl">
            <div class="loading" ng-show="loading"></div>
            <input style="display: none;" id="iLista" ng-change="" ng-model="playLista" value="${data}">
            <div class="alert" ng-class="messageType" ng-show="showMessage" id="message">
                {{message}}
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Compras</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="form-group col-sm-8">
                            <label for="nomeItem">Lista</label>
                            <input ng-model="lista.descricao" type="text" class="form-control" id="nomeItem" placeholder="Nome" disabled="true">
                        </div>
                        <div class="form-group col-sm-4">
                            <label for="total">Total</label>
                            <div class="input-group">
                                <span class="input-group-addon">R$</span>
                                <input ng-model="lista.total" type="number" class="form-control" id="total" disabled="true">
                            </div>
                        </div>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item" ng-repeat="item in lista.listasItensCollection">
                            <div class="rowItem">                          
                                <div class="form-group col-sm-3 col-md-4 not-padding-left col-xs-not-padding">
                                    <label for="nomeItem">Item</label>
                                    <input ng-model="item.descricao" type="text" class="form-control" id="nomeItem" placeholder="Nome" disabled="true">
                                </div>
                                <div class="form-group col-sm-4 col-md-3 col-xs-7 not-padding xs-not-padding-left">
                                    <label for="valor">Valor</label>
                                    <div class="input-group">
                                        <span class="input-group-addon">R$</span>
                                        <input ng-model="item.valor" type="number" class="form-control" id="valor" placeholder="Valor" disabled="true">
                                    </div>
                                </div>
                                <div class="form-group col-sm-2 col-md-3 col-xs-5 not-padding-right col-xs-not-padding">
                                    <label for="unidade">Unidade</label>
                                    <input ng-model="item.unidade" type="text" class="form-control" id="unidade" placeholder="Unidade" disabled="true">
                                </div>
                                <div class="form-group col-sm-3 col-md-2 not-padding-right col-xs-not-padding">
                                    <label for="quantidade">Quantidade</label>
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <button ng-click="add(item)" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></button>
                                        </div>
                                        <input ng-model="item.quantidade" type="text" id="quantidade" class="form-control" disabled="true">
                                        <div class="input-group-btn">
                                            <button ng-click="remove(item)" class="btn btn-default"><span class="glyphicon glyphicon-minus"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="btn-group">
                        <button ng-click="new()" class="btn btn-default" title="Criar um novo registro">Novo</button>
                        <a href="main" class="btn btn-default" title="Voltar à página inicial">Voltar</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
