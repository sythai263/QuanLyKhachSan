package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import DieuKhien.KetNoiDB;
import HienThi.DangNhap;

public class PhieuThue {
	private String soCMND,MaPhong,tenKH, MaNV = DangNhap.getLogin().getMaNV();
	private int MaPD, MaPT, soNgay= 0;
	private java.sql.Date ngayDen, ngayDi;
	
	
 	public int getSoNgay() {
		return soNgay;
	}
	public void setSoNgay(int soNgay) {
		this.soNgay = soNgay;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String ten) {
		this.tenKH = ten;
	}
	public java.sql.Date getNgayDen() {
		return ngayDen;
	}
	public void setNgayDen(java.sql.Date ngayDen) {
		this.ngayDen = ngayDen;
	}
	public java.sql.Date getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(java.sql.Date ngayDi) {
		this.ngayDi = ngayDi;
	}
	public String getMaPhong() {
		return MaPhong;
	}
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public int getMaPD() {
		return MaPD;
	}
	public void setMaPD(int maPD) {
		MaPD = maPD;
	}
	public int getMaPT() {
		return MaPT;
	}
	public void setMaPT(int maPT) {
		MaPT = maPT;
	}
	

	public PhieuThue(String soCMND, String maPhong, int maPD, int soNgay, Date ngayDen, Date ngayDi) {
		super();
		this.soCMND = soCMND;
		MaPhong = maPhong;
		MaPD = maPD;
		this.soNgay = soNgay;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
	}
	public void taoPhieuThue() {
		
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "INSERT INTO PhieuThue VALUES ('"+ getSoCMND()+"', "+
													"'"+ getMaNV()+"', "+ 
													"'"+ getMaPD()+"', null ) SELECT SCOPE_IDENTITY()";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				setMaPT(rs.getInt(1));
			}
			ChiTietPhieuThue ctpt = new ChiTietPhieuThue(getMaPT(),getMaPhong(), getNgayDen(), getNgayDi() );
			ctpt.taoCTPT();
			System.out.println("Tạo phiếu thuê thành công ! - Close !");
			ps.close();
			rs.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
