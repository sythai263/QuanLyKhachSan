package DoiTuong.KhachHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;
import DoiTuong.KhachSan.PanelPhong;
import DoiTuong.KhachSan.PhieuDat;
import DoiTuong.KhachSan.PhieuThue;
import DoiTuong.KhachSan.Phong;
import HienThi.DSPhong;
import HienThi.theme.ThemeDefault;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ThuePhong extends JDialog {
	
	private JTextField soCCCD;
	private JTextField ho;
	private JTextField ten;
	private JTextField diachi;
	private JTextField email;
	private JTextField sdt;
	private JTextField mst;
	private JLabel maphong;
	private int maPD ;
	private PhieuDat pd;
	private PhieuThue pt;
	private JDateChooser ngayBDThue = new JDateChooser();
	private java.sql.Date ngayDen, ngayDi, ngayDat;
	private JSpinner soNgay = new JSpinner();
	private JButton btnContinue = new JButton("Tiếp tục");
	private JPanel panel_1;
	private JLabel lblsngay;
	private int soNgayO;
	private KhachHang id = new KhachHang();
	private JPanel buttonPane = new JPanel();
	private String maPhieu = "", maPhong= "";;
	
	
	public JSpinner getSoNgay() {
		return soNgay;
	}

	public void setSoNgay(JSpinner soNgay) {
		this.soNgay = soNgay;
	}

	public int getMaPD() {
		return maPD;
	}

	public void setMaPD(int maPD) {
		this.maPD = maPD;
	}

	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public ThuePhong(String maPh) {
		this.maPhieu = maPh;
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThuePhong.class.getResource("/HienThi/img/logo_hotel.png")));
		setTitle("Thuê Phòng");
		setBounds(0, 0, 406, 567);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(ThemeDefault.getBackground_title_color());
		contentPanel.setSize(340, 500);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 400, 85);
		header.setBackground(contentPanel.getBackground());
		
		contentPanel.add(header);
		header.setLayout(new BorderLayout(0, 0));
		
		JLabel Title = new JLabel("Thuê Phòng");
		Title.setIcon(new ImageIcon("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\chiakhoa_white.png"));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(ThemeDefault.getFont_text_title());
		Title.setForeground(ThemeDefault.getText_color_white());
		header.add(Title);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		taoForm(maPh);
	
	}
	public void taoForm(String maPh) {
		JPanel panel = new JPanel();
		setBackground(ThemeDefault.getBackground_color());
		panel.setBounds(31, 92, 328, 395);
		contentPanel.add(panel);
		panel.setBackground(new Color(0,0,0,0));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN THUÊ PHÒNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(ThemeDefault.getFont_text_panel());
		lblNewLabel.setForeground(ThemeDefault.getText_color_white());
		panel.add(lblNewLabel);
		
		soCCCD = new JTextField();
		soCCCD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				setTTKH();
			}
		});
		soCCCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN : case KeyEvent.VK_RIGHT:
					ho.requestFocus();
				case KeyEvent.VK_ENTER:
					setTTKH();
				}
			}
		});
		
		maphong = new JLabel("Phòng: "+ maPh);
		maphong.setHorizontalAlignment(SwingConstants.CENTER);
		maphong.setForeground(ThemeDefault.getText_color_white());
		maphong.setBackground(ThemeDefault.getBackground_color());
		maphong.setFont(ThemeDefault.getFont_text_heading());
		panel.add(maphong);
		
		
		soCCCD.setFont(ThemeDefault.getFont_text_field());
		soCCCD.setBackground(ThemeDefault.getBackground_color());
		soCCCD.setForeground(ThemeDefault.getTextLabel_color());
		soCCCD.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Số CCCD/CMND:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(soCCCD);
		soCCCD.setColumns(24);
		
		ho = new JTextField();
		ho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(ho, e);
			}
		});
		ho.setFont(ThemeDefault.getFont_text_field());
		ho.setBackground(ThemeDefault.getBackground_color());
		ho.setForeground(ThemeDefault.getTextLabel_color());
		ho.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Họ:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(ho);
		ho.setColumns(13);
		
		ten = new JTextField();
		ten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(ten, e);
			}
		});
		ten.setFont(ThemeDefault.getFont_text_field());
		ten.setBackground(ThemeDefault.getBackground_color());
		ten.setForeground(ThemeDefault.getTextLabel_color());
		ten.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Tên:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(ten);
		ten.setColumns(10);
		
		diachi = new JTextField();
		diachi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(diachi, e);
			}
		});
		diachi.setFont(ThemeDefault.getFont_text_field());
		diachi.setBackground(ThemeDefault.getBackground_color());
		diachi.setForeground(ThemeDefault.getTextLabel_color());
		diachi.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Địa chỉ:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(diachi);
		diachi.setColumns(24);
		
		email = new JTextField();
		email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(email, e);
			}
		});
		email.setFont(ThemeDefault.getFont_text_field());
		email.setBackground(ThemeDefault.getBackground_color());
		email.setForeground(ThemeDefault.getTextLabel_color());
		email.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Email:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(email);
		email.setColumns(13);
		
		sdt = new JTextField();
		sdt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(sdt, e);
			}
		});
		sdt.setFont(ThemeDefault.getFont_text_field());
		sdt.setBackground(ThemeDefault.getBackground_color());
		sdt.setForeground(ThemeDefault.getTextLabel_color());
		sdt.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Số điện thoại:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(sdt);
		sdt.setColumns(10);
		
		mst = new JTextField();
		mst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				modelKeyEvent(mst, e);
			}
		});
		mst.setFont(ThemeDefault.getFont_text_field());
		mst.setBackground(ThemeDefault.getBackground_color());
		mst.setForeground(ThemeDefault.getTextLabel_color());
		mst.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Mã số thuế:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		panel.add(mst);
		mst.setColumns(24);
		
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblsngay = new JLabel("Số ngày thuê");
		lblsngay.setBackground(ThemeDefault.getBackground_color());
		lblsngay.setForeground(ThemeDefault.getTextLabel_color());
		lblsngay.setFont(ThemeDefault.getFont_text_label());
		panel_1.add(lblsngay);
		panel_1.add(soNgay);
		panel_1.setBackground(new Color(0,0,0,0));
		soNgay.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		soNgay.setFont(ThemeDefault.getFont_text_field());
		soNgay.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		
	}
	{
		
		buttonPane.setLocation(0, 0);
		buttonPane.setSize(365, 50);
		buttonPane.setBackground(ThemeDefault.getBackground_title_color());
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		{
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (kiemtra()) {
						capNhatTTKH();
						taoPhieuThue();
						JOptionPane.showMessageDialog(null, "Đã đặt phòng thành công !", "Thông báo",JOptionPane.NO_OPTION);
						dispose();
						
					}
				}
			});
			
			
			buttonPane.add(btnContinue);
		}
		{
			JButton cancelButton = new JButton("Hủy");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
	}
}
	public boolean kiemtra() {
		boolean kt = false;
		switch (KiemTra.CCCD(soCCCD.getText())) {
		case 0: 
			JOptionPane.showMessageDialog(null, "Số CCCD/CMND phải có 9 hoặc 12 số. Kiểm tra lại !");
			break;
		case -1:
			JOptionPane.showMessageDialog(null, "Số CCCD/CMND bắt buộc phải là số. Kiểm tra lại !");
			break;
		case 1:
			if ((ho.getText().length() == 0) || (ten.getText()).length()==0) {
				JOptionPane.showMessageDialog(null, "Họ, tên không được để trống. Kiểm tra lại !");
				break;
			}else if (diachi.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống. Kiểm tra lại !");
				break;
				
			}else {
				switch (KiemTra.Email(email.getText())) {
				case 0:
					JOptionPane.showMessageDialog(null, "Email không hợp lệ. Kiểm tra lại !");
					break;
				case 1:
					switch (KiemTra.SDT(sdt.getText())) {
					case -1:
						JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống. Kiểm tra lại !");
						break;
					case -2:
						JOptionPane.showMessageDialog(null, "Số điện thoại chỉ chứa số. Kiểm tra lại !");
						break;
					case -3:
						JOptionPane.showMessageDialog(null, "Số điện thoại chỉ có 10 số. Kiểm tra lại !");
						break;
					case 0:
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Kiểm tra lại !");
						break;
					case 1:
						kt = true;
					}
				}
			}
		}
		return kt;
	}
	
	//Set keyEvent cho các  text field
	public void modelKeyEvent(JTextField jtf,KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			jtf.transferFocusDownCycle();
			break;
		case KeyEvent.VK_UP:
			jtf.transferFocusUpCycle();
			break;
		case KeyEvent.VK_LEFT:
			jtf.transferFocusBackward();
			break;
		case KeyEvent.VK_RIGHT: case KeyEvent.VK_ENTER:
			jtf.transferFocus();
			break;
		}
	}
	
	//Tạo phiếu đặt
	public void taoPhieuDat() {
		soNgayO = (Integer) soNgay.getValue();
		xuLiNgay();
		if (getTTKH(soCCCD.getText())) {
			pd = new PhieuDat(soCCCD.getText(), ngayDen, soNgayO);
			pd.taoPhieuDat(maPhieu);
			CapNhatDB.thayDoiTTPhong(maPhieu, "DT");
		}
		else {
			KhachHang kh = new KhachHang(soCCCD.getText(), ho.getText(), ten.getText(), 
										diachi.getText(), sdt.getText(), email.getText(), mst.getText());
			kh.luuTTKH();
			pd = new PhieuDat(soCCCD.getText(), ngayDen, soNgayO);
			pd.taoPhieuDat(maPhieu);
			CapNhatDB.thayDoiTTPhong(maPhieu, "DT");
		}
		
		
		
	}
	public void taoPhieuThue() {
		taoPhieuDat();
		PhieuThue pt = new PhieuThue(soCCCD.getText(), maPhieu, pd.getMaPhieu(), soNgayO, ngayDen, ngayDi);
		pt.taoPhieuThue();
		CapNhatDB.thayDoiTTPhong(maPhieu, "CK");
		dispose();
		ThueDV chonDV = new ThueDV(pt.getMaPT());
		chonDV.setLocationRelativeTo(null);
		
	}
	//Set ngày đến, ngày đi của khách
	public void xuLiNgay() {
		java.util.Date now =ngayBDThue.getDate();
		Calendar cal = Calendar.getInstance();
		//lấy ngày hiện tại
		this.ngayDat = new java.sql.Date(cal.getTime().getTime());
		
		//lấy ngày bắt đầu đặt từ form
		cal.setTime(now);
		java.util.Date ngayDatPhong =cal.getTime();
		this.ngayDen = new java.sql.Date(ngayDatPhong.getTime());
		
		//Cộng thêm số ngày ở từ form vào ta được ngày trả phòng
		cal.add(Calendar.DAY_OF_MONTH, soNgayO);
		java.util.Date ngayTraPhong =cal.getTime();
		this.ngayDi = new java.sql.Date(ngayTraPhong.getTime());
		
		
	}
	
	
	//lấy DS khách hàng
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
			System.out.println("có nè");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
		}
		return kt;
	}
	
	
	//Kiểm tra thông tin khách hàng xem có rthay đổi gì không !
	public boolean checkTT() {
		boolean kt = true;
		if (soCCCD.getText().equals(this.id.getSoCCCD())) {
			if ((!ho.getText().equals(this.id.getHo())) ||
				(!ten.getText().equals(this.id.getTen())) ||
				(!diachi.getText().equals(this.id.getDiachi())) ||
				(!sdt.getText().equals(this.id.getSdt())) ||
				(!email.getText().equals(this.id.getEmail())) ||
				(!mst.getText().equals(this.id.getMst()))) {
				kt = false;
			}
		}
		return kt;
	}
	
	// cập nhật lại thông tin KH nếu có thay đổi !
	public void capNhatTTKH() {
		if (!checkTT()) {
			String[] mes = {"Bạn đã sửa đổi thông tin của khách hàng trước đây!", "Bạn có chắc chắn muốn cập nhật không ?"};
			int select = new JOptionPane().showConfirmDialog(null, mes, "Cảnh báo !", JOptionPane.OK_CANCEL_OPTION);
			if (select == JOptionPane.OK_OPTION) {
				this.id.setHo(ho.getText());
				this.id.setTen(ten.getText());
				this.id.setDiachi(diachi.getText());
				this.id.setEmail(email.getText());
				this.id.setSdt(sdt.getText());
				this.id.setMst(mst.getText());
				this.id.capNhatTTKH();
			}
			
		}
	}

	//Đề xuất thông tin khách hàng đã có dựa theo số CMND/CCCD
	public void setTTKH() {
		getTTKH(soCCCD.getText());
		if (soCCCD.getText().equals(this.id.getSoCCCD())) {
			ho.setText(this.id.getHo());
			ten.setText(this.id.getTen());
			diachi.setText(this.id.getDiachi());
			email.setText(this.id.getEmail());
			sdt.setText(this.id.getSdt());
			mst.setText(this.id.getMst());
		}
	}
}
