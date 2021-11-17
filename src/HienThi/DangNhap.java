package HienThi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DoiTuong.NhanVien.DSNhanVien;
import DoiTuong.NhanVien.TaiKhoan;
import DoiTuong.NhanVien.ThongTinNV;
import HienThi.theme.ThemeDefault;
import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import HienThi.img.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class DangNhap extends JFrame {
	JPasswordField matKhau = new JPasswordField();
	private JPanel contentPane;
	private  static ThongTinNV login;
	private static JTextField maNV = new JTextField();
	
	public static  ThongTinNV getLogin() {
		return login;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhap() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DangNhap.class.getResource("/HienThi/img/logo_hotel.png")));
		setTitle("ĐĂNG NHẬP HỆ THỐNG");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 542);
		
		contentPane = new JPanel();
		contentPane.setBackground(HienThi.theme.ThemeDefault.getBackground_title_color());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 335, 153);
		panel.setBackground(HienThi.theme.ThemeDefault.getBackground_title_color());
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBorder(null);
		
		JLabel lbTitle = new JLabel("");
		lbTitle.setIcon(new ImageIcon(DangNhap.class.getResource("/HienThi/img/hotel_100px.png")));
		lbTitle.setBounds(0, 0, 338, 153);
		lbTitle.setForeground(HienThi.theme.ThemeDefault.getText_color_white());
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("UTM Neutra", Font.PLAIN, 30));
		panel.add(lbTitle);
		
		
		maNV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					maNV.transferFocus();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					maNV.transferFocus();
				}
			}
		});
		maNV.setHorizontalAlignment(SwingConstants.LEFT);
		maNV.setBounds(67, 205, 200, 40);
		contentPane.add(maNV);
		maNV.setColumns(10);
		maNV.setBackground(ThemeDefault.getBackground_color());
		maNV.setForeground(new java.awt.Color(255, 255, 255));
		maNV.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Mã nhân viên:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		maNV.setFont(HienThi.theme.ThemeDefault.getFont_text_field());
		
		JButton btnLogin = new JButton("");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					EnterLogin();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					matKhau.requestFocus();
				}
			}
		});
		btnLogin.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent login) {
				EnterLogin();
			}
		});
		btnLogin.setBackground(new Color(255, 255, 255, 0));
		btnLogin.setBounds(147, 326, 40, 40);
		contentPane.add(btnLogin);
		btnLogin.setIcon(Images.continue_white);
		btnLogin.setBorder(null);
		
		matKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					EnterLogin();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					maNV.requestFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					btnLogin.requestFocus();
				}
			}
		});
		matKhau.setEchoChar('*');
		matKhau.setBackground(ThemeDefault.getBackground_color());
		matKhau.setBounds(67, 272, 200, 40);
		contentPane.add(matKhau);
		matKhau.setForeground(ThemeDefault.getTextLabel_color());
		matKhau.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Mật khẩu:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		matKhau.setFont(ThemeDefault.getFont_text_field());
		
		
		
		
		
	
	}
	public void EnterLogin() {
		if (ktraNhap()) {
			String loi="";
			switch (ktraLogin(maNV.getText(), matKhau.getText())) {
			case -1:
				loi = "Không tìm thấy nhân viên "+ maNV.getText();
				break;
			case 0: 
				loi = "Sai mật khẩu !";
				break;
			case 1:
				System.out.println("Đã đăng nhập thành công !");
				System.out.println("Đang load giao diện chính ....");
				dispose();
				setVisible(false);
				TrangChinh Mainframe = new TrangChinh();
				Mainframe.setExtendedState(MAXIMIZED_BOTH);
				Mainframe.setVisible(true);
				break;
			}
			if (loi.length() != 0) {
				JOptionPane.showMessageDialog(null, loi, "Thông báo", 1);
				System.out.println(loi);
			}
		}
	}
	//kiểm tra tài khoản và mật khẩu có hợp lệ không
	// nếu hợp lệ thì lấy thông tin của tài khoản đó
	public int ktraLogin(String maNV, String pass) {
		int check =0;
		Connection ketNoi = DieuKhien.KetNoiDB.ketNoi();
		String sql = "SELECT * FROM NhanVien WHERE MaNV = '"+maNV+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				TaiKhoan nv = new TaiKhoan(rs.getString("MaNV"),
											rs.getString("MatKhau"));
				String ho = rs.getNString("Ho");
				String ten = rs.getNString("Ten");
				String gt = rs.getNString("GioiTinh");
				Date ngaySinh = rs.getDate("NgaySinh");
				String sdt = rs.getString("SDT");
				String email = rs.getString("Email");
				String maBP = rs.getString("MaBP");
				String matKhau = rs.getString("MatKhau");
				
				login = new ThongTinNV(maNV, ho, ten, gt, ngaySinh, sdt, email, maBP, matKhau);
				
				if (maNV.equalsIgnoreCase(login.getMaNV()) && pass.equals(login.getMatKhau())){
					check = 1;
				}else check = 0;
			}else check = -1;
			ps.close();
			rs.close();
			ketNoi.close();
			System.out.println("Đóng kết nối !");
		
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return check;
	}
	
	
	//Kiểm tra dữ liệu nhập của người dùng
	//có lỗi thì thông báo lỗi 
	public boolean ktraNhap() {
		boolean check = false;
		String loi= "";
		switch (KiemTra.MaNV(maNV.getText())) {
		case -1:
			loi = "Không được để trống mã nhân viên !";
			break;
		case 0: 
			loi = "Mã nhân viên không đúng định dạng !";
			break;
		case 1: 
			switch (KiemTra.MatKhau(matKhau.getText())) {
			case -1:
				loi = "Không được để trống mật khẩu !";
				break;
			case 1:
				check = true;
				break;

			case 0: default:
				loi = "Mật khẩu sai !";
				break;
			}
			break;
		}
		if (loi.length() !=0 ) {
			JOptionPane.showMessageDialog(null, loi, "Thông báo", 1);
			System.out.println(loi);
		}
		
		return check;
		
	}

}
