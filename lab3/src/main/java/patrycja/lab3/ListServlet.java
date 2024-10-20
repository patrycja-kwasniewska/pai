package patrycja.lab3;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


@WebServlet(name = "ListServlet", urlPatterns = {"/listCountries"})
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        
        // Ustawienia połączenia z bazą danych
        String url = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";
        String user = "root";
        String password = "Naczyniachłonne01!";

        // Inicjalizacja obiektu sesji
        HttpSession session = request.getSession(true);
        ArrayList<CountryBean> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement st = conn.createStatement()) {

                String query = "SELECT name, code, population, surfaceArea FROM Country WHERE Continent = 'Europe'";
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    CountryBean country = new CountryBean();
                    country.setName(rs.getString("name"));
                    country.setCode(rs.getString("code"));
                    country.setPopulation(rs.getInt("population"));
                    country.setSurfaceArea(rs.getDouble("surfaceArea"));
                    list.add(country);
                }

                
                // Logowanie rozmiaru listy
                System.out.println("Number of countries retrieved: " + list.size());
                
                // Zapisanie listy krajów w obiekcie sesji
                session.setAttribute("list", list);
                
                // Przekierowanie do strony JSP
                response.sendRedirect("countryList.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Nie znaleziono sterownika JDBC.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Błąd w bazie danych.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); 
    }
}
