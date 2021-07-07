package msp;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UpdateOrder", urlPatterns = { "/updateorder" })
public class UpdateOrder extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        try {
            Connection con = CommonFunctions.getConnection();
            PreparedStatement ps =
                con.prepareStatement("UPDATE ORDERS\n" + "SET\n" + "RentalDate = ?,\n" + "ReturnDate = ?,\n" +
                                     "MovieID = ?,\n" + "NetAmount = ?,\n" + "Discount = ?,\n" + "GrossAmount = ?\n" +
                                     "WHERE OrderID = ?");
            ps.setDate(1, CommonFunctions.parseDate(request.getParameter("rentaldate")));
            ps.setDate(2, CommonFunctions.parseDate(request.getParameter("returndate")));
            ps.setString(3, request.getParameter("movieID"));
            ps.setDouble(4, CommonFunctions.parseDouble(request.getParameter("netamount")));
            ps.setDouble(5, CommonFunctions.parseDouble(request.getParameter("discount")));
            ps.setDouble(6, CommonFunctions.parseDouble(request.getParameter("grossamount")));
            ps.setString(7, request.getParameter("orderid"));

            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                out.println("<p> Order not updated!  Invalid input or internal error</p>");
                out.println("<p> Go back to <a href=\"/MovieSystemApplication-Project1-context-root/orderviewer\"> View the Order List </a> !</p>");
                out.println("<p> " + e.getMessage() + " </p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<p> Order updated... <br><a href=\"/MovieSystemApplication-Project1-context-root/orderviewer\"> View the Order List </a> !</p>");

            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
    }
}
