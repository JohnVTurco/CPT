import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarsRadioButtonsTurco extends CaesarsCheckBoxTurco implements ActionListener{
	private JRadioButton [] pizzaSize;
	protected JLabel lblPizzaCost;
	protected double pizzaCost;
	protected boolean sizeSelected;
	protected ButtonGroup bg;
	private JLabel deliveryTip;
	protected String sizeOrdered;
	private String [] sizeNames = {"Small", "Medium", "Large", "Party"};

	//Sub class constructor that calls the base class constructor, also creates radio buttons and labels
	public CaesarsRadioButtonsTurco(){
		super();
		pizzaSize = new JRadioButton[4];
		
		bg = new ButtonGroup();
		
		for(int i = 0; i < 4; i++) {
			pizzaSize[i] = radioButtonMaker(bg, sizeNames[i]);
		}
		
		lblPizzaCost = new JLabel("$0.00");
		lblPizzaCost.setFont(labelFont);
		lblPizzaCost.setBounds(33, 360, 80, 20);
		lblPizzaCost.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		lblPizzaCost.setHorizontalAlignment(JLabel.CENTER);
		
		sizeSelected = false;
		
		deliveryTip = new JLabel("Delivery on orders $15 or more is FREE!");
		deliveryTip.setBounds(0,400, 580, 20);
		deliveryTip.setHorizontalAlignment(JLabel.CENTER);
		
	}
	
	//Assists in creating radio buttons
	public JRadioButton radioButtonMaker(ButtonGroup b, String name) {
		JRadioButton button = new JRadioButton(name);
		b.add(button);
		button.addActionListener(this);
		return button;
	}
	
	//Method to add recently created components to panel, also calls base class addComponents method
	public void addComponents(JPanel panel) {
		super.addComponents(panel);
		JPanel rbPanel = new JPanel();
		rbPanel.setBounds(10, 170, 125, 150);
		rbPanel.setBackground(caesarsOrange);
		rbPanel.setLayout(new GridLayout(4,1));
		rbPanel.setBorder(BorderFactory.createTitledBorder(null, "Size",0, 0, myFont, Color.white));
		panel.add(rbPanel);
		panel.add(deliveryTip);
		
		for(int i = 0; i < 4; i++) {
			rbPanel.add(pizzaSize[i]);
		}
		
		panel.add(lblPizzaCost);
		
		
	}
	
	//Sets the price of the pizza based on the price if a radio button is pressed, 
	//if not, calls base class action performed method
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()instanceof JRadioButton) {
			isCalculated = false;
			sizeSelected = true;
			double [] sizePrices = {7.99, 8.99, 9.99, 10.99};
			
			//Checks which size was selected and adjusts price accordingly
			for(int i = 0; i < 4; i++) {
				if(pizzaSize[i].isSelected()) {
					pizzaCost = sizePrices[i];
					sizeOrdered = sizeNames[i];
				}
			}
			lblPizzaCost.setText(currency.format(pizzaCost));
		}
		else {
			super.actionPerformed(a);
		}
	}
	
	
	
}
