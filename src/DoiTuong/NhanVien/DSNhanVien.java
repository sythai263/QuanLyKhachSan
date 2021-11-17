package DoiTuong.NhanVien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DSNhanVien {
	private static ArrayList<TaiKhoan> danhSachTK = new ArrayList<TaiKhoan>();
	private static ArrayList<ThongTinNV> danhSachNV = new ArrayList<ThongTinNV>();
	
	public static void layDanhSach() {
		Connection ketNoi = DieuKhien.KetNoiDB.ketNoi();
		String sql = "SELECT * FROM [dbo].[NhanVien]";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan nv = new TaiKhoan(rs.getString("MaNV"),
											rs.getString("MatKhau"));
				danhSachTK.add(nv);
				String maNV = rs.getNString("MaNV");
				String ho = rs.getNString("Ho");
				String ten = rs.getNString("Ten");
				String gt = rs.getNString("GioiTinh");
				Date ngaySinh = rs.getDate("NgaySinh");
				String sdt = rs.getString("SDT");
				String email = rs.getString("Email");
				String maBP = rs.getString("MaBP");
				String matKhau = rs.getString("MatKhau");
				ThongTinNV nv1 = new ThongTinNV(maNV, ho, ten, gt, ngaySinh, sdt, email, maBP, matKhau);
				danhSachNV.add(nv1);
			}
			ps.close();
			rs.close();
			ketNoi.close();
			System.out.println("Đóng kết nối !");
			
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public static ArrayList<TaiKhoan> getDanhsach() {
		return danhSachTK;
	}
	public static ArrayList<ThongTinNV> getDanhSachNV() {
		return danhSachNV;
	}
	
	
	

}
