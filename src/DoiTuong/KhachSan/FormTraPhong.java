package DoiTuong.KhachSan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;
import DoiTuong.KhachHang.TraPhong;
import HienThi.theme.ThemeDefault;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;

public class FormTraPhong extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTimKiem;
	private String patten = "^[0-9]*$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormTraPhong dialog = new FormTraPhong();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormTraPhong() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setTitle("T\u00ECm ki\u1EBFm");
		setBounds(100, 100, 400, 221);
		setLocationRelativeTo(null);
		setBackground(ThemeDefault.getMenu_background());
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(getBackground());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(getContentPane().getBackground());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel Title = new JPanel();
		Title.setBounds(0, 0, 384, 44);
		Title.setBackground(getBackground());
		contentPanel.add(Title);
		
		JLabel lblTitle = new JLabel("T\u00ECm ki\u1EBFm");
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(ThemeDefault.getFont_text_title());
		lblTitle.setForeground(ThemeDefault.getText_color_white());
		Title.add(lblTitle);
		
		JPanel Khung = new JPanel();
		Khung.setBounds(39, 58, 306, 62);
		Khung.setBackground(Color.WHITE);
		
		contentPanel.add(Khung);
		Khung.setLayout(new GridLayout(2, 1, 20, 5));
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp m\u00E3 phi\u1EBFu thu\u00EA, m\u00E3 ph\u00F2ng, m\u00E3 phi\u1EBFu \u0111\u1EB7t");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(ThemeDefault.getFont_text_label());
		Khung.add(lblNewLabel);
		
		JPanel timkiem = new JPanel();
		Khung.add(timkiem);
		
		txtTimKiem = new JTextField();
		timkiem.add(txtTimKiem);
		txtTimKiem.setColumns(20);
		txtTimKiem.setFont(ThemeDefault.getFont_text_field());
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBackground(getContentPane().getBackground());
			{
				JButton okButton = new JButton("T\u00ECm");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//mã phòng
						if (txtTimKiem.getText().length() == 4) {
							if (!KiemTra.KTMaPhong(txtTimKiem.getText())) {
								JOptionPane.showMessageDialog(null, "Sai mã phòng !");
							} else {
								if (!KQTimPhong(txtTimKiem.getText())) {
									JOptionPane.showMessageDialog(null, "Hiện tại phòng này chưa có người thuê !");
								} else {
									TraPhong tp = new TraPhong(txtTimKiem.getText());
									tp.setVisible(true);
									tp.setLocationRelativeTo(null);
									dispose();
								}
							}
						}else if (txtTimKiem.getText().matches(patten)) {
							int maPD = Integer.valueOf(txtTimKiem.getText());
							switch (txtTimKiem.getText().length()) {
							case 9: 
								//Phiếu đặt
								if (KQTK(maPD, "PhieuDat", "MaPD")) {
									if (!KiemTraPT(txtTimKiem.getText())) {
										XacNhanTP tp = new XacNhanTP(txtTimKiem.getText());
										tp.setLocationRelativeTo(null);
										tp.setVisible(true);
										dispose();
									}else {
										JOptionPane.showMessageDialog(null, "Phiếu này đã được thuê !");
									}
								} else {
									JOptionPane.showMessageDialog(null, "Không tìm thấy mã phiếu đặt này !");
								}
								
								
								break;
							case 7:
								//Phiếu thuê
								if (KQTK(maPD, "PhieuThue", "MaPT")) {
									TraPhong tp = new TraPhong(txtTimKiem.getText());
									tp.setLocationRelativeTo(null);
									tp.setVisible(true);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Không tìm thấy mã phiếu thuê này !");
								}
								
								break;
							case 8:
								//Hóa đơn
								
								break;
							default:
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã phiếu nào phù hợp !");
								break;
									
							}
						}else {
							JOptionPane.showMessageDialog(null, "Hãy nhập một chuỗi số !");
						}
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("H\u1EE7y");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private boolean KQTK(int ma, String noiTim, String cot) {
		boolean kq = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM " +noiTim+" WHERE "+cot+" ='"+ma+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Đang tìm thông tin phiếu cho mã "+ma+"\nOK - close");
				kq= true;
			}
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return kq;
	}
	private boolean KQTimPhong(String maPhong) {
		boolean kq = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT TOP 1 PhieuThue.MaPT, CTPT.MaPhong, HoaDon.MaHD "
				+ "FROM PhieuThue inner join CTPT on PhieuThue.MaPT = CTPT.MaPT "
				+ "inner join HoaDon on PhieuThue.MaHD = HoaDon.MaHD "
				+ "where MaPhong = '"+maPhong+"' and HoaDon.TongTien = 0 ORDER BY MaPT";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				kq= true;
				System.out.println("Đang tìm thông tin phiếu cho phòng "+maPhong+"\nOK - close");
			}
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return kq;
	}
	private boolean KiemTraPT(String maPD) {
		boolean kq = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM PhieuThue WHERE MaPD='"+maPD+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Kiểm tra phiếu đã thuê chưa !\nOK - close");
				kq= true;
			}
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		
		return kq;
	}
}
