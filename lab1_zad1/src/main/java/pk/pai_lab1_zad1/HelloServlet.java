package pk.pai_lab1_zad1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author patrycjakwasniewska
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {
    
    // Deklaracja pola data1 typu Date
    private Date data1;

    // Metoda init(), nadpisana, aby zainicjalizować data1
    @Override
    public void init() {
        data1 = new Date();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloServlet at " + request.getContextPath() + "</h1>");
            
            
            //////////////////////////////////////////////////////////////////////////// ZAD 2

            // Wyświetlenie metadanych żądania HTTP
            out.println("<h2>Dane serwera</h2>");
            out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
            out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
            out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
            out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
            
            out.println("<h2>Szczegóły żądania</h2>");
            out.println("<p>request.getMethod(): " + request.getMethod() + "</p>");
            out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
            
            
            ////////////////////////////////////////////////////////////////////////////

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate1 = dateFormat.format(data1);
            String formattedCurrentDate = dateFormat.format(new Date());

            // Wyświetlenie wartości data1 - nie zmienia się po odświeżeniu strony
            out.println("<h2>Data inicjalizacji serwletu (z init()): " + formattedDate1 + "</h2>");
            // Ta data aktualizuje się po odświeżeniu strony
            out.println("<h2>data z processRequest: " + formattedCurrentDate + "</h2>");


            ////////////////////////////////////////////////////////////////////////////

            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
