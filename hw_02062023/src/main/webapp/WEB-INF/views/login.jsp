<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <jsp:include page="dependencies.jsp"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        .rounded-t-5 {
            border-top-left-radius: 0.5rem;
            border-top-right-radius: 0.5rem;
        }

        @media (min-width: 992px) {
            .rounded-tr-lg-0 {
                border-top-right-radius: 0;
            }

            .rounded-bl-lg-5 {
                border-bottom-left-radius: 0.5rem;
            }
        }
    </style>

</head>
<body>
<section class=" text-center text-lg-start">
    <div class="card mb-3">
        <div class="row g-0 d-flex align-items-center">
            <div class="col-lg-4 d-none d-lg-flex">
                <img src="https://www.museothyssen.org/sites/default/files/imagen/obras/1980.86_otono.jpg"
                     class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5"/>
            </div>
            <div class="col-lg-8">
                <div class="card-body py-5 px-md-5">
                    <form method="POST" action="login">

                        <%-- TO USE IN REGISTRATION FORM
                        <!-- Name input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="name" id="name" class="form-control"/>
                            <label class="form-label" for="name">Имя</label>
                        </div>

                        <!-- Last name input -->
                        <div class="form-outline mb-4">
                            <input type="text" name="lastName" id="lastName" class="form-control"/>
                            <label class="form-label" for="lastName">Фамилия</label>
                        </div>
                        --%>

                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <input type="email" name="email" id="email" class="form-control"/>
                            <label class="form-label" for="email">Email</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <input type="password" name="password" id="password" class="form-control"/>
                            <label class="form-label" for="password">Пароль</label>
                        </div>

                        <!-- Submit button -->
                        <input type="submit" class="btn btn-primary btn-block mb-4" value="Отправить">
                        <span>${result}</span>
                    </form>

                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
