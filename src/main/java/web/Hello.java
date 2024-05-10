package web;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/request.html")
public class Hello extends Serv {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String dburl = "jdbc:mariadb://192.168.0.2:3306/db1";
			String dbUserId = "root";
			String dbPassword = "zerobase";

			Class.forName("org.mariadb.jdbc.Driver");

			Connection connection = DriverManager.getConnection(dburl, dbUserId, dbPassword);
			Statement statement = connection.createStatement();

			int first = 1;
			int last = 1000;
			int num = 0;
			int cnt = 0;

			while (true) {

				String key = "7442695347676b6a3535464b6a4263";
				String url = "http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/" + first + "/" + last
						+ "/";

				OkHttpClient client = new OkHttpClient();
				Request.Builder builder = new Request.Builder().url(url).get();
				Request req = builder.build();
				Response res = client.newCall(req).execute();

				if (res.isSuccessful()) {
					ResponseBody body = res.body();
					
					if (body != null) {

						String s = body.string();

						JsonElement element = JsonParser.parseString(s);
						JsonArray array = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row")
								.getAsJsonArray();
						num = array.size();
						cnt += num;

						for (int i = 0; i < num; i++) {
							JsonObject obj = array.get(i).getAsJsonObject();

							String query = "insert into wifi\r\n"
									+ "(num,gu,wifi_name,address,address_detail,wifi_floor,install_type"
									+ ",owner,service_type,network_type,install_year,inside,environ,X_num,Y_num,date)"
									+ " values " + "('" + obj.get("X_SWIFI_MGR_NO").getAsString().replace("'", "") + "'"
									+ ",'" + obj.get("X_SWIFI_WRDOFC").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_MAIN_NM").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_ADRES1").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_ADRES2").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_INSTL_FLOOR").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_INSTL_TY").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_INSTL_MBY").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_SVC_SE").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_CMCWR").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_CNSTC_YEAR").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_INOUT_DOOR").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("X_SWIFI_REMARS3").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("LAT").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("LNT").getAsString().replace("'", "") + "'" + ",'"
									+ obj.get("WORK_DTTM").getAsString().replace("'", "") + "');";

							statement.executeQuery(query);
						}

					} else {
						break;
					}

				} else {
					break;
				}

				if (num < 1000) {
					break;
				}

				first += 1000;
				last += 1000;
			}

			statement.close();
			connection.close();

			request.setAttribute("cnt", cnt);
			RequestDispatcher rd = request.getRequestDispatcher("/get.jsp");
			rd.forward(request, response);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}