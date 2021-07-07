package msp;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Filter", urlPatterns = { "/filter" })
public class Filter extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Filtering</title>");
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
        out.println("color: blue;text-decoration:none;");
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

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM MOVIES WHERE ReleaseDate >= ? AND ReleaseDate <= ? AND Genre LIKE ? AND Rating >= ?");
            ps.setDate(1, CommonFunctions.parseDate(request.getParameter("releasedatefrom")));
            ps.setDate(2, CommonFunctions.parseDate(request.getParameter("releasedateto")));
            ps.setString(3, '%' + (request.getParameter("querystring")) + '%');
            ps.setDouble(4, CommonFunctions.parseDouble(request.getParameter("minrating")));

            ResultSet rs = ps.executeQuery();

            out.println("<p> <a href=\"/MovieSystemApplication-Project1-context-root/FilterForm.html\">FILTER MOVIES</a></p>");
            out.println("<h2  style=\"text-align:center;text-decoration: underline;\">MOVIES</h2>");
            out.println("<table class=\"center\">\n");
            out.println("<tr><th>Movie ID</th><th>Movie Title</th><th>Release Date</th><th>Price</th><th>Rating</th>" +
                        "<th>Genre</th><th>Actions</th></tr>\n");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("MovieID") + "</td><td>" + rs.getString("MovieTitle") +
                            "</td><td>" + rs.getString("ReleaseDate") + "</td><td>" + rs.getDouble("Price") +
                            "</td><td>" + rs.getDouble("Rating") + "</td><td>" + rs.getString("Genre") + "</td>" +
                            "<td style=\"text-align: center;\"> <a href=\"/MovieSystemApplication-Project1-context-root/rentmovie" +
                            "?movieid=" + rs.getString("MovieID") + "&gamename=" + rs.getString("MovieTitle") +
                            "&releasedate=" + rs.getString("ReleaseDate") + "&price=" + rs.getDouble("Price") +
                            "&rating=" + rs.getDouble("Rating") + "&genre=" + rs.getString("Genre") + "\">RENT</a>" +
                            "</td></tr>");
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
