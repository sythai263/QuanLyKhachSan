package HienThi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import DieuKhien.KiemTra;
import DoiTuong.KhachSan.ChiTietPhieuDat;
import DoiTuong.KhachSan.FormTraPhong;
import DoiTuong.KhachSan.PanelPhong;
import HienThi.theme.ThemeDefault;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DropMode;

public class TrangChinh extends JFrame {

	private JPanel contentPane;
	private String tittle;
	private DSPhong ds;
	private static ArrayList<ChiTietPhieuDat> DSPD = new ArrayList<ChiTietPhieuDat>();
	private static ArrayList<String> DSMaPhong = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChinh frame = new TrangChinh();
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
	public TrangChinh() {
		String titleApp = "Quản lý Khách sạn - bởi nhân viên "+ DangNhap.getLogin().getTen();
		setTitle(titleApp);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TrangChinh.class.getResource("/HienThi/img/logo_hotel.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280,720 );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel menu = new JPanel();
		contentPane.add(menu, BorderLayout.WEST);
		menu.setLayout(new GridLayout(10, 1, 0, 5));
		menu.setBackground(ThemeDefault.getMenu_background());
		
		JPanel card1 = new JPanel();
		card1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				card1.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card1.setBackground(ThemeDefault.getCard_color());
			}
		});
		
		JPanel card_title = new JPanel();
		card_title.setBackground(ThemeDefault.getCardTitle_color());		
		menu.add(card_title);
		card_title.setLayout(null);
		
		JLabel hello = new JLabel("Xin chào, "+DangNhap.getLogin().getTen() + "!");
		hello.setHorizontalAlignment(SwingConstants.CENTER);
		hello.setFont(ThemeDefault.getFont_text_label());
		hello.setForeground(ThemeDefault.getText_color_white());
		hello.setBounds(0, 0, 217, 58);
		card_title.add(hello);
		card1.setBackground(ThemeDefault.getCard_color());
		menu.add(card1);
		card1.setLayout(null);
		
		JLabel DatPhong = new JLabel("QUẢN LÝ ĐẶT PHÒNG");
		DatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ds.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				card1.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card1.setBackground(ThemeDefault.getCard_color());
			}
		});
		DatPhong.setForeground(ThemeDefault.getText_color_white());
		DatPhong.setHorizontalAlignment(SwingConstants.CENTER);
		DatPhong.setFont(ThemeDefault.getFont_text_label());
		DatPhong.setBounds(0, 0, 197, 63);
		card1.add(DatPhong);
		
		JPanel card2 = new JPanel();
		card2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				card2.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card2.setBackground(ThemeDefault.getCard_color());
			}
		});
		card2.setBackground(ThemeDefault.getCard_color());
		menu.add(card2);
		card2.setLayout(new BorderLayout(0, 0));
		
		JLabel thongKe = new JLabel("THỐNG KÊ");
		thongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ds.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				card2.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card2.setBackground(ThemeDefault.getCard_color());
			}
		});
		thongKe.setHorizontalAlignment(SwingConstants.CENTER);
		thongKe.setForeground(Color.WHITE);
		thongKe.setFont(new Font("UTM Caviar", Font.BOLD, 12));
		card2.add(thongKe);
		
		JPanel card3 = new JPanel();
		card3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				card3.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card3.setBackground(ThemeDefault.getCard_color());
			}
		});
		card3.setBackground(ThemeDefault.getCard_color());
		menu.add(card3);
		
		JPanel card4 = new JPanel();
		card4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				card4.setBackground(ThemeDefault.getCard_mouseEntered_color());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				card4.setBackground(ThemeDefault.getCard_color());
			}
		});
		card4.setBackground(ThemeDefault.getCard_color());
		menu.add(card4);
		
		JPanel card5 = new JPanel();
		card5.setBackground(ThemeDefault.getMenu_background());
		menu.add(card5);
		
		JPanel card6 = new JPanel();
		card6.setBackground(ThemeDefault.getMenu_background());
		menu.add(card6);
		
		JPanel card7 = new JPanel();
		card7.setBackground(ThemeDefault.getMenu_background());
		menu.add(card7);
		
		JPanel card8 = new JPanel();
		card8.setBackground(ThemeDefault.getMenu_background());
		menu.add(card8);
		
		JPanel footer = new JPanel();
		footer.setBackground(ThemeDefault.getMenu_background());
		menu.add(footer);
		footer.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		JLabel txtFooter = new JLabel("           Bản quyền thuộc về           ");
		txtFooter.setVerticalAlignment(SwingConstants.BOTTOM);
		txtFooter.setForeground(Color.WHITE);
		txtFooter.setHorizontalAlignment(SwingConstants.CENTER);
		txtFooter.setFont(ThemeDefault.getFont_text_label());
		footer.add(txtFooter);
		
		JLabel HotelName = new JLabel("Khách sạn ABC");
		HotelName.setVerticalAlignment(SwingConstants.TOP);
		HotelName.setHorizontalAlignment(SwingConstants.CENTER);
		HotelName.setForeground(Color.WHITE);
		HotelName.setFont(ThemeDefault.getFont_text_label());
		footer.add(HotelName);
		
		String textTitle = "Xin chào, "+ DangNhap.getLogin().getTen() + " !";
		
		JPanel show = new JPanel();
		contentPane.add(show, BorderLayout.CENTER);
		show.setLayout(new BorderLayout(0, 0));
		
		JPanel tool = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tool.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		show.add(tool, BorderLayout.NORTH);
		
		JButton btnTraCuu = new JButton("Tra Cứu");
		btnTraCuu.setFont(ThemeDefault.getFont_text_field());
		btnTraCuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTraPhong search = new FormTraPhong();
				search.setVisible(true);
				search.setLocationRelativeTo(null);
			}
		});
		
		JButton btnTaoNV = new JButton("Tạo nhân viên");
		btnTaoNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaoNhanVien user = new TaoNhanVien();
				user.setVisible(true);
				user.setLocationRelativeTo(null);
			}
		});
		btnTaoNV.setFont(ThemeDefault.getFont_text_field());
		if (DangNhap.getLogin().getMaBP().equals("QL")) {
			btnTaoNV.setVisible(true);
		}else btnTaoNV.setVisible(false);
		
		tool.add(btnTaoNV);
		tool.add(btnTraCuu);
		
		
		JPanel ShowMain = new JPanel();
		show.add(ShowMain, BorderLayout.CENTER);
		ShowMain.setLayout(new GridLayout(0, 1, 0, 0));
		
		ds = new DSPhong();
		ShowMain.add(ds);
		
		
	}
	public void timPhong(String mp) {
		for (PanelPhong x: DSPhong.getDSPanel()) {
			if (!x.getPhong().getMaPhong().equalsIgnoreCase(mp)) {
				x.setVisible(false);
			}
		}
	}
	public static void CapNhatThongTinPhong() {
		if (kiemTraPhieuDat()) {
			thayDoiTrangThai();
		}
	}
	public static boolean kiemTraPhieuDat() {
		boolean kt = false;
		System.out.println("kiểm tra phiếu! - close");
		DSPD.clear();
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPD WHERE DATEDIFF(DAY, CTPD.NgayBDThue, GETDATE() ) = 1";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					kt = true;
					ChiTietPhieuDat pd = new ChiTietPhieuDat();
					pd.setMaPhieuDat(rs.getInt("MaPD"));
					pd.setHangPhong(rs.getString("HangPhong"));
					pd.setSL(rs.getInt("SLPhong"));
					DSPD.add(pd);
				}
				
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return kt;
	}
	public static void thayDoiTrangThai() {
		DSMaPhong.clear();
		Connection ketNoi = KetNoiDB.ketNoi();
		
		for (ChiTietPhieuDat pd : DSPD) {
			String sql = "SELECT * FROM Phong WHERE HangPhong = '"+pd.getHangPhong()+"' AND MaTrangThai = 'DT'";
			try {
				PreparedStatement ps = ketNoi.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				rs.last();
				if (rs.getRow() != 0) {
					rs.first();
					for (int i = 1; i<=pd.getSL(); i++) {
						rs.next();
						CapNhatDB.thayDoiTTPhong(rs.getString("MaPhong"), "SS");
						System.out.println("Thay đổi trạng thái phòng do khách hàng đặt mà chưa thuê ! - close");
					}
				}
				System.out.println("không có gì cả ! - close");
				rs.close();
				ps.close();
			}catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Xong 2- close! ");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
