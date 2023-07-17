<%@ page import="by.teachmeskills.enums.SessionAttributesEnum" %>
<%@ page import="by.teachmeskills.entities.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">

    <div class="container-fluid">
      <a class="navbar-brand" href="shop?command=home_page">
        <img src="assets/books.png" width="30" height="30"
             class="d-inline-block align-top" alt="">
        Книжечки
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse"
              data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="shop?command=home_page">&#127968 Главная</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="shop?command=about_page">&#10067 Об авторе</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="shop?command=profile_page">&#128188 Профиль</a>
          </li>
          <%
            Cart cart = (Cart)request.getSession().getAttribute(SessionAttributesEnum.CART.getValue());
            String productsInCart = (cart != null && cart.size() != 0) ? String.valueOf(cart.size()) : "";
          %>
          <li class="nav-item">
            <a class="nav-link" href="shop?command=cart_page&type=show">&#128465 Корзина<sup style="color: red; font-weight: bold; font-size: 1rem;"><%=productsInCart%></sup></a>
          </li>
          <li>
            <a class="nav-link" href="shop?command=search_page">&#128269 Поиск</a>
          </li>
        </ul>
      </div>

      <div class="d-flex align-items-center">
        <button type="button" class="btn btn-primary px-3 me-2">
          Логин
        </button>
        <button type="button" class="btn btn-primary me-3">
          Регистрация
        </button>
      </div>

    </div>
  </nav>
</header>