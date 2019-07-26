import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Component;
import java.math.*;

public class ApplicationP5 {

	private JFrame currencyConverterFrame;
	private JTextField textFieldCurrency;
	private JTextField textFieldRate;
	private JTextField textFieldCurrencyFrom;
	private JTextField textFieldCurrencyTo;
	private JTextField textFieldAnswer;
	public JTextArea textAreaDatabase;


	public CurrencyConverterforGUI converter = new CurrencyConverterforGUI();
	private JTextField textFieldAmount;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationP5 window = new ApplicationP5();
					window.currencyConverterFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public ApplicationP5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		//Outside Frame
		currencyConverterFrame = new JFrame();
		currencyConverterFrame.setTitle("Currency Converter");
		currencyConverterFrame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
		currencyConverterFrame.setBounds(100, 100, 1083, 794);
		currencyConverterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currencyConverterFrame.getContentPane().setLayout(null);
		
		//Currency Database Display Area and Label
		JLabel lblCurrencyDatabase = new JLabel("Currency Database");
		lblCurrencyDatabase.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCurrencyDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrencyDatabase.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCurrencyDatabase.setForeground(SystemColor.desktop);
		lblCurrencyDatabase.setBackground(UIManager.getColor("Button.background"));
		lblCurrencyDatabase.setBounds(286, 31, 400, 38);
		currencyConverterFrame.getContentPane().add(lblCurrencyDatabase);
		
		JTextArea textAreaDatabase = new JTextArea();
		textAreaDatabase.setEditable(false);
		textAreaDatabase.setBounds(286, 80, 400, 271);
		textAreaDatabase.setText("Name: CAD\tRate: 1.31359\r\nName: ISK\tRate: 123.948\r\nName: GBP\tRate: 0.80326\r\nName: EURO\tRate: 0.89498\r\nName: AFN\tRate: 80.2915\r\nName: DZD\tRate: 119.085\r\nName: BRL\tRate: 3.75271\r\nName: BTN\tRate: 68.8776\r\nName: AWG\tRate: 1.79000\r\nName: BAM\tRate: 1.75043\r\nName: USD\tRate: 1.00\r\n");
		currencyConverterFrame.getContentPane().add(textAreaDatabase);
		

