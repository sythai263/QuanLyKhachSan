package HienThi;

import javax.swing.JPanel;
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Rectangle; 

import javax.swing.JComponent; 
import javax.swing.JFrame; 
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class ThongKe extends JPanel {

	/**
	 * Create the panel.
	 */
	public ThongKe() {
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(696, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		PieChart3 pie = new PieChart3();
		panel.add(pie);
		pie.setVisible(true);

	}
	
	class Slice { 
	    double value; 
	    Color color; 
	    public Slice(double value, Color color) { 
	     this.value = value; 
	     this.color = color; 
	    } 
	} 
	class PieChart3 extends JComponent { 
	    Slice[] slices = { new Slice(5, Color.black), 
	    new Slice(33, Color.green), 
	    new Slice(20, Color.yellow), new Slice(15, Color.red) }; 
	    PieChart3() {} 
	    public void paint(Graphics g) { 
	     drawPie((Graphics2D) g, getBounds(), slices); 
	    } 
	    void drawPie(Graphics2D g, Rectangle area, Slice[] slices) { 
	     double total = 0.0D; 
	     for (int i = 0; i < slices.length; i++) { 
	     total += slices[i].value; 
	     } 
	     double curValue = 0.0D; 
	     int startAngle = 0; 
	     for (int i = 0; i < slices.length; i++) { 
	     startAngle = (int) (curValue * 360/total); 
	     int arcAngle = (int) (slices[i].value * 360/total); 
	     g.setColor(slices[i].color); 
	     g.fillArc(area.x, area.y, area.width, area.height, 
	     startAngle, arcAngle); 
	     curValue += slices[i].value; 
	     } 
	    }
	}
}
