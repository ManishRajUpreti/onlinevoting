package onlinevoting;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet({"/vote"})
public class Vote extends HttpServlet {
private static final long serialVersionUID = 1L;
static final Connection con = DBConn.getDBConnection();
static PreparedStatement ps = null;


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   String f = request.getParameter("cardno");
   String l = request.getParameter("party");

   RequestDispatcher rd;
   try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/votingdb", "Vaishnavi", "Nelavetla@537");
      if (checkLogin(f)) {
         ps = con.prepareStatement("insert into vote values(?,?)");
         ps.setString(1, f);
         ps.setString(2, l);
         int i = ps.executeUpdate();
         RequestDispatcher rd1;
         if (i > 0) {
            out.print("Your Vote has been submitted successfully...");
            rd1 = request.getRequestDispatcher("thankyou.html");
            rd1.include(request, response);
         } else {
            out.print("Failed to submit vote, try again");
            rd1 = request.getRequestDispatcher("vote.html");
            rd1.include(request, response);
         }
      } else {
         out.print("Please enter correct card number");
         rd = request.getRequestDispatcher("vote.html");
         rd.include(request, response);
      }
   } catch (SQLIntegrityConstraintViolationException var9) {
      out.print("Please select any party");
      rd = request.getRequestDispatcher("vote.html");
      rd.include(request, response);
   } catch (Exception var10) {
      out.print(" " + var10);
      rd = request.getRequestDispatcher("vote.html");
      rd.include(request, response);
   }

   out.close();
}

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request, response);
}

static boolean checkLogin(String card) throws SQLException {
   boolean r = false;
   ps = con.prepareStatement("Select * from voters where cardno = ?");
   ps.setString(1, card);
   ResultSet rs = ps.executeQuery();
   r = rs.next();
   return r;
}
}

