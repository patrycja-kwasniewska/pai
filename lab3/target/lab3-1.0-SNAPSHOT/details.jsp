<%@ page import="java.util.ArrayList" %>
<%@ page import="patrycja.lab3.CountryBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<%
    // Odczytanie indeksu z parametru URL
    String indexParam = request.getParameter("index");
    CountryBean country = null;

    if (indexParam != null) {
        try {
            int index = Integer.parseInt(indexParam); // Konwersja na int

            // Odczytanie listy krajów z sesji
            ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list");

            // Sprawdzenie, czy lista nie jest null i czy indeks jest w poprawnym zakresie
            if (list != null && index >= 0 && index < list.size()) {
                country = list.get(index);
            }
        } catch (NumberFormatException e) {
            // Obs?uga b??du konwersji
            e.printStackTrace();
        }
    }
%>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Szczegóły kraju</title>
</head>
<body>
    <h1>Szczegóły kraju</h1>
    <%
        if (country != null) {
    %>
    <p><strong>Nazwa:</strong> <%= country.getName() %></p>
    <p><strong>Kod:</strong> <%= country.getCode() %></p>
    <p><strong>Ludność:</strong> <%= country.getPopulation() %></p>
    <p><strong>Powierzchnia:</strong> <%= country.getSurfaceArea() %> km²</p> <!-- Dodanie powierzchni -->
    <%
        } else {
    %>
    <p>Nie znaleziono informacji o wybranym kraju lub wystąpił błąd w przekazywaniu indeksu.</p>
    <%
        }
    %>
    
    <a href="countryList.jsp">Powrót do listy krajów</a>
</body>
</html>
