angular.module('apply.controller', [])
    .controller('ApplyCtrl', ['$scope','$http','Restangular','$state','$timeout', 'Upload', function ($scope, $http,Restangular,$state,$timeout,Upload) {
        $scope.currentTime = _.now();
        $scope.vin = "";

        $timeout(function() {
            var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
                $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
                $uploaderInput = $("#uploaderInput"),
                $uploaderFiles = $("#uploaderFiles");

            $uploaderInput.on("change", function(e){
                var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
                for (var i = 0, len = files.length; i < len; ++i) {
                    var file = files[i];

                    if (url) {
                        src = url.createObjectURL(file);
                    } else {
                        src = e.target.result;
                    }

                    $scope.isSubmit = true;
                    Upload.upload({
                        url: 'ocr/vin',
                        data: {file: file}
                    }).success(function (data, status, headers, config) {
                        console.log('success status: ' + JSON.stringify(data));
                        $scope.vin = data;
                    }).error(function (data, status, headers, config) {
                        //console.log('error status: ' + status);
                    });
                    $uploaderFiles.append($(tmpl.replace('#url#', src)));
                }
            });
            $uploaderFiles.on("click", "li", function(){
                $galleryImg.attr("style", this.getAttribute("style"));
                $gallery.fadeIn(100);
            });
            $gallery.on("click", function(){
                $gallery.fadeOut(100);
            });
        });
    }]);
