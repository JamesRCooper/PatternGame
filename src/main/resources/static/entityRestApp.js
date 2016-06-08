var app = angular.module('entityRestApp', []);

var absoluteAddress = 'jamescooper.privatedns.org:8081';

controllerMethodFactory = function(ctrlDest, ctrlType) {

    return function($scope, $http) {
        $scope.getEntity = function() {
            $http.get(ctrlDest).then(function (response) {
                $scope.entityResponse = response.data;
            });
        };

        $scope.postEntity = function(newEntity) {

            newEntity.type = ctrlType;
            $http.post(ctrlDest, newEntity).then(function(response) {
                $scope.entityResponse = [response];
            });
        };

        $scope.putEntity = function(updatedEntity) {

            updatedEntity.type = ctrlType;
            $http.put(ctrlDest + updatedEntity.identifier, updatedEntity)
                .then(function(response) {
                    $scope.entityResponse = [response];
                });
        };

        $scope.deleteEntity = function(oldEntity) {

            $http.delete(ctrlDest + oldEntity.identifier).then(function(response) {});
        };
    };
};

app.controller('armorController',
    controllerMethodFactory("/entity/armor/", "ARMOR"));

app.controller('armorDecoratorController',
    controllerMethodFactory("/entity/armorDecorator/", "ARMOR_DECORATOR"));

app.controller('weaponController',
    controllerMethodFactory("/entity/weapon/", "WEAPON"));

app.controller('weaponDecoratorController',
    controllerMethodFactory("/entity/weaponDecorator/", "WEAPON_DECORATOR"));

app.controller('characterController',
    controllerMethodFactory("/entity/character/", "CHARACTER"));
