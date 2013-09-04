<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="translator.view.find.title"/></title>
    <meta name="layout" content="translator">
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h1><g:message code="translator.view.find.title"/> <small>warning: this could take some time.</small></h1>
        </div>
        <g:form action="find" class="form">
            <div class="input-group">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span> <g:message code="form.generic.search" /> </button>
                </span>
                <input name="search" id="search" type="text" class="form-control" value="${search}" />
            </div>
        </g:form>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><g:message code="translator.view.list.column.code" /></td>
                <g:each in="${supported}" var="lang">
                    <td>${lang}</td>
                </g:each>
            </tr>
            </thead>
            <g:each in="${list}" var="item">
                <tr data-name="clickable" data-target="${g.createLink(action: 'details', params: [key: item.key])}">
                    <td>${item.key}</td>
                    <g:each in="${supported}" var="lang">
                        <td ${item.value["${lang}"] ? '' : 'class="danger"'}>${item.value["${lang}"] ?: ''}</td>
                    </g:each>
                </tr>
            </g:each>
        </table>
    </div>
</div>
</body>
</html>