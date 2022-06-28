import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarsCheckBoxTurco extends CaesarsComboBoxTurco implements ActionListener{
	protected JLabel lblToppingsCost;
	private JLabel toppingsMessage;
	protected double toppingsPrice;
	protected JCheckBox [] toppings;
	protected int toppingCounter;
	protected String toppingNames[] = {"Pineapple", "Pepperoni", "Red Peppers",
			"Bacon", "Onions", "Mushrooms", "Jalepenos", "Extra Cheese"};
	protected String toppingSelections = "";
	
	//Sub class constructor, calls base class constructor and also creates labels and check boxes
	public CaesarsCheckBoxTurco() {
		super();
		toppingCounter = 0;

		
		toppings = new JCheckBox[8];
		
		for(int i =0; i < 8; i++) {
			toppings[i] = new JCheckBox(toppingNames[i]);
			toppings[i].addActionListener(this);
		}
		
		toppingsMessage = new JLabel("First three (3) toppings are free!");
		Font fontOne = new Font("Ariel", Font.PLAIN, 11);
		toppingsMessage.setBounds(150, 330, 265, 15);
		toppingsMessage.setHorizontalAlignment(JLabel.CENTER);
		toppingsMessage.setFont(fontOne);
		
		lblToppingsCost = new JLabel("$0.00");
		lblToppingsCost.setFont(labelFont);
		lblToppingsCost.setBounds(250, 360, 80, 20);
		lblToppingsCost.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		lblToppingsCost.setHorizontalAlignment(JLabel.CENTER);
				
	}
	
	//Method to add recently created components to panel, also calls base class addComponents method
	public void addComponents(JPanel panel) {
		super.addComponents(panel);
		JPanel cbPanel = new JPanel();
		cbPanel.setBounds(150, 170, 265, 150);
		cbPanel.setLayout(new GridLayout(4,2));
		cbPanel.setBackground(caesarsOrange);
		cbPanel.setBorder(BorderFactory.createTitledBorder(null, "Toppings", 0, 0, myFont, Color.white));
		panel.add(cbPanel);
		for(int i = 0; i < 8; i++) {
			cbPanel.add(toppings[i]);
		}
		
		panel.add(toppingsMessage);
		panel.add(lblToppingsCost);
		
	}
	
	//Edits topping price is a checkbox is edited, if not, calls base class actionPerformed method
	public void actionPerformed(ActionEvent a) {
		
		//Checks if toppings were selected or unselected and adjusts price accordingly, when an action comes from a checkbox
		if(a.getSource() instanceof JCheckBox) {
			isCalculated = false;
			for(int i = 0; i < 8; i++) {
					if(a.getSource() == toppings[i]) {
						if(toppings[i].isSelected()) {
							toppingCounter++;
						}
						else {
							toppingCounter--;
						}
					}
					
			}
			

			if(toppingCounter > 3) {
				toppingsPrice = (toppingCounter - 3);
			}
			else {
				toppingsPrice = 0;
			}
			lblToppingsCost.setText(currency.format(toppingsPrice));
		}
		else {
			super.actionPerformed(a);
		}
		
	
	}


}
