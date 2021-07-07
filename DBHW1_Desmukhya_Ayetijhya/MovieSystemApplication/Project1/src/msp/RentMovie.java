package msp;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "RentMovie", urlPatterns = { "/rentmovie" })
public class RentMovie extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        String preparedStatement =
            "IF (DATEDIFF(day,?,GETDATE())/365 >=5)\n" +
            "INSERT INTO ORDERS(RentalDate,ReturnDate,MovieID,NetAmount,Discount,GrossAmount)\n" +
            "VALUES(CAST(GETDATE() AS Date),DATEADD(day, 7, GETDATE()),?,?,25,?*0.98)\nELSE\n" +
            "INSERT INTO ORDERS(RentalDate,ReturnDate,MovieID,NetAmount,Discount,GrossAmount)\n" +
            "VALUES(CAST(GETDATE() AS Date),DATEADD(day, 3, GETDATE()),?,?,0,?*1.23)";
        try {
            Connection con = CommonFunctions.getConnection();
            con.setAutoCommit(true);
            PreparedStatement ps = null;
            ps = con.prepareStatement(preparedStatement);
            Date rsDate = CommonFunctions.parseDate(request.getParameter("releasedate"));
            Double netAmount = CommonFunctions.parseDouble(request.getParameter("price"));
            String gId = request.getParameter("movieid");
            ps.setDate(1, rsDate);
            ps.setString(2, gId);
            ps.setDouble(3, netAmount);
            ps.setDouble(4, netAmount);
            ps.setString(5, gId);
            ps.setDouble(6, netAmount);
            ps.setDouble(7, netAmount);

            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                out.println("<p>New Order was not added!</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<p> New Order added... <br><a href=\"/MovieSystemApplication-Project1-context-root/orderviewer\"> View the Orders </a> !</p>");


            con.commit();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</body></html>");
        out.close();
    }
}
