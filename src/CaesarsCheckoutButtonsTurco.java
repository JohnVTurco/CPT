import java.awt.Color;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.*;
public class CaesarsCheckoutButtonsTurco extends CaesarsCheckoutTextFieldsTurco implements ActionListener{
	private JButton [] btnActions;
	private JRadioButton [] deliveryPlace;
	private JComboBox<Integer> expMonth, expYear;
	private double grandTotal;
	private JButton btnPayAtDoor;
	private ButtonGroup bg;
	
	/*Sub class constructor, calls base class constructor and creates buttons,
	radio buttons, combo boxes and a button group*/
	public CaesarsCheckoutButtonsTurco(double totalCost) {
		super();

		String buttonNames [] = {"Confirm", "Clear", "Exit"};
		grandTotal = totalCost;
		btnActions = new JButton[3];
		deliveryPlace = new JRadioButton[2];
		
		for(int i = 0; i < 3; i++) {
			btnActions[i] = buttonCreator(buttonNames[i], i* 150 + 50);
			panel.add(btnActions[i]);
		}
		
		btnPayAtDoor = new JButton("Pay in person");
		btnPayAtDoor.setBounds(145, 430, 200, 20);
		btnPayAtDoor.addActionListener(this);
		panel.add(btnPayAtDoor);
		
		Integer[]years = {2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032};
		Integer[]months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		expYear = new JComboBox<Integer>(years);
		expYear.setBounds(215, 288, 100, 20);
		expMonth = new JComboBox<Integer>(months);
		expMonth.setBounds(130, 288, 80, 20);
		panel.add(expMonth);
		panel.add(expYear);
		
		deliveryPlace = new JRadioButton[2];
		String[] deliveryLocations = {"Apartment", "House"};
		JPanel rbPanel = new JPanel();
		rbPanel.setBounds(320, 160, 150, 80);
		rbPanel.setBackground(Color.white);
		rbPanel.setBorder(BorderFactory.createTitledBorder("Delivery Location"));
		rbPanel.setBackground(littleCaesarsOrange);
		
		bg = new ButtonGroup();
		
		
		for(int i = 0; i < 2; i++) {
			deliveryPlace[i] = new JRadioButton(deliveryLocations[i]);
			deliveryPlace[i].addActionListener(this);
			bg.add(deliveryPlace[i]);
			rbPanel.add(deliveryPlace[i]);
		}
		panel.add(rbPanel);
		
	}
	
