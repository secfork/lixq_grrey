<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 
    <!DOCTYPE html>
    <html lang="en" data-ng-app="app">
    <head>
    <meta charset="utf-8" />
    <title>ThingLinx</title>
    <link href="../img/logo1.png" rel="shortcut icon">

    <meta name="description" content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />


    <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="../css/animate.css" type="text/css" />
    <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="../css/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="../css/font.css" type="text/css" />
    <link rel="stylesheet" href="../css/app.css" type="text/css" />

    </head>
    <body ng-controller="AppCtrl">

    <div class="app" id="app"
    ng-class="{'app-header-fixed':app.settings.headerFixed,
    'app-aside-fixed':app.settings.asideFixed,
    'app-aside-folded':app.settings.asideFolded ,
    ' fade-in-left-big ':true ,
    'smooth':true } " ui-view  >
    </div>







    </body>



    <!-- jQuery -->
    <script src="../lib/jquery/jquery.min.js"></script>

    <!-- Angular -->
    <script src="../lib/angular/angular.min.js"></script>
    <script src="../lib/angular/angular-cookies.min.js"></script>
    <script src="../lib/angular/angular-resource.min.js"></script>

    <script src="../lib/angular/angular-animate.min.js"></script>
    <script src="../lib/angular/angular-ui-router.min.js"></script>
    <script src="../lib/angular/angular-translate.js"></script>

    <script src="../lib/angular/ngStorage.min.js"></script>
    <script src="../lib/angular/ui-load.js"></script>
    <script src="../lib/angular/ui-jq.js"></script>
    <script src="../lib/angular/ui-validate.js"></script>
    <script src="../lib/angular/ui-bootstrap-tpls.min.js"></script>

    <!--  bootstrap  datepicker-->
    <script  src="../lib/bootstrap_datepicker/bootstrap-datepicker.js"></script>
    <link rel="stylesheet"  href="../lib/bootstrap_datepicker/datepicker.css" />


    <script src="../athena/app_login.js"></script>
    <script src="../athena/service/services.js"></script>
    <script src="../athena/service/sysconfig.js"></script>
    <script src="../athena/controller/controllers.js"></script>
    <script src="../athena/filter/filters.js"></script>
    <script src="../athena/directive/directives.js"></script>



    </html>