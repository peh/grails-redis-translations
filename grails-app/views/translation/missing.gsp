<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="translator.view.missing.title"/></title>
    <meta name="layout" content="translator">
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h1><g:message code="translator.view.missing.heading"/><button id="clear" class="btn btn-danger pull-right"><span></span><g:message
                    code="translater.view.missing.clear"/></button></h1>

        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <td><g:message code="translator.view.list.column.code"/></td>
                <g:each in="${supported}" var="lang">
                    <td>${lang}</td>
                </g:each>
            </tr>
            </thead>
            <g:each in="${list}" var="item">
                <tr data-name="clickable" data-target="${g.createLink(action: 'details', params: [key: item.key])}">
                    <td>${item.key}</td>
                    <g:each in="${supported}" var="lang">
                        <td>${item.value["${lang}"] ?: ''}</td>
                    </g:each>
                </tr>
            </g:each>
        </table>
    </div>
</div>
<g:javascript>
$(document).ready(function(){
    $('#clear').click(function(){
        if(confirm("${g.message(code: 'translator.view.missing.clear.confirm.text')}")){
            window.location = "${g.createLink(action: 'clearMissing')}";
        }
    });
});
</g:javascript>
</body>
</html>