package msp;

import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "OrderViewer", urlPatterns = { "/orderviewer" })
public class OrderViewer extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>OrderViewer</title>");
        out.println("<style>");
        out.println("td,th{");
        out.println("border: 1px solid Navy;width:11.0%;text-align: left;");
        out.println("}");
        out.println(".center{");
        out.println("margin-left:auto;margin-right:auto,border: 1px solid Navy;width:100.0%;text-align: center;");
        out.println("}");
        out.println("a:link{");
        out.println("color: blue;text-decoration:none;");
        out.println("}");
        out.println("a:visited{");
        out.println("color: green;text-decoration:none;");
        out.println("}");
        out.println("a:hover{");
        out.println("color: green;text-decoration:none;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        try {
            Connection con = CommonFunctions.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM dbo.Orders");

            out.println("<p> <a href=\"/MovieSystemApplication-Project1-context-root/movieviewer\">MOVIES</a></p>");
            out.println("<p> <a href=\"/MovieSystemApplication-Project1-context-root/FilterForm.html\">FILTER MOVIES</a></p>");
            out.println("<h2 style=\"text-align:center;text-decoration: underline;\">ORDERS</h2>");
            out.println("<table class=\"center\">\n");
            out.println("<tr><th>Order ID</th><th>Rental Date</th><th>Return Date</th><th>Movie ID</th>" +
                        "<th>Net Amount</th><th>Discount</th><th>Gross Amount</th><th>Action</th></tr>\n");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("OrderID") + "</td><td>" + rs.getString("RentalDate") +
                            "</td><td>" + rs.getString("ReturnDate") + "</td><td>" + rs.getInt("MovieID") +
                            "</td><td>" + rs.getDouble("NetAmount") + "</td><td>" + rs.getDouble("Discount") +
                            "</td><td>" + rs.getDouble("GrossAmount") +
                            "<td  style=\"text-align: center;\"> <a href=\"/MovieSystemApplication-Project1-context-root/processupdate" +
                            "?orderid=" + rs.getString("OrderID") + "&rentaldate=" + rs.getString("RentalDate") +
                            "&returndate=" + rs.getString("ReturnDate") + "&movieID=" + rs.getString("MovieID") +
                            "&netamount=" + rs.getDouble("NetAmount") + "&discount=" + rs.getDouble("Discount") +
                            "&grossamount=" + rs.getDouble("GrossAmount") + "\">UPDATE</a> | " +
                            "<a href=\"/MovieSystemApplication-Project1-context-root/deleteorder" + "?orderid=" +
                            rs.getString("OrderID") + "\">DELETE</a>" + "</td></tr>");
            }
            out.println("</table>");
            statement.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</body></html>");
        out.close();
    }
}
