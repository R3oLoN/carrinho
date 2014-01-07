/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('carrinhoApp', ['ngResource']);

function cadListaCtrl($scope, $resource) {
    $scope.showMessage = false;
    $scope.isNew = false;
    $scope.isRetrive = false;
    $scope.create = function() {
        $scope.showMessage = false;
        if ($scope.isNew || ($scope.lista !== undefined && $scope.lista.iLista !== undefined)) {
            $scope.loading = true;
            var lista = $scope.lista;
            var Resource;
            if (lista.iLista === undefined) {
                Resource = $resource("cad_lista/create?data=:data");
            } else if (lista.iLista !== undefined) {
                Resource = $resource("cad_lista/update?data=:data");
            }
            Resource.get({data: JSON.stringify(lista)}, function(response) {
                $scope.loading = false;
                if (response.status === "success") {
                    $scope.showMessage = true;
                    $scope.messageType = "alert-success";
                    $scope.message = response.message;
                    $scope.lista = response.data;
                    setTimeout(function() {
                        $('#message').addClass("ng-hide");
                        $scope.showMessage = false;
                    }, 3000);
                } else if (response.status === "error") {
                    $scope.showMessage = true;
                    $scope.messageType = "alert-danger";
                    $scope.message = response.message;
                }
            }, function(response) {
                processError($scope, response);
            });
        } else {
            $scope.showMessage = true;
            $scope.messageType = "alert-warning";
            $scope.message = "Não está sendo editado e nem inserido um novo dado.";
        }
    };
    $scope.new = function() {
        $scope.showMessage = false;
        $scope.lista = null;
        $scope.isNew = true;
    };
    $scope.change = function() {
        $scope.showMessage = false;
        if (!$scope.isNew && !$scope.isRetrive) {
            $scope.showMessage = true;
            $scope.messageType = "alert-warning";
            $scope.message = "Não está sendo editado e nem inserido um novo dado.";
        }
    };
    $scope.first = function() {
        $scope.loading = true;
        $scope.showMessage = false;
        $resource("cad_lista/first").get(function(response) {
            $scope.loading = false;
            if (response.status === "success") {
                $scope.isRetrive = true;
                $scope.lista = response.data;
            } else if (response.status === "error") {
                $scope.showMessage = true;
                $scope.messageType = "alert-danger";
                $scope.message = response.message;
            }
        }, function(response) {
            processError($scope, response);
        });
    };
    $scope.last = function() {
        $scope.loading = true;
        $scope.showMessage = false;
        $resource("cad_lista/last").get(function(response) {
            $scope.loading = false;
            if (response.status === "success") {
                $scope.isRetrive = true;
                $scope.lista = response.data;
            } else if (response.status === "error") {
                $scope.showMessage = true;
                $scope.messageType = "alert-danger";
                $scope.message = response.message;
            }
        }, function(response) {
            processError($scope, response);
        });
    };
    $scope.previous = function() {
        $scope.showMessage = false;
        if ($scope.isRetrive) {
            $scope.loading = true;
            var lista = $scope.lista;
            $resource("cad_lista/previous?data=:data").get({data: lista.iLista},
            function(response) {
                $scope.loading = false;
                if (response.status === "success") {
                    $scope.isRetrive = true;
                    $scope.lista = response.data;
                } else if (response.status === "error") {
                    $scope.showMessage = true;
                    $scope.messageType = "alert-danger";
                    $scope.message = response.message;
                }
            }, function(response) {
                processError($scope, response);
            });
        }
    };
    $scope.next = function() {
        $scope.showMessage = false;
        if ($scope.isRetrive) {
            $scope.loading = true;
            var lista = $scope.lista;
            $resource("cad_lista/next?data=:data").get({data: lista.iLista},
            function(response) {
                $scope.loading = false;
                if (response.status === "success") {
                    $scope.isRetrive = true;
                    $scope.lista = response.data;
                } else if (response.status === "error") {
                    $scope.showMessage = true;
                    $scope.messageType = "alert-danger";
                    $scope.message = response.message;
                }
            }, function(response) {
                processError($scope, response);
            });
        }
    };
    $scope.exclude = function() {
        $scope.loading = true;
        $scope.showMessage = false;
        if ($scope.isRetrive) {
            var lista = $scope.lista;
            $resource("cad_lista/exclude?data=:data").get({data: lista.iLista},
            function(response) {
                $scope.loading = false;
                if (response.status === "success") {
                    $scope.isRetrive = false;
                    $scope.isNew = false;
                    $scope.lista = null;
                    $scope.showMessage = true;
                    $scope.messageType = "alert-success";
                    $scope.message = response.message;
                    setTimeout(function() {
                        $('#message').addClass("ng-hide");
                        $scope.showMessage = false;
                    }, 3000);
                } else if (response.status === "error") {
                    $scope.isRetrive = false;
                    $scope.isNew = false;
                    $scope.showMessage = true;
                    $scope.messageType = "alert-danger";
                    $scope.message = response.message;
                }
            }, function(response) {
                processError($scope, response);
            });
        }
    };
    $scope.addItem = function() {
        if ($scope.lista !== undefined) {
            if ($scope.lista.listasItensCollection === undefined) {
                $scope.lista.listasItensCollection = new Array();
            }
            $scope.lista.listasItensCollection.push(new Object());
            console.log($scope.lista.listasItensCollection.length);
        }
    };
    $scope.removeItem = function(item) {
        if ($scope.lista !== undefined && $scope.lista.listasItensCollection !== undefined) {
            for (var i in $scope.lista.listasItensCollection) {
                if ($scope.lista.listasItensCollection[i] === item) {
                    if ($scope.isRetrive && item.iListaItens !== undefined) {
                        $scope.loading = true;
                        $resource("cad_lista/item/exclude?data=:data").get({data: item.iListaItens},
                        function(response) {
                            $scope.loading = false;
                            if (response.status === "success") {
                                $scope.showMessage = true;
                                $scope.messageType = "alert-success";
                                $scope.message = response.message;
                                setTimeout(function() {
                                    $('#message').addClass("ng-hide");
                                    $scope.showMessage = false;
                                }, 3000);
                            } else if (response.status === "error") {
                                $scope.showMessage = true;
                                $scope.messageType = "alert-danger";
                                $scope.message = response.message;
                            }
                        }, function(response) {
                            processError($scope, response);
                        });
                    }
                    $scope.lista.listasItensCollection.splice(i, 1);
                }
            }
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