/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('carrinhoCompras', ['ngResource']);

function loginCtrl($scope, $resource) {
    $scope.submit = function() {
        $scope.loading = true;
        var login = $scope.login;
        $resource('login/access?data=:data').get({data: JSON.stringify(login)}, function(response) {
            if (response.status === "success") {
                $scope.showMessage = true;
                $scope.messageType = "alert-success";
                $scope.message = response.message;
                setTimeout(function() {
                    document.location.href = 'main';
                }, 1500);
            } else if (response.status === "error") {
                $scope.loading = false;
                $scope.showMessage = true;
                $scope.messageType = "alert-danger";
                $scope.message = response.message;
            }
        });
    };
    $scope.cadUsuario = function (){
        document.location.href = 'cad_usuario';
    };
}



