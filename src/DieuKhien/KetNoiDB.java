package DieuKhien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoiDB {
	public static Connection ketNoi(){
        Connection connect = null;
        String uRL= "jdbc:sqlserver://;databaseName=QuanLyKhachSan";
        String userName ="sa";// "Admin";
        String password = "123";//"12abf16fa";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Kết nối CSDL thành công !");
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Không kết nối được cơ sở dữ liệu.");
        }
            
        return connect;
    }
    

}
