<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page errorPage="errorpage.jsp" %>

<jsp:useBean id="loan" class="patrycja.lab2.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kalkulator rat z JavaBean</title>
</head>
<body>
    <h1>Kalkulator rat kredytu z użyciem JavaBean</h1>

    <form method="post" action="calcwithbean.jsp">
        Kwota pożyczki: 
        <input type="text" name="kwota" value="<%= loan.getKwota() %>" /><br />
        
        Oprocentowanie roczne (%): 
        <input type="text" name="oprocentowanie" value="<%= loan.getOprocentowanie() %>" /><br />
        
        Liczba rat: 
        <input type="text" name="liczbaRat" value="<%= loan.getLiczbaRat() %>" /><br />
        
        <input type="submit" name="wyslij" value="Oblicz ratę" />
    </form>

    <% 
    if (request.getParameter("wyslij") != null) {
        try {
            double kwota = Double.parseDouble(request.getParameter("kwota"));
            double oprocentowanie = Double.parseDouble(request.getParameter("oprocentowanie"));
            int liczbaRat = Integer.parseInt(request.getParameter("liczbaRat"));

            // Walidacja liczby rat
            if (liczbaRat <= 0 || oprocentowanie <=0) {
                throw new RuntimeException("Liczba rat nie może być 0.");
            }
            
            loan.setKwota(kwota);
            loan.setOprocentowanie(oprocentowanie);
            loan.setLiczbaRat(liczbaRat);
            
            DecimalFormat df = new DecimalFormat("#.00");
            double rata = loan.getRata();
            out.println("<h2>Twoja miesięczna rata wynosi: " + df.format(rata) + " PLN</h2>");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Nieprawidłowy format danych wejściowych.");
        }
    }
    %>

</body>
</html>