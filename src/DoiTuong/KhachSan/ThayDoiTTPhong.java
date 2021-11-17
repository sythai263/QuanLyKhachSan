package DoiTuong.KhachSan;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HienThi.DSPhong;
import HienThi.TrangChinh;
import HienThi.theme.ThemeDefault;
import jdk.jshell.spi.ExecutionControl.ExecutionControlException;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThayDoiTTPhong extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaPhong;
	private JLabel lblTrangthai;
	private Phong idPhong = new Phong();
	private ArrayList<TrangThai> DSTT = new ArrayList<TrangThai>();
	private JLabel lblTTmoi = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThayDoiTTPhong frame = new ThayDoiTTPhong("EC12");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThayDoiTTPhong( String maPhong) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setTitle("Thay \u0111\u1ED5i th\u00F4ng tin ph\u00F2ng");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 341);
		contentPane = new JPanel();
		contentPane.setBackground(ThemeDefault.getBackground_color());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("TH\u00D4NG TIN PH\u00D2NG");
		lblTitle.setIcon(new ImageIcon("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\datphong_white.png"));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(101, 11, 292, 62);
		lblTitle.setFont(ThemeDefault.getFont_text_title());
		lblTitle.setForeground(ThemeDefault.getText_color_white());
		contentPane.add(lblTitle);
		
		txtMaPhong = new JTextField(maPhong);
		CheckMaPhong();
		txtMaPhong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				CheckMaPhong();
				TrangChinh.CapNhatThongTinPhong();
			}
		});
		txtMaPhong.setBounds(48, 113, 195, 44);
		contentPane.add(txtMaPhong);
		txtMaPhong.setFont(ThemeDefault.getFont_text_field());
		txtMaPhong.setBorder(new TitledBorder(null, "Mã Phòng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		txtMaPhong.setColumns(10);
		
		lblTrangthai = new JLabel("");
		lblTrangthai.setForeground(Color.WHITE);
		lblTrangthai.setBackground(Color.WHITE);
		lblTrangthai.setBounds(253, 113, 195, 44);
		lblTrangthai.setFont(ThemeDefault.getFont_text_label());
		lblTrangthai.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), 
				"Trạng thái hiện tại", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		contentPane.add(lblTrangthai);
		
		JComboBox cbxTT = new JComboBox();
		cbxTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(TrangThai a: DSTT) {
					if (a.getMaTT().equals(cbxTT.getSelectedItem())) {
						lblTTmoi.setText(a.getTenTT());
					}
				}
			}
		});
		getDSTrangThai();
		for (TrangThai tt: DSTT) {
			cbxTT.addItem(tt.getMaTT());
		}
		cbxTT.setFont(ThemeDefault.getFont_text_label());
		cbxTT.setBounds(48, 180, 195, 40);
		contentPane.add(cbxTT);
		
		lblTTmoi.setText(DSTT.get(0).getTenTT());
		lblTTmoi.setForeground(Color.WHITE);
		lblTTmoi.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Trạng thái mới", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		lblTTmoi.setBackground(Color.WHITE);
		lblTTmoi.setFont(ThemeDefault.getFont_text_label());
		lblTTmoi.setBounds(253, 180, 195, 40);
		contentPane.add(lblTTmoi);
		
		JButton btnOK = new JButton("Xác nhận");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaTT = (String) cbxTT.getSelectedItem();
				if (MaTT.equalsIgnoreCase("CK") || MaTT.equalsIgnoreCase("DT") ) {
					JOptionPane.showMessageDialog(null, "Không được thay đổi thành trạng thái có khách/ đặt trước !", "Thông báo", 1);
				}else {
					idPhong.updateTT(MaTT);
					JOptionPane.showMessageDialog(null, "Thay đổi thành công !", "Thông báo", 1);
					dispose();
					DSPhong.refreshRoom(txtMaPhong.getText());
					System.out.println("Đã thay đổi trạng thái phòng thành công !");
				}
				
				
			}
		});
		btnOK.setBounds(202, 243, 89, 23);
		contentPane.add(btnOK);
	}
	private void getDSTrangThai() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM TrangThai";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TrangThai tt = new TrangThai(rs.getString("MaTrangThai"), rs.getNString("TenTrangThai"));
				DSTT.add(tt);
			}
			
			rs.close();
			ps.close();
			ketNoi.close();
			
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void CheckMaPhong() {
		
		if(KiemTra.MaPhong(txtMaPhong.getText()) != 1) {
			JOptionPane.showMessageDialog(null, "Mã phòng chỉ bao gồm 2 ký tự chữ và 2 ký tự số. Ví dụ (EC11,...) !", 
					"Cảnh báo", 1);
		}else {
			getTTphong(txtMaPhong.getText());
			
		}
		
	}
	private void getTTphong(String maPhong) {
		String sql = "SELECT * FROM Phong inner join TrangThai on Phong.MaTrangThai = TrangThai.MaTrangThai "
				+ "WHERE Phong.MaPhong = '"+maPhong+"'";
		Connection ketNoi = KetNoiDB.ketNoi();
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs == null ) {
				JOptionPane.showMessageDialog(null, "Không có mã phòng này. Thử lại !", "Thông báo", 1);
			} else if (rs.next()) {
				idPhong.setMaPhong(rs.getString("MaPhong"));
				idPhong.setHangPhong(rs.getString("HangPhong"));
				idPhong.setMaTrangThai(rs.getString("MaTrangThai"));
				idPhong.setTenTrangThai(rs.getNString("TenTrangThai"));
				lblTrangthai.setText(idPhong.getTenTrangThai());
			}
			
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception ex) {
			System.out.print(ex.getMessage());
			
		}
		
	}
	
}
