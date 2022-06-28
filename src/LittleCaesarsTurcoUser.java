import java.awt.*;
import javax.swing.*;


public class LittleCaesarsTurcoUser {

	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Little Caesar's");
		JPanel panel = new JPanel();
		JLabel caesarsBanner = new JLabel(new ImageIcon("LittleCaesarsLogo.png"));
		JLabel paymentOptions = new JLabel(new ImageIcon("PaymentOptions.png"));
		caesarsBanner.setBounds(0, 30, 600, 100);
		caesarsBanner.setHorizontalAlignment(JLabel.CENTER);
		paymentOptions.setBounds(33, 420, 80, 175);
		panel.add(paymentOptions);
		panel.add(caesarsBanner);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		CaesarsComboBoxTurco cbt = new CaesarsButtonsTurco();
		
		cbt.addComponents(panel);
		cbt.setFrame(frame);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setSize(600, 650);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		
	}

}