import java.awt.*;
import javax.swing.*;
public class CaesarsCheckoutLabelsTurco {
	private JFrame frame;
	protected JPanel panel;
	private JLabel [] lblInfo;
	private JLabel cvv;
	protected Font myFont;
	private JLabel caesarsBanner;
	protected Color littleCaesarsOrange;
	
	//Base class constructor, creates a new frame and panel as well as labels, a font and a color
	public CaesarsCheckoutLabelsTurco() {
		
		frame = new JFrame("Checkout");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(500, 550);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setLayout(null);
		frame.setContentPane(panel);
		myFont = new Font("Ariel", Font.BOLD, 14);
		
		String[] labelText = {"Name :", "Address :", "Credit Card Number :","Exp(mm/yyyy) :"
				, "Special Delivery Instructions (Optional) :"};
		lblInfo = new JLabel[5];
		littleCaesarsOrange = new Color(246, 130, 31);
		
		caesarsBanner = new JLabel(new ImageIcon("LittleCaesarsLogo2.png"));
		caesarsBanner.setBounds(0, 30, 500, 100);
		caesarsBanner.setHorizontalAlignment(JLabel.CENTER);
		panel.add(caesarsBanner);
		
		
		cvv = new JLabel("CVV :");
		cvv.setBounds(340, 285, 60, 25);
		cvv.setFont(myFont);
		cvv.setForeground(littleCaesarsOrange);
		panel.add(cvv);
		panel.setBackground(Color.white);
		
		for(int i = 0; i < 5; i++) {
			lblInfo[i] = labelMaker(labelText[i], i*40 + 165);
			panel.add(lblInfo[i]);
		}
	
		
	}
	
	//Method to assist in making labels
	public JLabel labelMaker(String text, int y) {
		JLabel label = new JLabel(text);
		label.setBounds(15, y, 300, 25);
		label.setFont(myFont);
		label.setForeground(littleCaesarsOrange);
		return label;
	}
	

	


	
}
