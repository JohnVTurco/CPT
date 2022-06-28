import javax.swing.*;

public class CaesarsCheckoutTextFieldsTurco extends CaesarsCheckoutLabelsTurco{
	protected JTextField [] txtInfo;
	
	//sub class constructor, calls base class constructor and creates text fields
	public CaesarsCheckoutTextFieldsTurco() {
		super();
		
		txtInfo = new JTextField[5];
		txtInfo[0] = textFieldCreator(100, 165, 200, 25);
		txtInfo[1] = textFieldCreator(100, 205, 200, 25);
		txtInfo[2] = textFieldCreator(185, 245, 250, 25);
		txtInfo[3] = textFieldCreator(390, 285, 100, 25);
		txtInfo[4] = textFieldCreator(15, 370, 470, 30);
	}
	
	//Assists in making text fields
	private JTextField textFieldCreator(int x, int y, int xSize, int ySize) {
		JTextField txt = new JTextField();
		txt.setBounds(x, y, xSize, ySize);
		panel.add(txt);
		return txt;
	}
}
