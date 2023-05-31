<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="col-sm-6" style="text-align: left; font-size: 1.5rem;">
            <span style="padding: 2rem;">Имя: ${name}</span> <span style="padding: 2rem;">Фамилия: ${lastName}</span>
        </div>
        <div class="col-sm-6" style="text-align: right; font-size: 1.5rem;">
            <span style="padding-right: 2rem;">Баланс: ${balance}</span>
        </div>
    </div>
</div>
<div class="row">
    <c:forEach items="${categories}" var="item">
        <div class="col d-flex justify-content-center">
            <div class="card" style="width: 22rem; margin: 20px; background-color: lightgrey">
                <img src="${item.imagePath()}" class="card-img-top" style="height: 25rem;" alt="...">
                <div class="card-body" style="text-align: center">
                    <h5 class="card-title">${item.name()}</h5>
                        <%--
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                            card's
                            content.</p>
                            --%>
                    <a href="#" class="btn btn-primary">Перейти</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
