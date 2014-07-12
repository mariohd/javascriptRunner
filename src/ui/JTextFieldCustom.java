package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JTextField;

public class JTextFieldCustom extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String placeholder;

	public JTextFieldCustom(String placeholder) {
		super(10);
		this.placeholder = placeholder;
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);

		if (getText().isEmpty()
				&& !(FocusManager.getCurrentKeyboardFocusManager()
						.getFocusOwner() == this)) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Color.lightGray);
			g2.setFont(getFont().deriveFont(Font.BOLD));
			g2.drawString(getPlaceholder(), 5, 20);
			g2.dispose();
		}
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getPlaceholder() {
		return placeholder;
	}

}
