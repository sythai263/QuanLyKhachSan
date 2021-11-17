package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import DieuKhien.KetNoiDB;

public class ChiTietPhieuDat {
	private int MaPhieuDat, SL, SoNgayThue;
	private String HangPhong;
	private Date NgayBD;
	public int getMaPhieuDat() {
		return MaPhieuDat;
	}
	public void setMaPhieuDat(int maPhieuDat) {
		MaPhieuDat = maPhieuDat;
	}
	public int getSL() {
		return SL;
	}
	public void setSL(int sL) {
		SL = sL;
	}
	public int getSoNgayThue() {
		return SoNgayThue;
	}
	public void setSoNgayThue(int soNgayThue) {
		SoNgayThue = soNgayThue;
	}
	public String getHangPhong() {
		return HangPhong;
	}
	public void setHangPhong(String hangPhong) {
		HangPhong = hangPhong;
	}
	public java.sql.Date getNgayBD() {
		return NgayBD;
	}
	public void setNgayBD(java.sql.Date ngayBD) {
		NgayBD = ngayBD;
	}
	
	public ChiTietPhieuDat(int maPhieuDat, String hangPhong, int sL, Date ngayBD, int soNgayThue) {
		super();
		MaPhieuDat = maPhieuDat;
		HangPhong = hangPhong;
		SL = sL;
		NgayBD = ngayBD;
		SoNgayThue = soNgayThue;
	}
	
	public void taoCTPD() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String ctpd = "INSERT INTO CTPD VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(ctpd);
			ps.setInt(1, getMaPhieuDat());
			ps.setString(2,getHangPhong());
			ps.setInt(3, getSL());
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis); 
			ps.setDate(4, date);
			ps.setDate(5, getNgayBD());
			ps.setInt(6, getSoNgayThue());
			ps.executeUpdate();
			
			System.out.println("Tạo chi tiết phiếu đặt thành công ! - Close!");
			ps.close();
			ketNoi.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public ChiTietPhieuDat() {
		super();
	}
	
	

}
