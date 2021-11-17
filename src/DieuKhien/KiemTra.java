package DieuKhien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import com.sun.net.httpserver.Authenticator.Result;

public class KiemTra {
	private static String 	pattenSDT = "^[0-9]*$",
			
					pattenEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
		            
		            pattenPasword="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})",
		            
		            pattenMaNV = "^\\w{2}\\d{3}$",
		            pattenMaPhong = "^\\w{2}\\d{2}$",
		            pattenCCCD = "^[0-9]*$";
		            
	public static int MaPhong(String maPhong) {
		//Kiểm tra chuỗi có kí tự chưa
		//ngược lại nếu không phải rỗng thì 
		// kiểm tra định dạng
		// không đúng trả về 0
		int kq= 1;
		if (maPhong.length() == 0 ) {
			kq = -1;
		} else if (!maPhong.matches(pattenMaPhong)) {
				kq = 0;
		} else kq =1;
		return kq;
	}
	
	public static int MaNV(String manv) {
		//Kiểm tra chuỗi có kí tự chưa
		//ngược lại nếu không phải rỗng thì 
		// kiểm tra định dạng
		// không đúng trả về 0
		int kq= 1;
		if (manv.length() == 0 ) {
			kq = -1;
		} else if (!manv.matches(pattenMaNV)) {
				kq = 0;
		} else kq =1;
		return kq;
	}

	public static int MatKhau(String matkhau) {
		int kq= 1;
		if (matkhau.length() == 0 ) {
			kq = -1;
		}else {
			//ngược lại nếu không phải rỗng thì 
			// kiểm tra định dạng
			// không đúng trả về 0
			if (!matkhau.matches(pattenPasword)) {
				kq = 0;
			} else kq =1;
		}
		
		return kq;
	}
	
	public static int Email(String email) {
		int kq= 0;
			// kiểm tra định dạng
			// không đúng trả về 0
		if (email.length() ==0) {
			kq=-1;
		}else {
			if (email.matches(pattenEmail)) {
				kq=1;
			}
		}
		return kq;
	}

	public static int SDT(String sdt) {
		int kq = 0;
		if (sdt.length() == 0) {
			kq=-1;
			
		}else {
			if (!sdt.matches(pattenSDT)) {
				kq=-2;
			}else {
				if (sdt.length() != 10) {
					kq = -3;
				}else {
					if ((sdt.substring(0, 2).equals("03")) ||
	                    (sdt.substring(0, 2).equals("05")) ||
	                    (sdt.substring(0, 2).equals("07")) ||
	                    (sdt.substring(0, 2).equals("08")) ||
	                    (sdt.substring(0, 2).equals("09"))){
						kq =1;
	                }else{
	                	kq =0;
	                }
	                
				}
				
			}
		}
		return kq;
	}
	
	public static int CCCD(String cccd) {
		int kq = 0;
		if (!cccd.matches(pattenCCCD)) {
			kq = -1;
		}else if ((cccd.length()==9) || (cccd.length()== 12)) {
			kq = 1;
		}else kq = 0;
		return kq;
	}
	
	public static boolean kiemTraPhieuDat() {
		boolean kt = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPD WHERE DATEDIFF(DAY, CTPD.NgayBDThue, GETDATE() ) = 1";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kt = true;
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return kt;
	}
	public static boolean KTMaPhong(String maPhong) {
		boolean kt = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM Phong WHERE MaPhong ='"+maPhong+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next() ) kt = true;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return kt;
	}

	
}
