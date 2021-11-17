package DoiTuong.KhachSan;

import javax.swing.JLabel;
import javax.swing.JPanel;

import HienThi.theme.ThemeDefault;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PanelPhong extends JPanel {
	
	private JPanel main;
	private  Phong phong;
	
	
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	/**
	 * Create the panel.
	 */
	
	public PanelPhong(Phong phong) {
		setPhong(phong);
		main = new JPanel();
		add(main);
		main.setToolTipText("Mã phòng: "+ phong.getMaPhong()+ ", Trạng thái: " + phong.getTenTrangThai());
		
		setColor(phong);
		
		main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setColorMouse(phong);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setColor(phong);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
					DetailRoom detail = new DetailRoom(phong);
					detail.setVisible(true);
					detail.setLocationRelativeTo(null);
				
			}
		});
		setLayout(new GridLayout(0, 1, 0, 0));
		main.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel maPhong = new JLabel(phong.getMaPhong());
		maPhong.setHorizontalAlignment(SwingConstants.CENTER);
		maPhong.setFont(ThemeDefault.getFont_text_panel());
		main.add(maPhong);
		
		
	}
	public void setColor(Phong phong) {
		switch (phong.getMaTrangThai()) {
		case "SS":
			main.setBackground(ThemeDefault.getSs_bg_color());
			break;
		case "BT":
			main.setBackground(ThemeDefault.getBt_bg_color());
			break;
		case "DP":
			main.setBackground(ThemeDefault.getDp_bg_color());
			break;
		case "DT":
			main.setBackground(ThemeDefault.getDt_bg_color());
			break;
		case "CK": 
			main.setBackground(ThemeDefault.getCk_bg_color());
			break;
		}
	}
	public void setColorMouse(Phong phong) {
		switch (phong.getMaTrangThai()) {
		case "SS":
			main.setBackground(ThemeDefault.getSs_bg_mouseEntered_color());
			break;
		case "BT":
			main.setBackground(ThemeDefault.getBt_bg_mouseEntered_color());
			break;
		case "DP":
			main.setBackground(ThemeDefault.getDp_bg_mouseEntered_color());
			break;
		case "DT":
			main.setBackground(ThemeDefault.getDt_bg_mouseEntered_color());
			break;
		case "CK": 
			main.setBackground(ThemeDefault.getCk_bg_mouseEntered_color());
			break;
		}
	}
	public PanelPhong() {
		super();
	}
	public void refreshPanelPhong() {
		phong.refreshTTPhong();
		setColor(phong);
		main.setToolTipText("Mã phòng: "+ phong.getMaPhong()+ ", Trạng thái: " + phong.getTenTrangThai());
	}
	
}
