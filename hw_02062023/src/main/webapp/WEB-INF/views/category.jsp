<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${categoryName}</title>
    <jsp:include page="dependencies.jsp"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row">
    <c:forEach items="${categoryProducts}" var="product">
        <div class="col d-flex justify-content-center">
            <div class="card" style="width: 22rem; margin: 20px; background-color: lightgrey">
                <a href="#"><img src="${product.imagePath()}" class="card-img-top" style="height: 25rem;" alt="..."></a>
                <div class="card-body" style="text-align: center">
                    <h2 class="card-title">${product.name()}</h2>
                    <p class="card-text">Цена: <fmt:formatNumber value="${product.price()}"
                                                                 type="currency"/><br>
                        ${product.description()}</p>
                    <a href="#" class="btn btn-primary">Посмотреть</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
