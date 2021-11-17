package DoiTuong.KhachSan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import HienThi.DangNhap;

public class HoaDon {
	private int maHD, gia =0;
	private Date ngayLapHD;
	private String maNV = DangNhap.getLogin().getMaNV();
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public Date getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	
	public void taoMaHD() {
		Connection ketNoi = KetNoiDB.ketNoi();
		java.sql.Date now = CapNhatDB.NgayHTai();
		String sql = "INSERT INTO HoaDon VALUES ('"+ now +"','"+getMaNV()+"', '"+gia+"', '1') SELECT SCOPE_IDENTITY()";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				setMaHD(rs.getInt(1));
			}
			System.out.println("Tạo hóa đơn thành công !");
			ps.close();
			ketNoi.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
