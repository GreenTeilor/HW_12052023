<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="dependencies.jsp"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp"/>
<div style="margin-bottom: 20px;">
    <div class="row">
        <div class="col-sm-3" style="text-align: left; font-size: 1.5rem;">
            Имя: ${name}
        </div>
        <div class="col-sm-3" style="text-align: left; font-size: 1.5rem;">
            Фамилия: ${lastName}
        </div>
        <div class="balance col-sm-6" style="text-align: left; font-size: 1.5rem;">
            Баланс: <fmt:formatNumber value="${balance}"
                                      type="currency"/>
        </div>
    </div>
</div>
<div class="row">
    <c:forEach items="${categories}" var="item">
        <div class="col d-flex justify-content-center">
            <div class="card" style="width: 22rem; margin: 20px; background-color: lightgrey">
                <a href="category?name=${item.name()}"><img src="${item.imagePath()}" class="card-img-top" style="height: 25rem;" alt="..."></a>
                <div class="card-body" style="text-align: center">
                    <h2 class="card-title">${item.name()}</h2>
                    <a href="category?name=${item.name()}" class="btn btn-primary">Перейти</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
