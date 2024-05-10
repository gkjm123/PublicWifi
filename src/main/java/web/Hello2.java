package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/request2.html")
public class Hello2 extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "select * from history order by search_date desc;";
			ResultSet rs = statement.executeQuery(query);
			
			rs.last();
			int cnt = rs.getRow();
			rs.beforeFirst();
			
			String html = "";
			
			int c = 0;
			while (rs.next()) {
				c++;
				String bg = (c % 2 == 1) ? "white" : "#EEEEEE";
				html += "<tr bgcolor=\"" + bg + "\">";
				html += "<td>" + cnt + "</td>";
				for (int i = 2; i < 5; i++) {
					html += "<td>";
					html += rs.getString(i);
					html += "</td>";
				}
				html += "<td><button onclick=\"location.href='request3.html?id=" + rs.getString(1) + "'\">삭제</button></td>";
				html += "</tr>";
				cnt -= 1;
			}

			statement.close();
			connection.close();

			request.setAttribute("html", html);
			request.getRequestDispatcher("history.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}