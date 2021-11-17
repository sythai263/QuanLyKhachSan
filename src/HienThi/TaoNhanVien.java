package HienThi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;

import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;
import DoiTuong.NhanVien.BoPhan;
import DoiTuong.NhanVien.DSNhanVien;
import DoiTuong.NhanVien.DanhSachBP;
import DoiTuong.NhanVien.TaiKhoan;
import DoiTuong.NhanVien.ThongTinNV;
import HienThi.img.Images;
import HienThi.theme.ThemeDefault;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDayChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TaoNhanVien extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup GioiTinh = new ButtonGroup();
	private JTextField 	Ho = new JTextField(),
						Ten = new JTextField(),
						Email = new JTextField(),
						SDT = new JTextField(),
						MaNV = new JTextField(),
						TenBP = new JTextField();
	private JPasswordField MatKhau = new JPasswordField();
	private JComboBox MaBP = new JComboBox();
	private JDateChooser NgaySinh = new JDateChooser();
	private JLabel 	ttnv = new JLabel("THÔNG TIN NHÂN VIÊN"),
					title = new JLabel("TẠO NHÂN VIÊN");
	private JPanel 	content = new JPanel(),
					header = new JPanel();
	private JRadioButton GT_Nam = new JRadioButton("Nam"),
						GT_Nu = new JRadioButton("Nữ");
	private JButton okButton = new JButton("");
	private String loi ="" ;
	private ThongTinNV nv = new ThongTinNV();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoNhanVien frame = new TaoNhanVien();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void showDSBP() {
		DanhSachBP.layDSBP();
		for (BoPhan x: DanhSachBP.getDSachBP()) {
			MaBP.addItem(x.getMaBP());
		}
	}
	/**
	 * Create the frame.
	 */
	public TaoNhanVien() {
		
		setResizable(false);
		showDSBP();
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TaoNhanVien.class.getResource("/HienThi/img/logo_hotel.png")));
		setTitle("T\u1EA0O NH\u00C2N VI\u00CAN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(HienThi.theme.ThemeDefault.getBackground_title_color());
		contentPane.setLayout(null);
		
		taoForm();
		
	}
	public void taoForm() {
		header.setBounds(0, 0, 394, 88);
		header.setBackground(ThemeDefault.getBackground_color());
		contentPane.add(header);
		header.setLayout(new BorderLayout(0, 0));
		
		
		title.setFont(ThemeDefault.getFont_text_title());
		title.setForeground(ThemeDefault.getTextLabel_color());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(title);
		
		
		FlowLayout flowLayout = (FlowLayout) content.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		content.setBackground(ThemeDefault.getBackground_color());
		content.setBounds(32, 113, 330, 386);
		contentPane.add(content);
		
		
		ttnv.setHorizontalAlignment(SwingConstants.CENTER);
		ttnv.setForeground(ThemeDefault.getTextLabel_color());
		ttnv.setFont(ThemeDefault.getFont_text_panel());
		content.add(ttnv);
		
		Ho.setFont(ThemeDefault.getFont_text_field());
		Ho.setBackground(ThemeDefault.getBackground_color());
		Ho.setForeground(ThemeDefault.getTextLabel_color());
		Ho.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Họ:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(Ho);
		Ho.setColumns(15);
		
		Ten.setFont(ThemeDefault.getFont_text_field());
		Ten.setBackground(ThemeDefault.getBackground_color());
		Ten.setForeground(ThemeDefault.getTextLabel_color());
		Ten.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Tên:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(Ten);
		Ten.setColumns(10);
		GT_Nam.setSelected(true);
		
		GT_Nam.setFont(ThemeDefault.getFont_text_label());
		GT_Nam.setBackground(ThemeDefault.getBackground_color());
		GT_Nam.setForeground(ThemeDefault.getTextLabel_color());
		GioiTinh.add(GT_Nam);
		content.add(GT_Nam);
		
		GT_Nu.setFont(ThemeDefault.getFont_text_label());
		GT_Nu.setBackground(ThemeDefault.getBackground_color());
		GT_Nu.setForeground(ThemeDefault.getTextLabel_color());
		GioiTinh.add(GT_Nu);
		content.add(GT_Nu);
		
		
		BorderLayout borderLayout = (BorderLayout) NgaySinh.getLayout();
		NgaySinh.setLocale(new Locale("vi", "VN"));
		NgaySinh.getSpinner().setFont(ThemeDefault.getFont_text_field());
		NgaySinh.getSpinner().setBackground(ThemeDefault.getBackground_color());
		NgaySinh.getSpinner().setForeground(ThemeDefault.getTextLabel_color());
		NgaySinh.setFont(ThemeDefault.getFont_text_field());
		NgaySinh.setBackground(ThemeDefault.getBackground_color());
		NgaySinh.setForeground(ThemeDefault.getTextLabel_color());
		NgaySinh.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Ngày Sinh :", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		
		content.add(NgaySinh);
		
		
		Email.setFont(ThemeDefault.getFont_text_field());
		Email.setBackground(ThemeDefault.getBackground_color());
		Email.setForeground(ThemeDefault.getTextLabel_color());
		Email.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Email:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(Email);
		Email.setColumns(14);
		
		
		SDT.setFont(ThemeDefault.getFont_text_field());
		SDT.setBackground(ThemeDefault.getBackground_color());
		SDT.setForeground(ThemeDefault.getTextLabel_color());
		SDT.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Số điện thoại:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(SDT);
		SDT.setColumns(12);
		MaBP.setBackground(Color.CYAN);
		
		
		MaBP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TenBP.setText(DanhSachBP.getDSachBP().get(MaBP.getSelectedIndex()).getTenBP());
			}
		});
		
		MaBP.setSize(100, 30);
		MaBP.setSelectedIndex(0);
		content.add(MaBP);
		
		TenBP.setText(DanhSachBP.getDSachBP().get(0).getTenBP());
		TenBP.setColumns(12);
		TenBP.setEditable(false);
		TenBP.setFont(ThemeDefault.getFont_text_label());
		TenBP.setBackground(ThemeDefault.getBackground_color());
		TenBP.setForeground(ThemeDefault.getTextLabel_color());
		TenBP.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Tên bộ phận:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(TenBP);
		MaNV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				KTMaNV();
			}
		});
		
		
		
		MaNV.setFont(ThemeDefault.getFont_text_field());
		MaNV.setBackground(ThemeDefault.getBackground_color());
		MaNV.setForeground(ThemeDefault.getTextLabel_color());
		MaNV.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Mã Nhân viên:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(MaNV);
		MaNV.setColumns(12);
		
		
		MatKhau.setFont(ThemeDefault.getFont_text_field());
		MatKhau.setBackground(ThemeDefault.getBackground_color());
		MatKhau.setForeground(ThemeDefault.getTextLabel_color());
		MatKhau.setBorder(new TitledBorder(new MatteBorder(0, 2, 2, 0, ThemeDefault.getLineBoder_color()),
				"Mật khẩu:", TitledBorder.LEFT, TitledBorder.TOP, 
				ThemeDefault.getFont_text_label(), ThemeDefault.getTextLabel_color()));
		content.add(MatKhau);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkInput()) {
					JOptionPane.showMessageDialog(null, loi, "Thông báo", 1);
				}else {
					creatNV();
					if (nv.luuThongTinNV()) {
						JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !", "Thông báo", 1);
						dispose();
					}
					else JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm nhân viên !", "Thông báo", 1);
				}
			}
		});
		MatKhau.setColumns(12);
		
		
		okButton.setForeground(Color.WHITE);
		okButton.setIcon(Images.continue_white);
		okButton.setBackground(ThemeDefault.getBackground_color());
		okButton.setBorder(null);
		
		content.add(okButton);
		
	}
	public void creatNV() {
		nv.setHo(Ho.getText());
		nv.setTen(Ten.getText());
		String gt, mabp;
		if (GT_Nam.isSelected()) {
			gt = "Nam";
		}else gt = "Nữ";
		
	
		Calendar cal = Calendar.getInstance();
		java.sql.Date ns = new java.sql.Date(cal.getTime().getTime());
		
		mabp = (String) MaBP.getSelectedItem();
		nv.setGioiTinh(gt);
		nv.setEmail(Email.getText());
		nv.setMaBP(mabp);
		nv.setNgaySinh(ns);
		nv.setMaNV(MaNV.getText());
		nv.setMatKhau(MatKhau.getText());
		nv.setSDT(SDT.getText());
		
	}
	public boolean KtraDB() {
		boolean check = false;
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM NhanVien WHERE MaNV ='"+MaNV.getText()+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				check = true;
			}
			rs.close();
			ps.close();
			ketNoi.close();
			System.out.println("Kiểm tra sự tồn tại của mã nhân viên.");
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	public void KTMaNV() {
		loi = "";
		switch (KiemTra.MaNV(MaNV.getText())) {
		case -1: 
			loi = "Không được để trống Mã nhân viên.";
			break;
		case 0: 
			loi = "Mã nhân viên không đúng định dạng. Ví dụ: QL001,...";
			break;
		case 1:
			if (KtraDB()) {
				loi = "Mã nhân viên đã tồn tại vui lòng chọn mã nhân viên khác.";
				break;
			}
			break;
		}
		if (!loi.equals("")) {
			JOptionPane.showMessageDialog(null, loi, "Thông báo", 1);
			MaNV.requestFocus();
		}
	}
	public boolean checkInput() {
		loi = "";
		boolean check = false;
		if (Ho.getText().length() == 0) {
			loi = "Không để trống phần Họ.";
		}else {
			if (Ten.getText().length() == 0) {
				loi = "Không để trống phần Tên.";
			}else {
				if (!kiemTraTuoi()) {
					loi = "Nhân viên chưa đủ 18 tuổi !";
				}else {
					switch (KiemTra.Email(Email.getText())) {
					case -1:
						loi = "Không để trống Email.";
						break;
					case 0: 
						loi = "Email không hợp lệ !";
						break;
					case 1:
						switch (KiemTra.SDT(SDT.getText())) {
						case -1:
							loi = "Không để trống Số điện thoại.";
							break;
						case -2:
							loi = "Số điện thoại chỉ chứa các ký tự là số.";
							break;
						case -3:
							loi = "Số điện thoại chỉ có 10 số.";
							break;
						case 0:
							loi = "Số điện thoại không hợp lệ";
							break;
						case 1:
							switch (KiemTra.MatKhau(MatKhau.getText())) {
							case -1:
								loi = "Mật khẩu không được để trống.";
								break;
							case 0:
								loi = "Mật khẩu phải có ít nhất 6 ký tự trong đó có ít nhất"
										+ " 1 ký tự hoa, 1 ký tự số và 1 ký tự thường.";
								break;
							case 1 :
								if (MaNV.getText().length() == 0) {
									loi = "Không để trống mã nhân viên.";
									MaNV.requestFocus();
									break;
								}else check = true;
								break;
							}
							break;
						}
						break;
					}
				}
			}
			
		}
		return check;
	}
	public boolean kiemTraTuoi() {
		boolean check = false;
		Date sn = NgaySinh.getDate();
		Calendar cal = Calendar.getInstance();
		Date now = new Date(cal.getTime().getTime());
		long tuoi = TimeUnit.MILLISECONDS.toDays(now.getTime()-sn.getTime());
		if (tuoi > 6570) {
			check = true;
		}
		return check;
	}
}
