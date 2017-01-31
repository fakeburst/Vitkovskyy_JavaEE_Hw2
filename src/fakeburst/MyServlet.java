package fakeburst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

/**
 * Created by Max on 31-Jan-17.
 */
public class MyServlet extends HttpServlet {
    private String status = "";

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HW2</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>" + status + "</h2>");
            out.println("<form action=\"MyServlet\" method=\"post\">\n" +
                    "    User Name: <input type=\"text\" name=\"login\">\n" +
                    "    Password: <input type=\"password\" name=\"pass\">\n" +
                    "    <input type=\"hidden\" name=\"action\" value=\"login\">" +
                    "    <input type=\"submit\" value=\"Login\">\n" +
                    "</form> " +
                    "<form action=\"MyServlet\" method=\"post\">\n" +
                    "    User Name: <input type=\"text\" name=\"login\">\n" +
                    "    Password: <input type=\"password\" name=\"pass\">\n" +
                    "    <input type=\"hidden\" name=\"action\" value=\"register\">" +
                    "    <input type=\"submit\" value=\"Register\">\n" +
                    "</form> ");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action").equals("login")) {
            String login = request.getParameter("login");
            String pass = request.getParameter("pass");
            String role = "generic";

            if (users.get(login) != null && users.get(login).equals(pass)) {
                role = "registered";
            } else if (login.equals("admin") && pass.equals("admin")) {
                role = "admin";
            }
            request.setAttribute("role", role);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            status = "";
        } else if(request.getParameter("action").equals("register")) {
            users.putIfAbsent(request.getParameter("login"), request.getParameter("pass"));
            status = "Registered";
            processRequest(request, response);
        }
    }

    private HashMap<String, String> users = new HashMap<String, String>();
    {
        users.putIfAbsent("Max", "1234");
        users.putIfAbsent("Vlad", "5678");
    }

}
