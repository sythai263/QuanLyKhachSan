package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import DieuKhien.KetNoiDB;

public class ChiTietPhieuThue {
	private String MaPhong;
	private int MaPT;
	private Date NgayDi, NgayDen ;
	public String getMaPhong() {
		return MaPhong;
	}
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}
	public int getMaPT() {
		return MaPT;
	}
	public void setMaPT(int maPT) {
		MaPT = maPT;
	}
	public Date getNgayDen() {
		return NgayDen;
	}
	public void setNgayDen(Date ngayDen) {
		NgayDen = ngayDen;
	}
	public Date getNgayDi() {
		return NgayDi;
	}
	public void setNgayDi(Date ngayDi) {
		NgayDi = ngayDi;
	}
	public void taoCTPT() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "INSERT INTO CTPT VALUES (?,?,?,?)";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.setInt(1, getMaPT());
			ps.setString(2, getMaPhong());
			ps.setDate(3, getNgayDen());
			ps.setDate(4, getNgayDi());
			ps.executeUpdate();
			System.out.println("Tạo chi tiết phiếu thuê thành công ! - Close !");
			ps.close();
			ketNoi.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public ChiTietPhieuThue(int maPT, String maPhong, Date ngayDen, Date ngayDi) {
		super();
		MaPT = maPT;
		MaPhong = maPhong;
		NgayDen = ngayDen;
		NgayDi = ngayDi;
	}
	
	
}
