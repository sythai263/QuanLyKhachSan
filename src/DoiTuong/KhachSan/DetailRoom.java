package DoiTuong.KhachSan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DoiTuong.KhachHang.DatPhong;
import DoiTuong.KhachHang.GiaHanPhong;
import DoiTuong.KhachHang.ThuePhong;
import DoiTuong.KhachHang.TraPhong;
import DoiTuong.NhanVien.ThongTinNV;
import HienThi.DangNhap;
import HienThi.theme.ThemeDefault;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DetailRoom extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetailRoom dialog = new DetailRoom(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailRoom(Phong phong) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Thông tin phòng "+ phong.getMaPhong());
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setBounds(100, 100, 500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setBackground(ThemeDefault.getBackground_title_color());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(getBackground());
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(57, 43, 370, 270);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel title = new JLabel("CHI TIẾT");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(ThemeDefault.getFont_text_title());
		panel.add(title);
		
		JLabel maPhong = new JLabel("Mã phòng: "+ phong.getMaPhong() );
		maPhong.setHorizontalAlignment(SwingConstants.CENTER);
		maPhong.setFont(ThemeDefault.getFont_text_panel());
		panel.add(maPhong);
		
		JLabel hangPhong = new JLabel("Hạng phòng: "+ phong.getHangPhong());
		hangPhong.setHorizontalAlignment(SwingConstants.CENTER);
		hangPhong.setFont(ThemeDefault.getFont_text_panel());
		panel.add(hangPhong);
		
		JLabel trangThai = new JLabel("Trạng thái: "+ phong.getTenTrangThai());
		trangThai.setHorizontalAlignment(SwingConstants.CENTER);
		trangThai.setFont(ThemeDefault.getFont_text_panel());
		panel.add(trangThai);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnDoiTT = new JButton("Th.đổi trạng thái");
		panel_1.add(btnDoiTT);
		btnDoiTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThayDoiTTPhong td = new ThayDoiTTPhong(phong.getMaPhong());
				td.setLocationRelativeTo(null);
				td.setVisible(true);
				dispose();
				
			}
		});
		btnDoiTT.setFont(ThemeDefault.getFont_text_label());
		
		JButton btnthue = new JButton("Thuê phòng");
		panel_1.add(btnthue);
		btnthue.setFont(ThemeDefault.getFont_text_field());
		
		JButton btndatTruoc = new JButton("Đặt trước");
		panel_1.add(btndatTruoc);
		btndatTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonDatTruoc(phong.getMaPhong());
			}
		});
		btndatTruoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
					transferFocus();
				if (e.getKeyCode() == KeyEvent.VK_LEFT) btnthue.requestFocus();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				}
			}
		});
		btndatTruoc.setFont(ThemeDefault.getFont_text_field());
		btnthue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chonThuePhong(phong.getMaPhong());
			}
		});
		
		JPanel buton = new JPanel();
		panel.add(buton);
		
		JButton btnTraPhong = new JButton("Trả phòng");
		btnTraPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TraPhong trPh = new TraPhong(phong.getMaPhong());
				trPh.setVisible(true);
				trPh.setLocationRelativeTo(null);
			}
		});
		btnTraPhong.setFont(ThemeDefault.getFont_text_field());
		if (phong.getMaTrangThai().equals("CK")) {
			btnTraPhong.setVisible(true);
		}else btnTraPhong.setVisible(false);
		
		JButton btnGiaHan = new JButton("Gia hạn");
		btnGiaHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiaHanPhong ghp = new GiaHanPhong(phong.getMaPhong()); 
				ghp.setLocationRelativeTo(null);
				ghp.setVisible(true);
				dispose();
				
			}
		});
		buton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnGiaHan.setFont(ThemeDefault.getFont_text_field());
		buton.add(btnGiaHan);
		if (phong.getMaTrangThai().equals("CK")) {
			btnGiaHan.setVisible(true);
		}else btnGiaHan.setVisible(false);
		buton.add(btnTraPhong);
		if (phong.getMaTrangThai().equals("SS") || phong.getMaTrangThai().equals("DT")) {
			btnthue.setVisible(true);
		}else btnthue.setVisible(false);
		if ((phong.getMaTrangThai().equals("SS")) || (phong.getMaTrangThai().equals("DP"))) {
			btndatTruoc.setVisible(true);
		}else btndatTruoc.setVisible(false);
		if (phong.getMaTrangThai().equals("SS") || phong.getMaTrangThai().equals("BT") ||
				phong.getMaTrangThai().equals("DP")) {
			btnDoiTT.setVisible(true);
		}else {
			btnDoiTT.setVisible(false);
		}
		
		JButton btnCancel = new JButton("Hủy bỏ");
		btnCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()== KeyEvent.VK_LEFT) {
					if (phong.getMaTrangThai().equals("CK")) {
						btnTraPhong.requestFocus();
					}else {
						btndatTruoc.requestFocus();
					}
				}
				if (e.getKeyCode()== KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(ThemeDefault.getFont_text_field());
		buton.add(btnCancel);	
	}
	public void chonDatTruoc(String maPh) {
		DatPhong dp = new DatPhong(maPh);
		dispose();
		dp.setVisible(true);
		dp.setLocationRelativeTo(null);
		
	}
	public void chonThuePhong(String maPh) {
		ThuePhong thuePhong = new ThuePhong(maPh); 
		dispose();
		thuePhong.setVisible(true);
		thuePhong.setLocationRelativeTo(null);
	}
}
