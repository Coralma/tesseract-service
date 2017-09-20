angular.module('oaApp', ['restangular','ui.router','apply.controller','ngFileUpload'])
    .config(function ($stateProvider, $urlRouterProvider,RestangularProvider,$httpProvider) {
        RestangularProvider.setBaseUrl('/ocr');
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

        $stateProvider
            .state('apply', {
                url: '/apply',
                controller: 'ApplyCtrl',
                templateUrl: 'app/apply/apply.html'
            });
        $urlRouterProvider.otherwise('/apply');
    });