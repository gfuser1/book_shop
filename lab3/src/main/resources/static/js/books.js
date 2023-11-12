var app = angular.module('books', []);

app.controller("BooksController", function ($scope, $http) {

    $scope.book_id = -1;
    $scope.getBooks = function () {
        $http.get('/public/rest/books').success(function (data, status, headers, config) {
            $scope.booksList = data;
        }).error(function (data, status, headers, config) {
            if (data.message === 'Time is out') {
                $scope.finishByTimeout();
            }
        });
    };

    $scope.delete = function (id) {
        $http.delete('/public/rest/books/' + id).success(function (data, status, headers, config) {
            for (var i = 0; i < $scope.booksList.length; i++) {
                var j = $scope.booksList[i];
                if (j.id === id) {
                    $scope.booksList.splice(i, 1);
                    break;
                }
            }
        }).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });
    };

    $scope.add = function () {
        $http.post('/public/rest/books/' + $scope.title + "/" + $scope.author).success(function (data, status, headers, config) {
            $scope.booksList.push(data);
            $scope.title = null;
            $scope.author = null;
        }
        ).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });

    };

     $scope.update = function () {
            $http.post('/public/rest/books/'+$scope.book_id+"/"+ $scope.title + "/" + $scope.author).success(function (data, status, headers, config) {

                for (var i = 0; i < $scope.booksList.length; i++) {
                                var j = $scope.booksList[i];
                                if (j.id === $scope.book_id) {
                                    j.title = data.title;
                                    j.author = data.author;
                                    break;
                                }
                            }
                $scope.title = null;
                $scope.author = null;
                $scope.book_id = -1;
            }
            ).error(function (data, status, headers, config) {
                console.error(status, data, headers);
            });

     };

    $scope.setUpdateParam = function(book){
        $scope.book_id = book.id;
        $scope.title = book.title;
        $scope.author = book.author;
    };

});
