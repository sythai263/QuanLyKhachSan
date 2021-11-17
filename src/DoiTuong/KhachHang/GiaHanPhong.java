package DoiTuong.KhachHang;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import HienThi.theme.ThemeDefault;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import DieuKhien.CapNhatDB;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class GiaHanPhong extends JFrame {

	private JPanel contentPane;
	private String maPhong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaHanPhong frame = new GiaHanPhong("");
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
	public GiaHanPhong(String maPhong) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.POPUP);
		this.maPhong=maPhong;
		setIconImage(Toolkit.getDefaultToolkit().getImage(GiaHanPhong.class.getResource("/HienThi/img/logo_hotel.png")));
		setTitle("Gia H\u1EA1n Ph\u00F2ng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(ThemeDefault.getBackground_title_color());
		setContentPane(contentPane);
		
		JPanel Header = new JPanel();
		FlowLayout fl_Header = (FlowLayout) Header.getLayout();
		fl_Header.setVgap(10);
		Header.setBackground(contentPane.getBackground());
		contentPane.add(Header, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Gia Hạn");
		lblTitle.setFont(ThemeDefault.getFont_text_title());
		lblTitle.setForeground(ThemeDefault.getText_color_white());
		Header.add(lblTitle);
		
		JPanel Body = new JPanel();
		Body.setBackground(contentPane.getBackground());
		contentPane.add(Body, BorderLayout.CENTER);
		Body.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(47, 0, 280, 160);
		Body.add(panel);
		panel.setLayout(null);
		
		JLabel lblPhong = new JLabel("Phòng: "+ this.maPhong);
		lblPhong.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhong.setBounds(10, 11, 260, 25);
		lblPhong.setFont(ThemeDefault.getFont_text_panel());
		panel.add(lblPhong);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 204), 1, true), "Số ngày cần gia hạn thêm", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		spinner.setBounds(32, 47, 216, 36);
		panel.add(spinner);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 109, 260, 40);
		panel.add(panel_1);
		
		JButton btnOK = new JButton("Gia hạn");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapNhatDB.giaHanPhong(maPhong, (int) spinner.getValue());
				JOptionPane.showMessageDialog(null, "Gia hạn phòng "+ maPhong+" thành công !");
				dispose();
			}
		});
		panel_1.add(btnOK);
		
		JButton btnCancel = new JButton("Hủy bỏ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnCancel);
		
	}
	

}
