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

@WebServlet("/mark.html")
public class Mark extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "select * from mark;";
			ResultSet rs = statement.executeQuery(query);
			
			String html = "";
			
			html += "<tr style=\"color:white; font-weight : bold;\" bgcolor=\"#3cb371\">"
					+ "<td width=\"10%\">ID</td><td width=\"30%\">북마크 이름</td><td width=\"25%\">와이파이명</td>"
					+ "<td width=\"25%\">등록일자</td><td width=\"10%\">비고</td></tr>";
			
			int c = 0;
			while (rs.next()) {
				String bg = (c % 2 == 0) ? "white" : "#EEEEEE";
				html += "<tr bgcolor=\"" + bg + "\">";
				for (int i = 1; i < 5; i++) {
					html += "<td>";
					
					if (i == 2) {
						if (rs.getString(i) == null) {
							html += "";
						}
						else {
							html += "<a href=\"detail.html?id=" + rs.getString(5) + "\">" + rs.getString(i) + "</a>";
						}
					}
					
					else {
						if (rs.getString(i) == null) {
							html += "";
						}
						else {
							html += rs.getString(i);
						}
					}
					
					html += "</td>";
				}
				html += "<td><a href=\"mark_delete.jsp?id=" + rs.getString(5) + "&group=" + rs.getString(2) + "&name="
				+ rs.getString(3) + "&date=" + rs.getString(4) + "\">삭제</a></td>";
				html += "</tr>";
			}

			statement.close();
			connection.close();
			
			request.setAttribute("html", html);
			request.getRequestDispatcher("mark.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}