package msp;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "DeleteOrder", urlPatterns = { "/deleteorder" })
public class DeleteOrder extends HttpServlet {
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
            con.setAutoCommit(true);
            PreparedStatement ps = null;

            ps = con.prepareStatement("DELETE FROM ORDERS WHERE OrderID = ?");


            ps.setInt(1, CommonFunctions.parseInteger(request.getParameter("orderid")));


            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                out.println("<p>Order couldn't be deleted</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<p> Order deleted...<br> <a href=\"/MovieSystemApplication-Project1-context-root/orderviewer\"> View the Order List </a> !</p>");


            con.commit();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</body></html>");
        out.close();
    }
}
