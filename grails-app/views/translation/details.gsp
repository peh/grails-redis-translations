<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="translator.view.details.title"/></title>
    <meta name="layout" content="translator">
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h1>${key} <small></small></h1>
        </div>
        <g:form action="save" params="[key: key]" class="form-horizontal">
            <g:each in="${supported}" var="item">
                <div class="form-group">
                    <label class="col-md-2" for="${item}">${item}</label>

                    <div class="col-md-10">
                        <g:textField name="${item}" class="form-control" value="${obj["$item"] ?: ''}"/>
                    </div>
                </div>
            </g:each>
            <button type="submit" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-floppy-disk"></span> Save</button>
        </g:form>
    </div>
</div>
<g:javascript>
</g:javascript>
</body>
</html>