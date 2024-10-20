package pk.pai_lab1_zad4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    // Pobranie obiektu sesji
    HttpSession session = request.getSession(true);
    
    // Obsługa historii operacji
    ArrayList<String> history = (ArrayList<String>) session.getAttribute("history");
    if (history == null) {
        history = new ArrayList<>();
    }

    // Sprawdzanie ciasteczek
    String powitanie = "Witaj po raz pierwszy!";
    boolean isReturningUser = false;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("UserId".equals(cookie.getName())) {
                powitanie = "Witaj po raz kolejny!";
                isReturningUser = true;
                break;
            }
        }
    }

    // Jeśli użytkownik jest nowy, tworzymy nowe ciasteczko
    if (!isReturningUser) {
        Cookie newUserCookie = new Cookie("UserId", "1");
        response.addCookie(newUserCookie);
    }

    // Obsługa czyszczenia historii
    String clearHistory = request.getParameter("clearHistory");
    if ("true".equals(clearHistory)) {
        history.clear();
        session.setAttribute("history", history);
    }

    try (PrintWriter out = response.getWriter()) {
        // Pobranie parametrów z formularza
        String num1String = request.getParameter("num1");
        String num2String = request.getParameter("num2");
        String operation = request.getParameter("operation");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Wynik Kalkulatora</title>");
        out.println("</head>");
        out.println("<body>");

        // Wyświetlenie powitania
        out.println("<h2>" + powitanie + "</h2>");

        // Obsługa, gdy parametry są obecne i poprawne
        if (!isNullOrEmpty(num1String) && !isNullOrEmpty(num2String) && !isNullOrEmpty(operation)) {
            double num1 = 0;
            double num2 = 0;
            String resultMessage = "";

            // Przekształcenie String na double z obsługą wyjątków
            try {
                num1 = Double.parseDouble(num1String);
                num2 = Double.parseDouble(num2String);
            } catch (NumberFormatException e) {
                resultMessage = "Podano niepoprawny format liczby!";
                out.println("<h2>" + resultMessage + "</h2>");
                history.add(resultMessage); // Dodanie błędu do historii
                session.setAttribute("history", history); // Zapisanie historii w sesji
                return;
            }

            // Sprawdzenie dzielenia przez zero
            if (operation.equals("/") && num2 == 0) {
                resultMessage = "Nie można dzielić przez zero!";
                out.println("<h2>" + resultMessage + "</h2>");
                history.add(resultMessage); // Dodanie błędu do historii
                session.setAttribute("history", history); // Zapisanie historii w sesji
            } else {
                // Jeśli nie ma błędu, wykonaj obliczenie
                double result = oblicz(num1, num2, operation);
                resultMessage = num1 + " " + operation + " " + num2 + " = " + result;

                // Wyświetlenie wyniku
                out.println("<h1>Wynik:</h1>");
                out.println("<p>" + resultMessage + "</p>");
                history.add(resultMessage); // Dodanie wyniku do historii
                session.setAttribute("history", history); // Zapisanie historii w sesji
            }
        }

        // Wyświetlenie historii operacji
        if (!history.isEmpty()) {
            out.println("<h2>Historia operacji:</h2>");
            out.println("<ul>");
            for (String h : history) {
                out.println("<li>" + h + "</li>");
            }
            out.println("</ul>");
        }

        // Link powrotny do formularza
        out.println("<p><a href=\"/lab1_zad1/calc.html\">Powrót do kalkulatora</a></p>");

        // Link do usunięcia historii
        out.println("<p><a href=\"CalcServlet?clearHistory=true\">Wyczyść historię</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}


    // Metoda pomocnicza do obliczeń
private double oblicz(double num1, double num2, String operation) {
    switch (operation) {
        case "+":
            return num1 + num2;
        case "-":
            return num1 - num2;
        case "*":
            return num1 * num2;
        case "/":
            return num1 / num2;
        default:
            throw new IllegalArgumentException("Nieznana operacja: " + operation);
    }
}


    // Sprawdzenie, czy parametr jest pusty
    private boolean isNullOrEmpty(String param) {
        return (param == null || param.trim().equals(""));
    }

    // Obsługa braku wartości parametru
    private void obsluga_Brakujacej_Wartosci_Parametru(PrintWriter out) {
        out.println("<h2>Brakujące dane! Upewnij się, że wszystkie pola są wypełnione.</h2>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "CalcServlet with session and cookie handling";
    }
}
