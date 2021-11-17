package DieuKhien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DoiTuong.KhachSan.PanelPhong;
import DoiTuong.KhachSan.Phong;
import HienThi.DSPhong;
import HienThi.DangNhap;
import HienThi.TrangChinh;

public class CapNhatDB {
	public static java.sql.Date  NgayHTai(){
		long millis = System.currentTimeMillis();
		java.sql.Date now = new java.sql.Date(millis);
		return now;
		
	}
	
	
	public static void thanhToanHD(int maHD, int Tong) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE HoaDon SET NgayLapHD = '"+NgayHTai()+
					"', MaNV = '"+DangNhap.getLogin().getMaNV()+
					"', TongTien= '"+Tong+"', TrangThai ='1' WHERE MaHD = '"+maHD+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Thanh toán hóa đơn thành công ! - Close !");
			ps.close();
			ketNoi.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void xoaCTDV(int maPT) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "DELETE FROM CTDV WHERE MaPT = '"+maPT+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			System.out.println("Cập nhật trạng thái phiếu thuê thành công ! - Close !");
			ps.close();
			ketNoi.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static int tinhSoNgayO(int maPT){
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT NgayDen FROM CTPT WHERE MaPT = '"+ maPT+"'";
		int soNgayO=1;
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				java.sql.Date ngayDen= rs.getDate("NgayDen");
	        	soNgayO = (int) (NgayHTai().getTime() - ngayDen.getTime())/(24*3600*1000);
	        	if (soNgayO <=1) {
	        		soNgayO =1;
	        	}
			}
			ps.close();
			ketNoi.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return soNgayO;
	}

	public static void thayDoiTTPhong(String maPhong, String trangThai) {
		
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE Phong SET MaTrangThai= '"+ trangThai+"' WHERE MaPhong='"+maPhong+"'";
		try {
			
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			ketNoi.close();
			for (PanelPhong pn : DSPhong.getDSPanel()) {
				if (pn.getPhong().getMaPhong().equals(maPhong)){
					pn.getPhong().setMaTrangThai(trangThai);
					pn.getPhong().updateTT(trangThai);
					pn.refreshPanelPhong();
				}
			}
			TrangChinh.CapNhatThongTinPhong();
			System.out.println("Đã cập nhật thông tin phòng thành công - Close !");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int layMaPT(String maPhong) {
		
		int maPT = 0;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT TOP 1 MaPT FROM CTPT WHERE MaPhong='"+maPhong+"' ORDER BY MaPT DESC";
		
		
		try {
			System.out.println("Lấy thông tin phiếu thuê !");
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				maPT = rs.getInt(1);
			}
			rs.close();
			ps.close();
			ketNoi.close();
			System.out.println("Đã lấy thông tin phiếu thuê thành công. - Close !");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return maPT;
		
	}
	public static void giaHanPhong(String maPhong, int soNgay) {
		
		
		java.sql.Date ngayDi = CapNhatDB.NgayHTai();
		int maPT = 0, maPD = 0;
		Connection ketNoi = KetNoiDB.ketNoi();
		
		String sql = "SELECT CTPT.MaPT, CTPT.NgayDi, PhieuThue.MaPD FROM CTPT inner join PhieuThue on CTPT.MaPT = PhieuThue.MaPT "
				+ "WHERE CTPT.MaPhong = '"+maPhong
				+"' AND DATEDIFF(DAY,CTPT.NgayDen, GETDATE()) >=0  AND DATEDIFF(DAY,CTPT.NgayDi, GETDATE()) <=0";
		
		try {
			
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				maPT= rs.getInt("MaPT");
				maPD= rs.getInt("MaPD");
				ngayDi = rs.getDate("NgayDi");
			}
			
			long di = ngayDi.getTime() + soNgay*24*3600*1000;
			java.sql.Date ngayDiMoi = new java.sql.Date(di);
			
			
			updateCTPD(maPD, soNgay + laySoNgayO(maPD));
			updateCTPT(maPT, ngayDiMoi);
			TrangChinh.CapNhatThongTinPhong();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateCTPT(int maPT, java.sql.Date ngayDi) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE CTPT SET CTPT.NgayDi ='"+ngayDi+"' WHERE MaPT= '"+maPT+"'" ;
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			ketNoi.close();
			System.out.println( ngayDi + "  Cập nhật lại ngày thuê mới !" + maPT);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCTPD(int maPD, int soNgay) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE CTPD SET CTPD.SoNgayO ='"+soNgay+"' WHERE MaPD= '"+maPD+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			ketNoi.close();
			System.out.println("Cập nhật lại thông tin phiếu đặt");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int laySoNgayO(int maPD) {
		int so = 0;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPD WHERE MaPD= '"+maPD+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				so = rs.getInt("SoNgayO");
			}
			ps.close();
			ketNoi.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return so;
	}
	
	public static void updatePhieuThue(int maPT, int maHD) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "UPDATE PhieuThue SET MaHD ='"+maHD+"' WHERE MaPT = '"+maPT+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			ketNoi.close();
			System.out.println("Cập nhật lại thông tin phiếu thuê xong. ");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
