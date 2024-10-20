<%-- 
    Document   : errorpage
    Created on : 15 paź 2024, 19:59:57
    Author     : patrycjakwasniewska
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Błąd - nieprawidłowe dane</title>
    </head>
    <body>
        <h2>Wprowadzono błędne dane!</h2>
        <p>Pojawił się następujący błąd: 
            <strong><%= exception.getMessage() %></strong>
        </p>
        <p>Proszę wrócić i spróbować ponownie.</p>
        <a href="calcwithbean.jsp">Powrót do kalkulatora</a>
    </body>
</html>
