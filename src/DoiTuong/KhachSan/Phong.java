package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DieuKhien.KetNoiDB;


public class Phong {
	private  String MaPhong = "", MaTrangThai = "", HangPhong = "", TenTrangThai = "";
 	
	public  String getMaPhong() {
		return MaPhong;
	}

	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}

	public String getHangPhong() {
		return HangPhong;
	}

	public void setHangPhong(String hangPhong) {
		HangPhong = hangPhong;
	}


	public Phong(String maPhong, String trangThai, String hangPhong, String tenTT) {
		super();
		MaPhong = maPhong;
		MaTrangThai = trangThai;
		HangPhong = hangPhong;
		TenTrangThai = tenTT;
	}
	
	public String getMaTrangThai() {
		return MaTrangThai;
	}
	public void setMaTrangThai(String maTrangThai) {
		MaTrangThai = maTrangThai;
	}
	public void updateTT(String trangthai) {
		setMaTrangThai(trangthai);
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE Phong SET MaTrangThai = '"+ trangthai+"' WHERE MaPhong = '"+ getMaPhong()+"' ";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			refreshTTPhong();
			System.out.println("Cập nhật lại trạng thái phòng !- Close ! ");
			ps.close();
			ketNoi.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTenTrangThai() {
		
		return TenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		TenTrangThai = tenTrangThai;
	}

	public void refreshTTPhong() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT Phong.MaPhong, Phong.MaTrangThai, TrangThai.TenTrangThai "
				+ "FROM Phong inner join TrangThai on Phong.MaTrangThai = TrangThai.MaTrangThai " + 
				"Where MaPhong = '"+getMaPhong()+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setMaTrangThai(rs.getString("MaTrangThai"));
				setTenTrangThai(rs.getNString("TenTrangThai"));
			}
			ps.close();
			ketNoi.close();
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Phong() {
		super();
	}
	
	//private  void 

	
}
