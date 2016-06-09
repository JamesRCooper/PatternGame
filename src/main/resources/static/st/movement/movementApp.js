var app = angular.module('movementApp', []);

app.controller('poolController', function($scope, $http) {

    $http.get("/entity/character/").then(function (response) {
        $scope.characters = response.data;
    });

    $scope.loadCharacter = function (characterName) {
        $http.get("/pool/place?characterName=" + characterName).then(function (response) {
            $scope.response = response;
            $scope.playerPoolId = response.data.message;
        });
    };

    $scope.enterRoom = function (location) {
        $http.get("/pool/moveToRoom?activeId=" + $scope.playerPoolId + "&roomName=" + location)
            .then(function (response) {
            });
    };

    $scope.movePlayer = function (direction) {
        $http.get("/pool/character/move/" + direction + "?activeId=" + $scope.playerPoolId)
            .then(function (outerResponse) {
                $scope.response = outerResponse;
                $http.get("/pool/map?activeId=" + $scope.playerPoolId).then(function (innerResponse) {
                    setUpMap(innerResponse.data.objects);
                });
            });
    };

    $scope.facePlayer = function (direction) {
        $http.get("/pool/character/face/" + direction + "?activeId=" + $scope.playerPoolId)
            .then(function (outerResponse) {
                $scope.response = outerResponse;
                $http.get("/pool/map?activeId=" + $scope.playerPoolId).then(function (innerResponse) {
                    setUpMap(innerResponse.data.objects);
                });
            });
    }

    var setUpMap = function(rows) {
        var rowStructs = [];
        for (var i = 0; i < rows.length; i++) {
            var rowStruct = {};
            rowStruct.row = rows[i];
            rowStruct.index = i;
            rowStructs.push(rowStruct);
        }
        $scope.mappers = rowStructs;
    }

});