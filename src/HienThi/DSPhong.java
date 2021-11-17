package HienThi;

import javax.swing.JPanel;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import DoiTuong.KhachSan.Phong;
import DoiTuong.KhachSan.PanelPhong;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DSPhong extends JPanel {
	private static ArrayList<PanelPhong> DSPanel = new ArrayList<PanelPhong>();
	private static ArrayList<Phong> DSPhong = new ArrayList<Phong>();

	public static ArrayList<PanelPhong> getDSPanel() {
		return DSPanel;
	}

	public static void setDSPanel(ArrayList<PanelPhong> dSPanel) {
		DSPanel = dSPanel;
	}

	public static ArrayList<Phong> getDSPhong() {
		return DSPhong;
	}

	public static void setDSPhong(ArrayList<Phong> dSPhong) {
		DSPhong = dSPhong;
	}

	/**
	 * Create the panel.
	 */
	public DSPhong() {
		setLayout(new GridLayout(0, 10, 4, 4));
		kiemTraPhong();
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT p.MaPhong, p.HangPhong, tt.MaTrangThai, tt.TenTrangThai "
				+ "FROM Phong p INNER JOIN TrangThai tt "
				+ "ON p.MaTrangThai = tt.MaTrangThai";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Phong p = new Phong();
				p.setMaPhong(rs.getNString("MaPhong"));
				p.setHangPhong(rs.getNString("HangPhong"));
				p.setMaTrangThai(rs.getNString("MaTrangThai"));
				p.setTenTrangThai(rs.getNString("TenTrangThai"));
				DSPhong.add(p);
				PanelPhong pn = new PanelPhong(p);
				DSPanel.add(pn);
				add(pn);
			}
			TrangChinh.CapNhatThongTinPhong();
			ketNoi.close();
			ps.close();
			rs.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void lamMoiTTPhong() {
		for (PanelPhong pn : DSPanel) {
			pn.refreshPanelPhong();
		}
	}
	public static void refreshRoom(String maPhong) {
		for (PanelPhong pn : DSPanel) {
			if (maPhong.equals(pn.getPhong().getMaPhong())) {
				pn.refreshPanelPhong();
			}
		}
	}
	public static void kiemTraPhong() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPT WHERE DATEDIFF(DAY, CTPT.NgayDi, GETDATE() ) > 0";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null ) {
				while (rs.next()) {
					CapNhatDB.thayDoiTTPhong(rs.getString("MaPhong"), "SS");
				}
			}
			ps.close();
			rs.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
