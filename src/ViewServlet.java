import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Student Records</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f8f9fa;
                            margin: 0;
                            padding: 20px;
                        }
                        .container {
                            max-width: 800px;
                            margin: auto;
                            background: white;
                            padding: 30px;
                            border-radius: 10px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }
                        h2 {
                            text-align: center;
                            color: #343a40;
                        }
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                        }
                        th, td {
                            padding: 12px;
                            text-align: center;
                            border-bottom: 1px solid #dee2e6;
                        }
                        tr:hover {
                            background-color: #f1f1f1;
                        }
                        th {
                            background-color: #007bff;
                            color: white;
                        }
                        .btn-back {
                            margin-top: 20px;
                            display: inline-block;
                            padding: 10px 20px;
                            background-color: #28a745;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                        .btn-back:hover {
                            background-color: #218838;
                        }
                    </style>
                </head>
                <body>
                    <div class='container'>
                        <h2>All Student Records</h2>
                        <table>
                            <tr>
                                <th>Roll No</th>
                                <th>Name</th>
                                <th>Address</th>
                            </tr>
                """);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb", "root", "Vedant@20");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NotVedant");

            while (rs.next()) {
                int rollno = rs.getInt("rollno");
                String name = rs.getString("name");
                String address = rs.getString("address");

                out.println("<tr><td>" + rollno + "</td><td>" + name + "</td><td>" + address + "</td></tr>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<tr><td colspan='3'><strong>Error:</strong> " + e.getMessage() + "</td></tr>");
        }

        out.println("""
                        </table>
                        <div style="text-align: center;">
                            <a class='btn-back' href='index.html'>← Back to Home</a>
                        </div>
                    </div>
                </body>
                </html>
                """);
    }
}
