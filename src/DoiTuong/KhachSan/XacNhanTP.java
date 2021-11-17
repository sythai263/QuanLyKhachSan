package DoiTuong.KhachSan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import DoiTuong.KhachHang.KhachHang;
import DoiTuong.KhachHang.ThueDV;
import HienThi.theme.ThemeDefault;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class XacNhanTP extends JFrame {

	private JPanel contentPane;
	private JTextField txtHo, txtTen, txtSDT, txtEmail,
						txtDiaChi, txtMST, txtSoNgayO, txtSoCCCD;
	KhachHang id = new KhachHang();
	String maPhieu= "", soNgayO = "", maPhong ="";
	java.sql.Date ngayDen, ngayDi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XacNhanTP frame = new XacNhanTP("100000001");
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
	public XacNhanTP(String maPD) {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setType(Type.POPUP);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setTitle("X\u00E1c nh\u1EADn thu\u00EA ph\u00F2ng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(ThemeDefault.getMenu_background());
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(getBackground());
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		CacThongTinCoBan(maPD);
		this.maPhieu = maPD;
		taoForm();
		
		
	}
	private void BorderTextField(JTextField field, String title) {
		field.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 204), 1, true), title, TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
	}
	private void taoForm() {
		
		JPanel Header = new JPanel();
		Header.setBackground(getBackground());
		contentPane.add(Header, BorderLayout.NORTH);
		
		
		JLabel lbtTitle = new JLabel("X\u00C1C NH\u1EACN");
		lbtTitle.setFont(ThemeDefault.getFont_text_title());
		lbtTitle.setForeground(ThemeDefault.getText_color_white());
		Header.add(lbtTitle);
		
		JPanel Content = new JPanel();
		Content.setBackground(getBackground());
		contentPane.add(Content, BorderLayout.CENTER);
		Content.setLayout(null);
		
		JPanel show = new JPanel();
		show.setBounds(50, 14, 484, 256);
		Content.add(show);
		show.setLayout(new GridLayout(6, 4, 5, 5));
		
		JPanel pnInfo = new JPanel();
		show.add(pnInfo);
		pnInfo.setLayout(new GridLayout(1, 2, 10, 5));
		
		JPanel pnMaPhong = new JPanel();
		pnInfo.add(pnMaPhong);
		pnMaPhong.setLayout(null);
		
		maPhong= layMaPhong(maPhieu);
		JLabel lblMaPhong = new JLabel("Mã Phòng: "+ maPhong);
		lblMaPhong.setBounds(10, 0, 207, 38);
		lblMaPhong.setFont(ThemeDefault.getFont_text_panel());
		pnMaPhong.add(lblMaPhong);
		
		JPanel pnMaPD = new JPanel();
		pnInfo.add(pnMaPD);
		pnMaPD.setLayout(null);
		
		JLabel lblMaPD = new JLabel("Mã Phiếu: "+ maPhieu);
		lblMaPD.setBounds(15, 0, 207, 38);
		lblMaPD.setFont(ThemeDefault.getFont_text_panel());
		pnMaPD.add(lblMaPD);
		
		
		
		JPanel pnHoTen = new JPanel();
		show.add(pnHoTen);
		pnHoTen.setLayout(new GridLayout(1, 2, 10, 5));
		
		JPanel pnHo = new JPanel();
		pnHoTen.add(pnHo);
		pnHo.setLayout(null);
		
		txtHo = new JTextField(id.getHo());
		txtHo.setEditable(false);
		txtHo.setBounds(15, 0, 207, 38);
		BorderTextField(txtHo, "Họ:");
		txtHo.setFont(ThemeDefault.getFont_text_field());
		pnHo.add(txtHo);
		txtHo.setColumns(10);
		
		JPanel pnTen = new JPanel();
		pnHoTen.add(pnTen);
		pnTen.setLayout(null);
		
		txtTen = new JTextField(id.getTen());
		txtTen.setEditable(false);
		txtTen.setBounds(15, 0, 207, 38);
		BorderTextField(txtTen, "Tên:");
		txtTen.setFont(ThemeDefault.getFont_text_field());
		pnTen.add(txtTen);
		txtTen.setColumns(10);
		
		JPanel pnSoCMND = new JPanel();
		show.add(pnSoCMND);
		pnSoCMND.setLayout(null);
		
		txtSoCCCD = new JTextField(id.getSoCCCD());
		txtSoCCCD.setEditable(false);
		txtSoCCCD.setBounds(15, 0, 455, 38);
		txtSoCCCD.setFont(ThemeDefault.getFont_text_field());
		BorderTextField(txtSoCCCD, "Số CMND/CCCD:");
		pnSoCMND.add(txtSoCCCD);
		txtSoCCCD.setColumns(10);
		
		JPanel pnLienLac = new JPanel();
		show.add(pnLienLac);
		pnLienLac.setLayout(new GridLayout(1, 2, 10, 5));
		
		JPanel pnSDT = new JPanel();
		pnLienLac.add(pnSDT);
		
		txtSDT = new JTextField(id.getSdt());
		txtSDT.setColumns(10);
		txtSDT.setEditable(false);
		txtSDT.setBounds(15, 0, 207, 38);
		BorderTextField(txtSDT, "Số ĐT:");
		txtSDT.setFont(ThemeDefault.getFont_text_field());
		pnSDT.setLayout(null);
		pnSDT.add(txtSDT);
		txtTen.setColumns(10);
		
		JPanel pnEmail = new JPanel();
		pnLienLac.add(pnEmail);
		
		txtEmail = new JTextField(id.getEmail());
		txtEmail.setEditable(false);
		txtEmail.setBounds(15, 0, 207, 38);
		BorderTextField(txtEmail, "Email:");
		txtEmail.setFont(ThemeDefault.getFont_text_field());
		pnEmail.setLayout(null);
		pnEmail.add(txtEmail);
		txtTen.setColumns(10);
		
		JPanel pnDiaChi = new JPanel();
		show.add(pnDiaChi);
		pnDiaChi.setLayout(null);
		
		txtDiaChi = new JTextField(id.getDiachi());
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(ThemeDefault.getFont_text_field());
		txtDiaChi.setBounds(15, 0, 455, 38);
		BorderTextField(txtDiaChi, "Địa chỉ:");
		pnDiaChi.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		JPanel pnKhac = new JPanel();
		show.add(pnKhac);
		pnKhac.setLayout(new GridLayout(1, 2, 10, 5));
		
		JPanel pnMST = new JPanel();
		pnKhac.add(pnMST);
		
		txtMST = new JTextField(id.getMst());
		txtMST.setColumns(10);
		txtMST.setEditable(false);
		txtMST.setBounds(15, 0, 207, 38);
		BorderTextField(txtMST, "MST:");
		txtMST.setFont(ThemeDefault.getFont_text_field());
		pnMST.setLayout(null);
		pnMST.add(txtMST);
		txtTen.setColumns(10);
		
		JPanel pnSoNgayO = new JPanel();
		pnKhac.add(pnSoNgayO);
		
		txtSoNgayO = new JTextField(soNgayO);
		txtSoNgayO.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String parten = "^[0-9]*$";
				if (isVisible()) {
					if (!txtSoNgayO.getText().matches(parten)) {
						JOptionPane.showMessageDialog(null, "Chỉ được nhập số !");
						txtSoNgayO.requestFocus();
					}
				}
			}
		});
		txtSoNgayO.setEditable(false);
		txtSoNgayO.setBounds(15, 0, 207, 38);
		BorderTextField(txtSoNgayO, "Số ngày ở:");
		txtSoNgayO.setFont(ThemeDefault.getFont_text_field());
		pnSoNgayO.setLayout(null);
		pnSoNgayO.add(txtSoNgayO);
		txtSoNgayO.setColumns(10);
		
		JPanel Footer = new JPanel();
		Footer.setBackground(getBackground());
		FlowLayout flowLayout = (FlowLayout) Footer.getLayout();
		flowLayout.setHgap(30);
		flowLayout.setVgap(10);
		contentPane.add(Footer, BorderLayout.SOUTH);
		
		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choPhepSua();
			}
			
		});
		Footer.add(btnEdit);
		
		JButton btnOk = new JButton("OK");
		Footer.add(btnOk);
		
		JButton btnHuy = new JButton("H\u1EE7y");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Footer.add(btnHuy);
	}
	private void CacThongTinCoBan(String maPD) {
		
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM PhieuDat WHERE MaPD ='"+maPD+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					
					String soCCCD = rs.getString("SoCMND");
					getTTKH(soCCCD);
				
			}
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	private String layMaPhong(String maPD) {
		String maphong ="";
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPD inner join Phong on CTPD.HangPhong = Phong.HangPhong "
				+ "WHERE MaPD ='"+maPD+"' and MaTrangThai ='DT'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					maphong= rs.getString("MaPhong");
					soNgayO = rs.getString("SoNgayO");
				}
			
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return maphong;
	}
	public boolean getTTKH(String soCMND) {
		boolean kt = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM KhachHang WHERE SoCMND = '"+soCMND+"'";
		
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.id.setSoCCCD(rs.getString("SoCMND"));
				this.id.setHo(rs.getNString("Ho"));
				this.id.setTen(rs.getNString("Ten"));
				this.id.setSdt(rs.getString("SDT"));
				this.id.setDiachi(rs.getNString("DC"));
				this.id.setEmail(rs.getString("Email"));
				this.id.setMst(rs.getNString("MSThue"));
				kt = true;
				
			}
			ps.close();
			ketNoi.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return kt;
	}
	public void setTTKH(String soCCCD) {
		if (getTTKH(soCCCD)) {
			this.txtHo.setText(this.id.getHo());
			this.txtTen.setText(this.id.getTen());
			this.txtSoCCCD.setText(this.id.getSoCCCD());
			this.txtSDT.setText(this.id.getSdt());
			this.txtDiaChi.setText(this.id.getDiachi());
			this.txtEmail.setText(this.id.getEmail());
			this.txtMST.setText(this.id.getMst());
		}
	}
	public void choPhepSua() {
		txtHo.setEditable(true);
		txtTen.setEditable(true);
		txtSoCCCD.setEditable(true);
		txtSDT.setEditable(true);
		txtMST.setEditable(true);
		txtDiaChi.setEditable(true);
		txtEmail.setEditable(true);
		txtSoNgayO.setEditable(true);
	}
	public void xacNhan() {
		
		int maPhieuInt = Integer.valueOf(maPhieu);
		int soNgay = Integer.valueOf(txtSoNgayO.getText());
		xuLiNgay(soNgay);
		PhieuThue pt = new PhieuThue(txtSoCCCD.getText(), maPhong , maPhieuInt, soNgay , ngayDen, ngayDi);
		pt.taoPhieuThue();
		CapNhatDB.thayDoiTTPhong(maPhieu, "CK");
		dispose();
		ThueDV chonDV = new ThueDV(pt.getMaPT());
		chonDV.setLocationRelativeTo(null);
	}
	public void xuLiNgay(int soNgayO) {
		Calendar cal = Calendar.getInstance();
		//lấy ngày hiện tại
		this.ngayDen = new java.sql.Date(cal.getTime().getTime());
		
		//Cộng thêm số ngày ở từ form vào ta được ngày trả phòng
		cal.add(Calendar.DAY_OF_MONTH, soNgayO);
		java.util.Date ngayTraPhong =cal.getTime();
		this.ngayDi = new java.sql.Date(ngayTraPhong.getTime());
		
		
	}
	
}
