<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
    <style>
        *:not(input) {
            margin: 5px;
        }
    </style>
</head>
<body>
<form action="calculator" method="POST">
    <div>
        <input name="operand1">
        <input name="operand2">
        <span> = </span>
        <span>${result}</span>
    </div>
    <div>
        <span>Операции: </span>
    </div>
    <div>
        <span>Сложить</span><input type="radio" name="operation" value="sumOperation"/>
        <span>Вычесть</span><input type="radio" name="operation" value="subOperation"/>
        <span>Умножить</span><input type="radio" name="operation" value="mulOperation"/>
        <span>Делить</span><input type="radio" name="operation" value="divOperation"/>
    </div>
    <div>
        <input type="submit" value="Посчитать"/>
    </div>
</form>
</body>
</html>
