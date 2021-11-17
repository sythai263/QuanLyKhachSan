package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DieuKhien.KetNoiDB;

public class CTDV {
	private ArrayList<DichVu> dv = new ArrayList<DichVu>();
	
	private int maPT, maHD, maPD, soNgayO;
	
	public ArrayList<DichVu> getDv() {
		return dv;
	}

	public void setDv(ArrayList<DichVu> dv) {
		this.dv = dv;
	}

	public int getMaPT() {
		return maPT;
	}

	public void setMaPT(int maPT) {
		this.maPT = maPT;
	}

	public CTDV(int maPT, ArrayList<DichVu> dv) {
		super();
		this.dv = dv;
		this.maPT = maPT;
	}
	
	
	public CTDV() {
		super();
	}


	public void LuuCTDV() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "INSERT INTO CTDV VALUES (?,?)";
		PreparedStatement ps;
		try {
			System.out.println("Đang thêm dịch vụ cho KH!");
			 ps = ketNoi.prepareStatement(sql);
			for (DichVu dichvu: dv) {
				
				 ps.setInt(1, getMaPT());
				 ps.setString(2, dichvu.getMaDV());
				 ps.executeUpdate();
				 
			}
			TongTien();
			ps.close();
			ketNoi.close();
			System.out.println("Đã cập nhật các dịch vụ cho KH - Close !");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void layMaHD() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT MaHD, MaPD FROM PhieuThue WHERE MaPT = '"+maPT+"'";
		PreparedStatement ps;
		try {
			System.out.println("Lấy Thông tin hóa đơn.");
			ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.maHD = rs.getInt("MaHD");
				this.maPD = rs.getInt("MaPD");
			}
			ps.close();
			ketNoi.close();
			
			String laySoNgayO= "SELECT SoNgayO FROM CTPD WHERE MaPD ='"+this.maPD+"'";
			ResultSet rs1 = ps.executeQuery(laySoNgayO);
			if (rs.next()) {
				this.soNgayO= rs.getInt("SoNgayO");
			}
			
			System.out.println("Đã cập nhật các dịch vụ cho KH - Close !");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public int tinhTien() {
		layMaHD();
		int tongTien = 0;
		for (DichVu a: dv) {
			tongTien+= soNgayO*a.getDonGia();
		}
		
		return tongTien;
	}

	public void TongTien() {
		layMaHD();
		Calendar cal = Calendar.getInstance();
		java.sql.Date ngay = new java.sql.Date(cal.getTime().getTime());
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE HoaDon SET NgayLapHD ='"+ngay+"', TongTien= '"+tinhTien()+"' "
				+ "WHERE MaHD = '"+maHD+"'";
		PreparedStatement ps;
		try {
			ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			
			ps.close();
			ketNoi.close();
			System.out.println("Đã cập nhật hóa đơn - Close !");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
