<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="translator.view.list.title"/></title>
    <meta name="layout" content="translator">
</head>

<body>
<div class="row">
    <div class="col-md-12">
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
                        <td ${item.value["${lang}"] ? '' : 'class="danger"'}>${item.value["${lang}"] ?: ''}</td>
                    </g:each>
                </tr>
            </g:each>
        </table>
    </div>
</div>
</body>
</html>