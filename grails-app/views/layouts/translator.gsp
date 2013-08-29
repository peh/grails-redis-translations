<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:layoutTitle/></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>

<body>
<a class="sr-only" href="#content">Skip navigation</a>

<!-- Docs master nav -->
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand"><g:message code="translator.brand.name"/></a>
        </div>
        <nav id="collapse" class="collapse navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li ${actionName == 'list' ? 'class="active"' : ''}>
                    <g:link controller="translation" action="list"><g:message code="translator.nav.list"/></g:link>
                </li>
                <li>
                    <g:link controller="translation" action="find"><g:message code="translator.nav.find"/></g:link>
                </li>
                <li>
                    <g:link controller="translation" action="missing"><g:message code="translator.nav.missing"/></g:link>
                </li>
                <li>
                    <g:link controller="translation" action="stats"><g:message code="translator.nav.stats"/></g:link>
                </li>
            </ul>
        </nav>
    </div>
</header>

<div class="container" style="margin-top: 55px;">
    <div class="col-md-12">
        <g:if test="${flash.message}">
            <div class="alert alert-dismissable ${flash.success == true ? 'alert-success' : flash.success == false ? 'alert-danger' : 'alert-info'}">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <strong>Warning!</strong> Best check yo self, you're not looking too good.
            </div>
        </g:if>
    </div>
</div>

<div class="container">
    <g:layoutBody/>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
</div>
</body>
</html>