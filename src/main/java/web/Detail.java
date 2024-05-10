package web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

@WebServlet("/detail.html")
public class Detail extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String id = request.getParameter("id");
			
			Class.forName("org.mariadb.jdbc.Driver");

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			String query = "select * from wifi where num = '" + id + "';";
			ResultSet rs = statement.executeQuery(query);
			
			rs.first();
			ResultSetMetaData rsmd = rs.getMetaData();
			int c_num = rsmd.getColumnCount();
			String wifi_name = rs.getString(3);
			
			String html = "";
			String[] c_arr = {"관리번호", "자치구", "와이파이명", "도로명주소", "상세주소",
					"설치위치(층)", "설치유형", "설치기관", "서비스구분", "망종류", "설치년도", "실내외구분", "WIFI접속환경",
					"X좌표", "Y좌표", "작업일자"};
			
			html += "<tr><td style=\"color:white; font-weight : bold;\" bgcolor=\"#3cb371\" width=\"50%\">거리(Km)</td><td width=\"50%\">0.0000</td>";
			
			int c = 0;
			for (int i = 1; i < c_num+1; i++) {
				c++;
				String bg = (c % 2 == 0) ? "white" : "#EEEEEE";
				html += "<tr bgcolor=\"" + bg + "\">";
				html += "<td style=\"color:white; font-weight : bold;\" bgcolor=\"#3cb371\">" + c_arr[i-1] + "</td>";
				html += "<td>";
				html += rs.getString(i);
				html += "</td>";
				html += "</tr>";
			}
			
			
			
			String query1 = "select name from mark_group;";
			ResultSet rs1 = statement.executeQuery(query1);
			
			String html1 = "";
			
			html1 += "<select name=\"mark_group\">";
			html1 += "<option value=\"none\">북마크 그룹 이름 선택</option>";
			
			while (rs1.next()) {
				html1 += "<option value=\"" + rs1.getString(1) + "\">" + rs1.getString(1) + "</option>";
			}
			
			html1 += "</select>";
			
			

			statement.close();
			connection.close();

			request.setAttribute("html", html);
			request.setAttribute("html1", html1);
			request.setAttribute("wifi_id", id);
			request.setAttribute("wifi_name", wifi_name);
			request.getRequestDispatcher("detail.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}