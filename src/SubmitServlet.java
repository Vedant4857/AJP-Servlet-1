import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String rollno = request.getParameter("rollno");
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb", "root", "Vedant@20"
            );

            // Insert data
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO NotVedant (rollno, name, address) VALUES (?, ?, ?)"
            );

            ps.setInt(1, Integer.parseInt(rollno));
            ps.setString(2, name);
            ps.setString(3, address);

            int i = ps.executeUpdate();

            if (i > 0)
                out.println("<h3>Student record inserted successfully!</h3>");
            else
                out.println("<h3>Failed to insert data.</h3>");

            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(out);
        }
    }
}
