<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
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
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <input type="email" name="login" id="login" class="form-control"/>
                            <label class="form-label" for="login">Логин</label>
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
