/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('carrinhoCompras', ['ngResource']);

function mainCtrl($scope, $resource) {
    $resource("main/list").get(function(response) {
        if (response.status === "success") {
            $scope.list = response.data;
        } else if (response.status === "error") {
            $scope.showMessage = true;
            $scope.messageType = "alert-danger";
            $scope.message = response.message;
        }
    },function (response){
        processError($scope,response);
    });
    $scope.clickItem = function(item) {
        $resource('main/item?data=:data').get({data: item});
    };
}

function processError($scope,response) {
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


