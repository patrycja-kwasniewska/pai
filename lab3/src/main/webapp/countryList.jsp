<%@ page import="java.util.ArrayList" %>
<%@ page import="patrycja.lab3.CountryBean" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<%
    ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list");
%>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Kraje Europy</title>
</head>
<body>
    <h1>Kraje Europy</h1>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Code</th>
            <th>Population</th>
            <th>Details</th>
        </tr>
        <%
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    CountryBean country = list.get(i);
        %>
        <tr>
            <td><%= country.getName() %></td>
            <td><%= country.getCode() %></td>
            <td><%= country.getPopulation() %></td>
            <td><a href="details.jsp?index=<%= i %>">Details</a></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">Brak danych</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
