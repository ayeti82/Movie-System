package msp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ProcessUpdate", urlPatterns = { "/processupdate" })
public class ProcessUpdate extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>ProcessViewer</title>");
        out.println("<style>");
        out.println("td,th{");
        out.println("border: 1px solid Navy;width:50.0%;text-align: center;");
        out.println("}");
        out.println("table{");
        out.println("width:50.0%;text-align: center;");
        out.println("}");
        out.println(" input[type=text], select {");
        out.println(" width: 95%;border-radius: 1.0px ;padding: 12px 20px;margin: 8px 0;display: inline-block;\n" +
                    "border: 1px solid Navy;box-sizing: border-box;background: #f7f7f7  ;");
        out.println("}");
        out.println("input[type=submit] {");
        out.println(" width: 45%;background-color: Blue;color: white;padding: 14px 20px;margin-left:25px;\n" +
                    "margin-top:10px;border: none;cursor: pointer;");
        out.println("}");
        out.println("input[type=submit]:hover {");
        out.println("background-color: Navy;");
        out.println("}");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>UPDATE ORDER</h2>");
        out.println("<form name=\"UpdateSimilarForm\" action=\"updateorder\" method=\"get\">");
        out.println("<table>");

        out.println("</tr><tr><td>ORDER ID</td>" +
                    "<td><input readonly=\"readonly\" type=\"text\"  name=\"orderid\" value=\"" +
                    request.getParameter("orderid") + "\"/></td>");
        out.println("</tr><tr><td>RENTAL DATE</td><td><input type=\"text\" name=\"rentaldate\" value=\"" +
                    request.getParameter("rentaldate").substring(0, 10) + "\"/></td>");
        out.println("</tr><tr><td>RETURN DATE</td><td><input type=\"text\" name=\"returndate\" value=\"" +
                    request.getParameter("returndate").substring(0, 10) + "\"/></td>");
        out.println("</tr><tr><td>MOVIE ID</td><td><input type=\"text\" name=\"movieID\"  value=\"" +
                    request.getParameter("movieID") + "\"/></td>");
        out.println("</tr><tr><td>NET AMOUNT</td><td><input type=\"text\" name=\"netamount\" value=\"" +
                    request.getParameter("netamount") + "\"/></td>");
        out.println("</tr><tr><td>DISCOUNT</td><td><input type=\"text\" name=\"discount\" value=\"" +
                    request.getParameter("discount") + "\"/></td>");
        out.println("</tr><tr><td>GROSS AMOUNT</td><td><input type=\"text\" name=\"grossamount\" value=\"" +
                    request.getParameter("grossamount") + "\"/></td>");


        out.println("</table><input type=\"submit\" name=\"Submit\"/></form>");

        out.println("</body></html>");
        out.close();
    }
}
