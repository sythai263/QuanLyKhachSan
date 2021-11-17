package DoiTuong.KhachHang;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HienThi.DangNhap;
import HienThi.TrangChinh;
import HienThi.theme.ThemeDefault;

import java.awt.Toolkit;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.sun.net.httpserver.Authenticator.Result;

import DieuKhien.CapNhatDB;
import DieuKhien.KetNoiDB;
import DieuKhien.QuyDoi;
import DoiTuong.KhachSan.HoaDon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
public class TraPhong extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private int maPT, maHD, giaPhong;
	private int Tong = 0;
	private String tenKH = "", tenPhong="", maPhong =""; 
	private HoaDon hd = new HoaDon();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraPhong frame = new TraPhong("EC12");
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
	public TraPhong(String maPhong) {
		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setTitle("Tr\u1EA3 ph\u00F2ng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 420);
		contentPane = new JPanel();
		contentPane.setBackground(ThemeDefault.getBackground_title_color());
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		this.maPhong = maPhong;
		this.maPT = CapNhatDB.layMaPT(maPhong);
		
		layHangPhong(maPhong);
		layTTKH(maPT);
		taoFrame();
		
	}
/*
	public TraPhong(int maPT) {
		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\JAVA\\java\\QuanLyKhachSan\\src\\HienThi\\img\\logo_hotel.png"));
		setTitle("Tr\u1EA3 ph\u00F2ng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 420);
		contentPane = new JPanel();
		contentPane.setBackground(ThemeDefault.getBackground_title_color());
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		this.maPhong = layMaPhong(maPT);
		this.maPT = maPT;
		layTTKH(maPT);
		taoFrame();

	}
*/
	public void layDSDV(int maPT) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		tbl.setNumRows(0);
		Vector vt ;
		
		String sql = 	"SELECT CTDV.MaDV, DV.TenDV, DV.DonGia "+
						"FROM CTDV inner join DV on CTDV.MaDV = DV.MaDV "+
							 " WHERE CTDV.MaPT = '"+maPT+"'";
		Connection ketNoi = KetNoiDB.ketNoi();
		try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 1, thanhTien = 0;
            long milis = System.currentTimeMillis();
            int soNgayO = CapNhatDB.tinhSoNgayO(maPT);
            Date ngayDen = new Date(milis);
            while (rs.next()){
            	
            	
                vt = new Vector ();
                vt.add(i);
                vt.add(rs.getString("TenDV"));
                vt.add(QuyDoi.QUSo(rs.getInt("DonGia")));
                vt.add(soNgayO);
                thanhTien = rs.getInt("DonGia") * soNgayO;
                vt.add(QuyDoi.QUSo(thanhTien));
                tbl.addRow(vt);
                Tong+= thanhTien;
                i++;
                        
            }
            {
            	Vector vt1 = new Vector();
                vt1.add(i);
                vt1.add(tenPhong);
                vt1.add(QuyDoi.QUSo(giaPhong));
                vt1.add(soNgayO);
                vt1.add(QuyDoi.QUSo(giaPhong*soNgayO));
                tbl.addRow(vt1);
                Tong+= giaPhong*soNgayO;
            }
            
            table.setModel(tbl);
            ps.close();
            rs.close();
            ketNoi.close();
            
                
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                
            }
	}

	public void layTTKH(int maPT) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT KhachHang.Ho, KhachHang.Ten, PhieuThue.MaHD " + 
				"FROM KhachHang inner join PhieuThue on KhachHang.SoCMND = PhieuThue.SoCMND "+
				"WHERE PhieuThue.MaPT = '"+maPT+"'";
		try {
			
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tenKH = rs.getNString("Ho") +" "+ rs.getNString("Ten");
				
				maHD = rs.getInt("MaHD");
						
			}
			rs.close();
			ps.close();
			ketNoi.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void layHangPhong(String maPhong) {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT GiaPhong.Gia, HangPhong.TenHP "
					+ "FROM GiaPhong inner join Phong on GiaPhong.HangPhong = Phong.HangPhong "
					+ "inner join HangPhong on GiaPhong.HangPhong = HangPhong.HangPhong "
					+ "WHERE Phong.MaPhong = '"+maPhong+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.giaPhong = rs.getInt("Gia");
				this.tenPhong = rs.getNString("TenHP");
			}
			rs.close();
			ps.close();
			ketNoi.close();
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	public void taoFrame() {
		JPanel header = new JPanel();
		header.setBackground(contentPane.getBackground());
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel TITLE = new JLabel("TR\u1EA2 PH\u00D2NG");
		TITLE.setHorizontalAlignment(SwingConstants.CENTER);
		TITLE.setFont(ThemeDefault.getFont_text_title());
		TITLE.setForeground(ThemeDefault.getText_color_white());
		header.add(TITLE, BorderLayout.NORTH);
		
		JPanel show = new JPanel();
		contentPane.add(show, BorderLayout.CENTER);
		show.setLayout(null);
		show.setBackground(contentPane.getBackground());
		
		JPanel info = new JPanel();
		info.setBounds(45, 25, 494, 221);
		show.add(info);
		info.setLayout(null);
		
		JPanel TTKH = new JPanel();
		TTKH.setBorder(new LineBorder(Color.BLACK, 1, true));
		TTKH.setBounds(10, 11, 474, 35);
		info.add(TTKH);
		TTKH.setLayout(null);
		
		String lblKH = "Khách hàng: "+ tenKH;
		JLabel lblTenKH = new JLabel(lblKH);
		lblTenKH.setFont(ThemeDefault.getFont_text_label());
		lblTenKH.setBounds(10, 5, 285, 25);
		TTKH.add(lblTenKH);
		
		String maHD = "Mã HĐ: "+ this.maHD;
		JLabel lblMaHD = new JLabel(maHD);
		lblMaHD.setFont(ThemeDefault.getFont_text_label());
		lblMaHD.setBounds(305, 5, 159, 25);
		TTKH.add(lblMaHD);
		
		JPanel ChiTiet = new JPanel();
		ChiTiet.setBounds(10, 82, 474, 139);
		info.add(ChiTiet);
		ChiTiet.setLayout(null);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setBorder(null);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFont(ThemeDefault.getFont_text_label());
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Tên DV", "Đơn giá", "Số ngày thuê", "Thành tiền"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.setBounds(27, 11, 400, 108);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scroll.setBounds(37, 0, 400, 139);
		scroll.setViewportView(table);
		ChiTiet.add(scroll);
		layDSDV(maPT);
		
		JLabel lblCacDV = new JLabel("C\u00E1c d\u1ECBch v\u1EE5 \u0111\u00E3 \u0111\u0103ng k\u00FD s\u1EED d\u1EE5ng:");
		lblCacDV.setVerticalAlignment(SwingConstants.TOP);
		lblCacDV.setBounds(30, 57, 454, 26);
		lblCacDV.setFont(ThemeDefault.getFont_text_label());
		info.add(lblCacDV);
		
		JPanel giatien = new JPanel();
		giatien.setBounds(45, 245, 494, 41);
		show.add(giatien);
		giatien.setLayout(null);
		
		JPanel tien = new JPanel();
		tien.setBounds(126, 5, 319, 30);
		giatien.add(tien);
		tien.setLayout(null);
		
		JLabel lblTongCong = new JLabel("Tổng cộng: "+ QuyDoi.QUSo(Tong) + " vnđ.");
		lblTongCong.setVerticalAlignment(SwingConstants.TOP);
		lblTongCong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTongCong.setFont(ThemeDefault.getFont_text_panel());
		lblTongCong.setBounds(10, 0, 299, 30);
		tien.add(lblTongCong);
		
		JPanel footer = new JPanel();
		FlowLayout fl_footer = (FlowLayout) footer.getLayout();
		fl_footer.setHgap(40);
		fl_footer.setVgap(10);
		contentPane.add(footer, BorderLayout.SOUTH);
		footer.setBackground(contentPane.getBackground());
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bamOK();
			}
		});
		footer.add(btnOK);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		footer.add(btnHuy);
	}

	private String layMaPhong(int maPT) {
		String maPhong= "";
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM CTPT WHERE MaPT ='"+maPT+"'";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					maPhong = rs.getString("MaPhong");
				}
			}
			ps.close();
			rs.close();
			ketNoi.close();
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}catch (Exception e){
			System.out.print(e.getMessage());
		}
		return maPhong;
	}
	public void bamOK() {
		hd.setGia(Tong);
		hd.taoMaHD();
		CapNhatDB.updatePhieuThue(maPT, hd.getMaHD());
		CapNhatDB.updateCTPT(maPT, CapNhatDB.NgayHTai());
		CapNhatDB.thayDoiTTPhong(maPhong, "SS");
		JOptionPane.showMessageDialog(null, "Đã trả phòng thành công !", "Thông báo", 1);
		dispose();
		TrangChinh.CapNhatThongTinPhong();
	}
	
}
