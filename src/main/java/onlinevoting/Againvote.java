package onlinevoting;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/againvote"})
public class Againvote extends HttpServlet {
private static final long serialVersionUID = 1L;


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doGet(request, response);
}
}
