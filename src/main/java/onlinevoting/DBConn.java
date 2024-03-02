package onlinevoting;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
static Connection conn = null;

static {
   try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votingdb", "root", "");
      if (!conn.isClosed()) {
         System.out.println("Connection established");
      }
   } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Error in DBConn");
      e.printStackTrace();
   }

}

public DBConn() {
}

public static Connection getDBConnection() {
   return conn;
}
}