	//Assists in creating buttons
	public JButton buttonCreator(String text, int x) {
		JButton button = new JButton(text);
		button.setBounds(x, 480, 100, 20);
		button.addActionListener(this);
		button.setFont(myFont);
		return button;
	}
	
	
	/*Method called when an action is performed by one of the components.
	Error checks and displays output or exits.*/
	public void actionPerformed(ActionEvent a) {
		
		/*If confirm button is selected, error check text fields to confirm valid inputs,
		then proceed if all inputs are valid*/
		if(a.getSource() == btnActions[0]) {
			String nums = "0123456789";
			boolean areNumbers = true;
			//Two for loops to check if the cvv and credit card are numbers only
			for(int i = 0; i < txtInfo[2].getText().length(); i++) {
				if(nums.indexOf(txtInfo[2].getText().charAt(i)) == -1) {
					areNumbers = false;
					break;
				}
			}
			for(int j = 0; j < txtInfo[3].getText().length(); j++) {
				if(nums.indexOf(txtInfo[3].getText().charAt(j)) == -1){
					areNumbers = false;
					break;
				}
			}
			//Calls error trap method to continue error checking if inputs are valid
			if(areNumbers) {
				errorTrapCheckout();
			}
			//Prompts user to fix cvv and credit card number fields
			else {
				JOptionPane.showMessageDialog(null, "Credit Card Number and CVV must be numbers only",
						"Payment Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		//Allows user to exit program if confirmed, when exit button is pressed 
		else if(a.getSource() == btnActions[1]) {
			for(int i = 0; i < 5; i++) {
				txtInfo[i].setText("");
			}
			bg.clearSelection();
			expMonth.setSelectedIndex(0);
			expYear.setSelectedIndex(0);
			txtInfo[0].requestFocus();
		}
		else if(a.getSource() == btnActions[2]){
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
					"Little Caesar's", JOptionPane.YES_NO_OPTION) == 0){
				JOptionPane.showMessageDialog(null, "Thank you for choosing Little Caesar's!",
						"Little Caesar's", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
		//Does nothing is either radio button is pressed
		else if(a.getSource() == deliveryPlace[0] || a.getSource() == deliveryPlace[1]) {
			
		}
		//Gives necessary information if user decides to pay in person
		else {
			if(txtInfo[1].getText().length() != 0) {
				if( deliveryPlace[0].isSelected() || deliveryPlace[1].isSelected()) {
					Calendar calendar = Calendar.getInstance();
					 calendar.add(Calendar.MINUTE, 25);
					JOptionPane.showMessageDialog(null, "A reciept will be brought to the delivery address with your order."
							+ "\nPlease have your desired payment method ready.\nEstimated time of arrival: "+calendar.getTime());
					JOptionPane.showMessageDialog(null, "Thank you for ordering from Little Caesar's!"
							+ "\nYour pizza will be delivered in 30 minutes or it's free!",
							"Little Caesars",JOptionPane.PLAIN_MESSAGE, new ImageIcon("LittleCaesarsIcon.png"));
					System.exit(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter both an address and a delivery location",
							"No Address No Pizza", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter both an address and a delivery location",
						"No Address No Pizza", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
	
	//Method to assist error checking
	public void errorTrapCheckout() {
		//Checks if any text field is left blank
		if(txtInfo[0].getText().length() == 0 || txtInfo[1].getText().length() == 0
				|| txtInfo[2].getText().length() == 0 || txtInfo[3].getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please complete required fields", "Information Error", JOptionPane.ERROR_MESSAGE);
		}
		//Checks that the credit card number is a valid number of digits
		else if(txtInfo[2].getText().length() != 16) {
			JOptionPane.showMessageDialog(null, "Invalid credit card number", "Payment Error", JOptionPane.ERROR_MESSAGE);
		}
		//Checks that the cvv is a valid number of digits
		else if(txtInfo[3].getText().length() != 3) {
			JOptionPane.showMessageDialog(null, "Invalid CVV", "Payment Error", JOptionPane.ERROR_MESSAGE);
		}
		//Proceeds to checkout if the user chose a delivery location
		else if(deliveryPlace[0].isSelected() || deliveryPlace[1].isSelected()) {
			NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
			JTextArea myArea = new JTextArea();
			String placeOfDelivery = "Apartment", deliveryInstructions = "N/A";
			if(deliveryPlace[1].isSelected()) {
				placeOfDelivery = "House";
			}
			if(txtInfo[4].getText().length() != 0) {
				deliveryInstructions = txtInfo[4].getText();
			}
			
			//Displays receipt and closes program
			myArea.setText("Name: \t\t"+txtInfo[0].getText()+"\n\n__Shipping Info.__\nAddress:\t\t"
			+txtInfo[1].getText()+"\nDelivery Place: \t\t"+placeOfDelivery+"\n\n__Billing Info.__\nCredit Card: \t\t"
					+ "XXXX-XXXX-XXXX-"+txtInfo[2].getText().substring(12)+"\nGrand Total: \t\t"
			+currency.format(grandTotal)+"\n\n__Delivery Instructions__\n"+deliveryInstructions);
			JOptionPane.showMessageDialog(null, myArea, "Reciept", JOptionPane.PLAIN_MESSAGE);
			 Calendar calendar = Calendar.getInstance();
			 calendar.add(Calendar.MINUTE, 25);
			JOptionPane.showMessageDialog(null, "Thank you for ordering from Little Caesar's!"
					+ "\nYour pizza will be delivered in 30 minutes or it's free!\nEstimated time of arrival: "+calendar.getTime(),
					"Little Caesars",JOptionPane.PLAIN_MESSAGE, new ImageIcon("LittleCaesarsIcon.png"));
			System.exit(0);
		}
		//Tells user to enter a delivery location
		else {
			JOptionPane.showMessageDialog(null, "Pleae enter a delivery location", "Information Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	

}
