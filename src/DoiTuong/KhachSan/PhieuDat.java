package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DieuKhien.KetNoiDB;
import HienThi.DangNhap;

public class PhieuDat {
	private String soCCCD = "", maNV=DangNhap.getLogin().getMaNV() , loi = "";
	int maPhieu= 0, soNgay =0;
	java.sql.Date ngayThue;
	

	public int getSoNgay() {
		return soNgay;
	}

	public void setSoNgay(int soNgay) {
		this.soNgay = soNgay;
	}

	public java.sql.Date getNgayThue() {
		return ngayThue;
	}

	public void setNgayThue(java.sql.Date ngayThue) {
		this.ngayThue = ngayThue;
	}

	public int getMaPhieu() {
		return maPhieu;
	}

	public void setMaPhieu(int maPhieu) {
		this.maPhieu = maPhieu;
	}

	public String getSoCCCD() {
		return soCCCD;
	}

	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}

	public String getMaNV() {
		return maNV;
	}
	
	public String getLoi() {
		return loi;
	}
	

	

	public PhieuDat(String soCCCD, Date ngayThue, int soNgay) {
		super();
		this.soCCCD = soCCCD;
		this.soNgay = soNgay;
		this.ngayThue = ngayThue;
	}
	

	public PhieuDat(String soCCCD, int soNgay) {
		super();
		this.soCCCD = soCCCD; 
		this.soNgay = soNgay;
	}

	public PhieuDat() {
		super();
	}

	public void taoPhieuDat(String maPh) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "INSERT INTO PhieuDat VALUES ( '"+soCCCD+"', '"+getMaNV()+ "') SELECT SCOPE_IDENTITY() AS LastID";
		try {
			PreparedStatement ps1 = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps1.execute();
			ResultSet rs = ps1.getGeneratedKeys();
			if (rs.next()) {
				setMaPhieu(rs.getInt(1));
			}
			ps1.close();
			rs.close();
			System.out.println("Tạo phiếu đặt thành công ! - Close !");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ChiTietPhieuDat ctpd = new ChiTietPhieuDat(getMaPhieu(), layHangPhong(maPh), 1, getNgayThue(), getSoNgay());
		ctpd.taoCTPD();
	}
	public String layHangPhong(String maPh) {
		String hp = "";
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM Phong WHERE MaPhong ='"+ maPh+"'";
		try {
			PreparedStatement ps1 = ketNoi.prepareStatement(sql);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				hp = rs.getString("HangPhong");
			}
			ps1.close();
			rs.close();
			System.out.println("Tạo phiếu đặt thành công ! - Close !");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return hp;
	}

}
