/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('carrinhoCompras', ['ngResource']);

function cadUsuarioCtrl($scope, $resource) {
    $scope.showMessage = false;
    $scope.create = function() {
        $scope.loading=true;
        var user = $scope.user;
        var passwordConfirm = $scope.passwordConfirm;
            if (user.senha === passwordConfirm) {
            $resource("cad_usuario/create?data=:data")
                    .get({data: JSON.stringify(user)}, function(response) {
                        if (response.status === "success") {
                            $scope.showMessage = true;
                            $scope.messageType = "alert-success";
                            $scope.message = response.message;
                            setTimeout(function(){
                                document.location.href = 'login';
                            },2000);
                        } else if (response.status === "error") {
                            $scope.loading=false;
                            $scope.showMessage = true;
                            $scope.messageType = "alert-danger";
                            $scope.message = response.message;
                        }
                    });
        } else {
            $scope.showMessage = true;
            $scope.messageType = "alert-danger";
            $scope.message = "A senha e a confirmação da senha devem ser iguais!";
        }
    };
}
