var app = angular.module('carrinhoApp', ['ngResource']);

function comprasCtrl($scope, $resource) {
    $scope.showMessage = false;
    var iListas = $('#iLista').attr('value');
    if (iListas !== undefined && iListas.length > 0) {
        $scope.playLista = null;
        $scope.loading = true;
        $resource("compras/creat?data=:data").get({data: iListas}, function(response) {
            $scope.loading = false;
            $scope.lista = response.data;
            $scope.lista.total = 0;
            for (var i in $scope.lista.listasItensCollection) {
                $scope.lista.listasItensCollection[i].quantidade = 0;
            }
            addMessage($scope, response);
        }, function(response) {
            $scope.loading = false;
            processError($scope, response);
        });
    }
    $scope.new = function() {
        $scope.showMessage = false;
        if ($scope.lista !== undefined && $scope.lista.iLista !== undefined) {
            $scope.loading = true;
            $resource("compras/creat?data=:data").get({data: $scope.lista.iLista}, function(response) {
                $scope.loading = false;
                $scope.lista = response.data;
                $scope.lista.total = 0;
                for (var i in $scope.lista.listasItensCollection) {
                    $scope.lista.listasItensCollection[i].quantidade = 0;
                }
                addMessage($scope, response);
            }, function(response) {
                $scope.loading = false;
                processError($scope, response);
            });
        }
    };
    $scope.add = function(item) {
        $scope.showMessage = false;
        item.quantidade++;
        $scope.lista.total += item.valor;
    };
    $scope.remove = function(item) {
        $scope.showMessage = false;
        if (item.quantidade > 0) {
            item.quantidade--;
            $scope.lista.total -= item.valor;
        }
    };
}

function processError($scope, response) {
    if (response.status === 401) {
        $scope.showMessage = true;
        $scope.messageType = "alert-danger";
        $scope.message = "Sua sessão expirou";
        setTimeout(function() {
            document.location.href = 'login.jsp';
        }, 1500);
    } else {
        $('html').html(response.data);
    }
}

function addMessage($scope, response) {
    if (response.message !== undefined) {
        if (response.status === "success") {
            $scope.showMessage = true;
            $scope.messageType = "alert-success";
            $scope.message = response.message;
            setTimeout(function() {
                $('#message').addClass("ng-hide");
                $scope.showMessage = false;
            }, 3000);
        } else if (response.status === "erros") {
            $scope.showMessage = true;
            $scope.messageType = "alert-danger";
            $scope.message = response.message;
        }
    }
}