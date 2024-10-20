<%-- 
    Document   : calc.jsp
    Created on : 15 paź 2024, 19:33:06
    Author     : patrycjakwasniewska
--%>

<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>Kalkulator rat</title>
</head>
<body>
    <h1>Kalkulator rat kredytu</h1>
    
    <!-- Formularz do wprowadzenia danych -->
    <form method="post" action="calc.jsp">
        Kwota pozyczki: <input type="text" name="kwota" /><br />
        Oprocentowanie roczne (%): <input type="text" name="oprocentowanie" /><br />
        Liczba rat: <input type="text" name="liczba_rat" /><br />
        <input type="submit" name="wyslij" value="Oblicz ratę" />
    </form>

    <% 
    // Sprawdzamy, czy formularz został wyslany
    if (request.getParameter("wyslij") != null) {
        String wynik = "";
        try {
            // Pobieranie danych z formularza
            double k = Double.parseDouble(request.getParameter("kwota"));
            double pr = Double.parseDouble(request.getParameter("oprocentowanie"));
            int n = Integer.parseInt(request.getParameter("liczba_rat"));

            // Obliczanie oprocentowania miesięcznego
            double p = pr / 12 / 100;

            // Obliczanie raty kredytu według wzoru
            double rata = (k * p) / (1 - Math.pow(1 + p, -n));

            // Formatowanie wyniku do dwóch miejsc po przecinku
            DecimalFormat df = new DecimalFormat("#.00");
            wynik = "Twoja miesięczna rata wynosi: " + df.format(rata) + " PLN";
        } catch (Exception ex) {
            wynik = "Błedne dane. Proszę spróbować ponownie.";
        }

        // Wyświetlanie wyniku
        out.println("<h2>" + wynik + "</h2>");
    }
    %>

    <hr />

    <!-- Wyświetlanie bieżacej daty -->
    <h2>Aktualna data: <%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %></h2>
    
</body>
</html>