		//Enter Currency Text Field
		textFieldCurrency = new JTextField();
		textFieldCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrency.setText("Enter Currency");
		textFieldCurrency.setBackground(UIManager.getColor("Button.light"));
		textFieldCurrency.setBounds(286, 374, 175, 30);
		currencyConverterFrame.getContentPane().add(textFieldCurrency);
		textFieldCurrency.setColumns(10);
		textFieldCurrency.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
		        JTextField textFieldCurrency = (JTextField)e.getComponent();
		        textFieldCurrency.setText("");
		    }
		});
		
		//Enter Rate Text Field
		textFieldRate = new JTextField();
		textFieldRate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRate.setText("Enter Rate");
		textFieldRate.setBackground(UIManager.getColor("Button.light"));
		textFieldRate.setBounds(511, 374, 175, 30);
		currencyConverterFrame.getContentPane().add(textFieldRate);
		textFieldRate.setColumns(10);
		textFieldRate.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
		        JTextField textFieldRate = (JTextField)e.getComponent();
		        textFieldRate.setText("");
		    }
		});
		
		
		//ADD Button 
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency = "";
				BigDecimal rate = BigDecimal.ZERO;
				try {
					currency = textFieldCurrency.getText(); 	
					rate = new BigDecimal(textFieldRate.getText());
					CurrencyConverterforGUI.addCurrency(currency, rate);
					textAreaDatabase.setText(converter.toString());
				}catch(Exception e2) {
					if(e2.getMessage()==null)
					JOptionPane.showMessageDialog(null, "Error: Please enter a numerical rate.");
					else {
					JOptionPane.showMessageDialog(null, e2.getMessage());	
					}
				}
			}
		});
		
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBounds(286, 453, 89, 23);
		currencyConverterFrame.getContentPane().add(btnNewButton);
		
		
		//UPDATE Button 
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency = "";
				BigDecimal rate = BigDecimal.ZERO;
				try {
					currency = textFieldCurrency.getText(); 	
					rate = new BigDecimal(textFieldRate.getText());
					CurrencyConverterforGUI.updateCurrency(currency, rate);
					textAreaDatabase.setText(converter.toString());
				}catch(Exception e2) {
					if(e2.getMessage()==null)
					JOptionPane.showMessageDialog(null, "Error: Please enter a numerical rate.");
					else {
					JOptionPane.showMessageDialog(null, e2.getMessage());	
					}
				}
			}
		});
	
		btnUpdate.setForeground(SystemColor.desktop);
		btnUpdate.setBackground(UIManager.getColor("Button.background"));
		btnUpdate.setBounds(440, 453, 89, 23);
		currencyConverterFrame.getContentPane().add(btnUpdate);
		
		//REMOVE Button 
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(SystemColor.desktop);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency = "";
				try {
					currency = textFieldCurrency.getText(); 	
					CurrencyConverterforGUI.removeCurrency(currency);
					textAreaDatabase.setText(converter.toString());
				}catch(Exception e2) {
					if(e2.getMessage()==null)
					JOptionPane.showMessageDialog(null, "Error: Please enter a numerical rate.");
					else {
					JOptionPane.showMessageDialog(null, e2.getMessage());	
					}
				}
			}
		});
		
		btnRemove.setBackground(UIManager.getColor("Button.background"));
		btnRemove.setBounds(597, 453, 89, 23);
		currencyConverterFrame.getContentPane().add(btnRemove);
		
		//Currency From Text Field
		textFieldCurrencyFrom = new JTextField();
		textFieldCurrencyFrom.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrencyFrom.setBackground(UIManager.getColor("Button.light"));
		textFieldCurrencyFrom.setText("Enter Currency to Convert From");
		textFieldCurrencyFrom.setToolTipText("");
		textFieldCurrencyFrom.setBounds(214, 528, 175, 30);
		currencyConverterFrame.getContentPane().add(textFieldCurrencyFrom);
		textFieldCurrencyFrom.setColumns(10);
		textFieldCurrencyFrom.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
		        JTextField txtCurrencyFrom = (JTextField)e.getComponent();
		        txtCurrencyFrom.setText("");
		    }
		});
		
		//Currency To Field
		textFieldCurrencyTo = new JTextField();
		textFieldCurrencyTo.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrencyTo.setBackground(UIManager.getColor("Button.light"));
		textFieldCurrencyTo.setText("Enter Currency to Convert To");
		textFieldCurrencyTo.setBounds(399, 528, 175, 30);
		currencyConverterFrame.getContentPane().add(textFieldCurrencyTo);
		textFieldCurrencyTo.setColumns(10);
		textFieldCurrencyTo.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
		        JTextField txtCurrencyTo = (JTextField)e.getComponent();
		        txtCurrencyTo.setText("");
		    }
		});
		
		//Amount Text Field
		textFieldAmount = new JTextField();
		textFieldAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAmount.setText("Enter Amount");
		textFieldAmount.setColumns(10);
		textFieldAmount.setBackground(SystemColor.controlHighlight);
		textFieldAmount.setBounds(597, 528, 175, 30);
		currencyConverterFrame.getContentPane().add(textFieldAmount);
		textFieldAmount.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
		        JTextField textFieldAmount = (JTextField)e.getComponent();
		        textFieldAmount.setText("");
		    }
		});
		
		//Convert Button
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currencyFrom = "";
				String currencyTo = "";
				try {
					currencyFrom = textFieldCurrencyFrom.getText(); 	
					currencyTo = textFieldCurrencyTo.getText();
					BigDecimal amount = new BigDecimal(textFieldAmount.getText());
					BigDecimal answer = CurrencyConverterforGUI.convert(amount, currencyFrom, currencyTo);
					textFieldAnswer.setText(answer.toString());
				}catch(Exception e2) {
					if(e2.getMessage()==null)
					JOptionPane.showMessageDialog(null, "Error: Please enter a numerical amount.");
					else {
					JOptionPane.showMessageDialog(null, e2.getMessage());	
					}
				}
			}
		});
		
		btnConvert.setForeground(SystemColor.desktop);
		btnConvert.setBackground(UIManager.getColor("Button.background"));
		btnConvert.setBounds(429, 599, 89, 23);
		currencyConverterFrame.getContentPane().add(btnConvert);
		
		//Answer Label
		JLabel lblAnswer = new JLabel("Answer:");
		lblAnswer.setForeground(SystemColor.desktop);
		lblAnswer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAnswer.setBounds(286, 661, 72, 14);
		currencyConverterFrame.getContentPane().add(lblAnswer);

		//Answer Text Field
		textFieldAnswer = new JTextField();
		textFieldAnswer.setEditable(false);
		textFieldAnswer.setBackground(UIManager.getColor("Button.light"));
		textFieldAnswer.setBounds(368, 651, 211, 36);
		currencyConverterFrame.getContentPane().add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
	}
}
