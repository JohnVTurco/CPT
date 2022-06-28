import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class CaesarsComboBoxTurco implements ActionListener, WindowListener{
	protected JComboBox[] numOfDrinks;
	private JLabel [] lblDrinks;
	protected JLabel lblDrinkCost;
	protected double drinkTotalCost;
	protected NumberFormat currency;
	protected Font myFont, labelFont;
	protected Color caesarsOrange;
	protected boolean check = false;
	protected  JFrame localFrame;
	protected boolean isCalculated;
	
	//Base class constructor, creates a font, color, local frame, labels and combo boxes
	public CaesarsComboBoxTurco() {
		localFrame = new JFrame();
		
		currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
		String[] drinkNames = {"Water ($1.00)", "Sprite ($2.00)", "Coke ($2.00)", "Pepsi ($1.50)"};
		Integer[] nums = {0, 1, 2, 3, 4, 5, 6};
		
		numOfDrinks = new JComboBox[4];
		
		for(int i = 0; i < 4; i++) {
			numOfDrinks[i] = new JComboBox<Integer>(nums);
			numOfDrinks[i].addActionListener(this);
			numOfDrinks[i].setBounds(95, i*30 + 30, 60, 20);
		}
	
		lblDrinks = new JLabel[4];
		for(int i = 0; i < 4; i++) {
			lblDrinks[i] = labelMaker(drinkNames[i], i * 30 + 30);
		}

		lblDrinkCost = new JLabel("$0.00");
		lblDrinkCost.setFont(labelFont);
		lblDrinkCost.setBounds(470, 360, 80, 20);
		lblDrinkCost.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		lblDrinkCost.setHorizontalAlignment(JLabel.CENTER);
		
		 myFont = new Font("Ariel", Font.BOLD, 16);
		 labelFont = new Font("Ariel", Font.BOLD, 12);
		 caesarsOrange = new Color(253, 97, 32);
		 
			
	}
	
	//Method to assist in making labels
	public JLabel labelMaker(String name, int y) {
		JLabel label = new JLabel(name);
		label.setBounds(10, y, 100, 20);
		return label;
	}
	
	//Method that adds components to the panel
	public void addComponents(JPanel panel) {
		Font myFont = new Font("Ariel", Font.BOLD, 16);
		Color caesarsOrange = new Color(253, 97, 32);
		JPanel cboPanel = new JPanel();
		cboPanel.setBackground(caesarsOrange);
		cboPanel.setLayout(null);
		cboPanel.setBounds(430, 170, 160, 150);
		cboPanel.setBorder(BorderFactory.createTitledBorder(null, "Beverages", 0, 0, myFont, Color.white));
		
		for(int i = 0; i < 4; i++){
			cboPanel.add(numOfDrinks[i]);
			cboPanel.add(lblDrinks[i]);
		}
		panel.add(lblDrinkCost);
		panel.add(cboPanel);
		
	}

	//Method to set the local frame equal to the frame, allowing the window listener to work
	public void setFrame(JFrame frame) {
		localFrame = frame;
		localFrame.addWindowListener(this);
	}

	//Method sets the drink cost label when combo boxes receive input
	public void actionPerformed(ActionEvent e) {
		drinkTotalCost = 0;
		isCalculated = false;
		double [] drinkPrices = {1, 2, 2, 1.5};
		//For loop to check which combo box is changed and set the drink total price accordingly
		for(int i =0; i < 4; i++) {
			drinkTotalCost += numOfDrinks[i].getSelectedIndex() * drinkPrices[i];
		}
		
		lblDrinkCost.setText(currency.format(drinkTotalCost));
		
	}

	public void windowOpened(WindowEvent e) {
	}
	
	//Window listener to confirm that the user wants to exit when the close button is pressed 
	public void windowClosing(WindowEvent e) {
		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Little Caesar's", JOptionPane.YES_NO_OPTION) == 0){
			JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesar's!", "Little Caesar's", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
}
