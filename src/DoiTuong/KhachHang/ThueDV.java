package DoiTuong.KhachHang;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DieuKhien.KetNoiDB;
import DoiTuong.KhachSan.CTDV;
import DoiTuong.KhachSan.DichVu;
import HienThi.theme.ThemeDefault;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class ThueDV extends JFrame {
	private CTDV ctdv = new CTDV() ;
	private ArrayList<DichVu> dsdv = new ArrayList<DichVu>();
	private ArrayList<DichVu> dvChon = new ArrayList<DichVu>();
	private ArrayList<JCheckBox> cbxList = new ArrayList<JCheckBox>();
	private JPanel show = new JPanel(), cacDV = new JPanel();
	private JButton btnOK = new JButton("OK");
	private JButton btnHuy = new JButton("Hủy");
	private JPanel footer = new JPanel();
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThueDV frame = new ThueDV(400011);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};

	/**
	 * Create the frame.
	 */
	public ThueDV(int mapt) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThueDV.class.getResource("/HienThi/img/logo_hotel.png")));
		

		setTitle("D\u1ECBch v\u1EE5 \u0111i k\u00E8m");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ctdv.setMaPT(mapt);
		
		
		//Tạo phần đầu 
		JPanel header = new JPanel();
		FlowLayout flowLayout = (FlowLayout) header.getLayout();
		flowLayout.setHgap(20);
		header.setBackground(ThemeDefault.getBackground_title_color());
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("D\u1ECACH V\u1EE4");
		lblTitle.setFont(ThemeDefault.getFont_text_title());
		lblTitle.setForeground(ThemeDefault.getText_color_white());
		header.add(lblTitle);
		
		
		
		
		//Tạo phần thân
		contentPane.add(show, BorderLayout.CENTER);
		show.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		show.add(cacDV);
		show.setBackground(header.getBackground());
		cacDV.setLayout(new GridLayout(0, 2, 10, 5));
		taoCheckBox();
		
		
		
		
		//tạo Footer
		contentPane.add(footer,BorderLayout.SOUTH);
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		footer.add(btnOK);
		footer.add(btnHuy);
		footer.setBackground(header.getBackground());
			{
				btnOK.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ctdv.getDv().clear();
					// TODO Auto-generated method stub
					for (int i = 0; i<cbxList.size(); i++) {
						if (cbxList.get(i).isSelected()) {
							ctdv.getDv().add(dsdv.get(i));
						}
						
					}
					ctdv.LuuCTDV();
					JOptionPane.showMessageDialog(null, "Đã thêm dịch vụ thành công !");
					dispose();
					
				}
			});
			
				btnHuy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					
				}
			});
			}
			
			
	}

	//tạo các checkbox
	public void taoCheckBox() {
		layDSDV();
		for (DichVu a: dsdv) {
			JCheckBox jCheck = new JCheckBox(a.getTenDV());
			jCheck.setHorizontalAlignment(SwingConstants.LEADING);
			jCheck.setFont(ThemeDefault.getFont_text_label());
			cbxList.add(jCheck);
			cacDV.add(jCheck);
		}
	}
	
	public void chonDV() {
		for (JCheckBox cb: cbxList) {
			if (cb.isSelected()) {
				for (DichVu dv: dsdv) {
					if (cb.getText().contentEquals(dv.getTenDV())) {
						dvChon.add(dv);
					}
				}
			}
		}
	}
	//lấy DS các dịch vụ đang có
	public void layDSDV() {
		Connection ketNoi = KetNoiDB.ketNoi();
		String sql = "SELECT * FROM DV";
		try {
			PreparedStatement ps = ketNoi.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DichVu dv = new DichVu();
				dv.setMaDV(rs.getString("MaDV"));
				dv.setTenDV(rs.getNString("TenDV"));
				dv.setDonGia(rs.getInt("DonGia"));
				dsdv.add(dv);
			}
			rs.close();
			ps.close();
			ketNoi.close();
			System.out.println("Đã lấy DSDV thành công - Close !");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
