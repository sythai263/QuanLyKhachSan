package DoiTuong.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DieuKhien.KetNoiDB;
import HienThi.DangNhap;

import java.sql.Date;

public class ThongTinNV {
	protected static String MaNV, MatKhau;
	private static String 	Ho ,
							Ten ,
							GioiTinh ,
							SDT ,
							Email ,
							MaBP ;
	private static java.sql.Date NgaySinh ;
	
	
	public static String getMaNV() {
		return MaNV;
	}
	public static void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public static String getHo() {
		return Ho;
	}
	public static void setHo(String ho) {
		Ho = ho;
	}
	public static String getTen() {
		return Ten;
	}
	public static void setTen(String ten) {
		Ten = ten;
	}
	public static String getSDT() {
		return SDT;
	}
	public static void setSDT(String sDT) {
		SDT = sDT;
	}
	public static String getGioiTinh() {
		return GioiTinh;
	}
	public static void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public static String getEmail() {
		return Email;
	}
	public static void setEmail(String email) {
		Email = email;
	}
	public static String getMaBP() {
		return MaBP;
	}
	public static void setMaBP(String maBP) {
		MaBP = maBP;
	}
	public static String getMatKhau() {
		return MatKhau;
	}
	public static void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public static java.sql.Date getNgaySinh() {
		return NgaySinh;
	}
	public static void setNgaySinh(java.sql.Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
	public ThongTinNV() {
		
	}
	
	public ThongTinNV(String maNV,String ho, String ten, String gt, Date ngaySinh,
						String sdt, String email, String maBP, String matKhau) {
		setMaNV(maNV);
		setHo(ho);
		setTen(ten);
		setGioiTinh(gt);
		setNgaySinh(ngaySinh);
		setSDT(sdt);
		setEmail(email);
		setMaBP(maBP);
		setMatKhau(matKhau);
	}
	
	public static boolean luuThongTinNV() {
		Connection ketNoi = DieuKhien.KetNoiDB.ketNoi();
		String sql = "INSERT INTO NhanVien VALUES (?,?,?,?,?,?,?,?,?)";
		boolean kq = false;
		
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.setString(1, getMaNV());
			ps.setString(2, getHo());
			ps.setString(3, getTen());
			ps.setString(4, getGioiTinh());
			ps.setDate(5, getNgaySinh());
			ps.setString(6, getEmail());
			ps.setString(7, getSDT());
			ps.setString(8, getMaBP());
			ps.setString(9, getMatKhau());
			ps.executeUpdate();
			kq= true;
			ps.close();
			ketNoi.close();
			System.out.println("Thêm dữ liệu nhân viên thành công !");
		}catch(SQLException ex) {
			ex.printStackTrace();
			kq = false;
		}catch (Exception e) {
			e.printStackTrace();
			kq= false;
		}
		
		return kq;
	}
	

}
