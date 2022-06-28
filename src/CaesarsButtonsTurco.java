import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
public class CaesarsButtonsTurco extends CaesarsRadioButtonsTurco implements ActionListener{
	private JLabel [] totalPrices;
	private JButton[] buttons;
	private JLabel[] priceNames;
	private double grandTotal;
	private String drinkSelections = "";
	
	//Sub class constructor that calls base class constructor and creates buttons and labels
	public CaesarsButtonsTurco() {
		super();
		totalPrices = new JLabel[4];
		buttons = new JButton[4];
		priceNames = new JLabel[4];
		String[] buttonNames = {"CALCULATE", "CLEAR", "CHECKOUT", "EXIT"};
		String[] labelNames = {"SUBTOTAL :", "DELIVERY FEE :", "HST :", "GRAND TOTAL :"};
		
		for(int i = 0; i < 4; i++) {
			totalPrices[i] = labelPriceMaker(i*40 + 430);
			buttons[i] = buttonCreator(buttonNames[i], i* 45 + 420);
			priceNames[i] = labelPriceNamesMaker(labelNames[i], i*40 +430);
		}
		
	}
	
	//Method to assist button creating
	public JButton buttonCreator(String name, int y) {
		JButton button = new JButton(name);
		button.setBounds(445, y, 130, 35);
		button.addActionListener(this);
		button.setForeground(caesarsOrange);
		button.setFont(myFont);
		return button;
	}
	
	//Method to assist price label making
	public JLabel labelPriceMaker(int y) {
		JLabel label = new JLabel("$0.00");
		label.setBounds(290, y, 110, 30);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(labelFont);
		label.setBackground(Color.white);
		label.setOpaque(true);
		return label;
	}
	
	//Method that assists label making 
	public JLabel labelPriceNamesMaker(String title, int y) {
		JLabel label = new JLabel(title);
		label.setBounds(170, y, 110, 30);
		label.setFont(labelFont);
		return label;
	}

	//Method to add recently created components to panel, also calls base class addComponents method
	public void addComponents(JPanel panel) {
		super.addComponents(panel);
		

		
		for(int i = 0; i <4; i++) {
			panel.add(totalPrices[i]);
			panel.add(buttons[i]);
			panel.add(priceNames[i]);
		}
		
	}
	
	//Method to check which button is pressed and to act accordingly, 
	//calls base class action performed method if a button is not the source of the action
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() instanceof JButton) {
			double totalCost, delivery, tax;
			
			//Calculates the prices and sets labels accordingly when calculate button is pressed
			if(a.getSource() == buttons[0]) {
				
					totalCost = pizzaCost + toppingsPrice + drinkTotalCost;
					totalPrices[0].setText(currency.format(totalCost));
					if(totalCost < 15) {
						totalPrices[1].setBackground(Color.white);
						totalPrices[1].setText("$3.00");
						delivery = 3;
					}
					else {
						totalPrices[1].setText("FREE");
						totalPrices[1].setBackground(Color.green);
						delivery = 0;
					}
					tax = 0.13 * totalCost;
					totalPrices[2].setText(currency.format(tax));
					grandTotal = totalCost + delivery + tax;
					totalPrices[3].setText(currency.format(grandTotal));
					isCalculated = true;
					toppingSelections = "";
					drinkSelections = "";
				}
				
			//Clears all fields and necessary variables when clear button is pressed
			else if(a.getSource() == buttons[1]){
				bg.clearSelection();
				pizzaCost = 0;
				lblPizzaCost.setText("$0.00");
				for(int i = 0; i < 8; i++) {
					toppings[i].setSelected(false);
				}
				toppingsPrice = 0;
				lblToppingsCost.setText("$0.00");
				for(int j = 0; j < 4; j++) {
					numOfDrinks[j].setSelectedIndex(0);
					totalPrices[j].setText("$0.00");
				}
				totalPrices[1].setBackground(Color.white);
				drinkTotalCost = 0;
				lblDrinkCost.setText("$0.00");
				toppingCounter = 0;
				sizeSelected = false;
				isCalculated = false;
				sizeOrdered = "";
				toppingSelections = "";
			}
			
			//Proceeds to checkout if order is calculated and pizza size is selected when checkout button is pressed
			else if(a.getSource() == buttons[2]) {
				if(sizeSelected) {
					if(isCalculated) {
						int k = 0;
						for(int i = 0;i < 8; i++) {
							if(toppings[i].isSelected()) {
								if(k % 2 == 0) {
									toppingSelections += toppingNames[i]+ "\t";
								}
								else {
									toppingSelections += toppingNames[i]+ "\n";
								}
								k++;
							}
						}
						if(toppingSelections == "") {
							toppingSelections = "None";
						}
						
						String[] drinkNames = {"Water", "Sprite", "Coke", "Pepsi"};
						
						for(int j = 0; j < 4; j++) {
							if(numOfDrinks[j].getSelectedIndex() > 0) {
								drinkSelections += drinkNames[j]+"\tQuantity: "+numOfDrinks[j].getSelectedIndex() +"\n";
							}
						}
						if(drinkSelections == "") {
							drinkSelections = "None";
						}
						JTextArea area = new JTextArea();
						
						//Creates a copy of your order for confirmation and creates an object 
						//of a new class if information is correct, disposes of current frame
						area.setText("Here is you order:\n\n" +"__Pizza Size__\n"+sizeOrdered+"\n\n__Toppings__\n"+
						toppingSelections+"\n\n__Drinks__\n"+drinkSelections+"\n\nGrand Total\t\t"+currency.format(grandTotal)+"\n\nIs this order correct?");
						if(JOptionPane.showConfirmDialog(null, area, "Little Caesar's", JOptionPane.YES_NO_OPTION) == 0) {
						
							localFrame.dispose();
							CaesarsCheckoutLabelsTurco cct = new CaesarsCheckoutButtonsTurco(grandTotal);
							
							
						
						}
					}
					//Tells user to calculate order in order to proceed
					else
					{
						JOptionPane.showMessageDialog(null, "You must calculate your order", "Checkout Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				//Tells user to select a pizza size in order to proceed
				else {
					JOptionPane.showMessageDialog(null, "Your order could not be completed!\nPlease select a pizza size.",
							"Critical error!", JOptionPane.ERROR_MESSAGE);
				}
			
			}
			//Exits program if confirmed by user, when the exit button is selected
			else {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Little Caesar's", JOptionPane.YES_NO_OPTION) == 0){
					JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesar's!", "Little Caesar's", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
		}	
		else {
			super.actionPerformed(a);
		}
		
	}
	
}
