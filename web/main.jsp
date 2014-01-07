<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="carrinhoCompras">
    <head>
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <script src="./js/main-ctrls.js"></script>
        <link href="./css/style.css" rel="stylesheet">
        <title>Carrinho</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <div ng-controller="mainCtrl" class="container content">
            <div class="alert" ng-class="messageType" ng-show="showMessage">
                {{message}}
            </div>
            <ul class="list-group">
                <li ng-repeat="item in list" class="list-group-item">
                    {{item.descricao}}
                    <a href="compras?data={{item.iLista}}" style="float: right;" class="link glyphicon glyphicon-play"></a>
                </li>
            </ul>
        </div>
    </body>
</html>
