package antiSpamFilter;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

	private JFrame frame;
	
	private JPanel panelConfigure;
	private JLabel labelConfigureManual;
	private JCheckBox checkBoxConfigureManual;
	private JLabel labelConfigureAuto;
	private JCheckBox checkBoxConfigureAuto;
	
	private JPanel panelPath;
	private JLabel labelPathRules;
	private JCheckBox checkBoxPathRules;
	private JLabel labelPathHam;
	private JCheckBox checkBoxPathHam;
	private JLabel labelPathSpam;
	private JCheckBox checkBoxPathSpam;
	
	public GUI() {
		addContentInterface();
		startInterface();
	}
	
	private void addContentInterface() {
		frame = new JFrame("Anti-Spam Controler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3, 0));
		
		panelConfigure = new JPanel();
		panelConfigure.setLayout(new FlowLayout());
		frame.add(panelConfigure);
		
		labelConfigureManual = new JLabel("Configuração Manual");
		panelConfigure.add(labelConfigureManual);
		
		checkBoxConfigureManual = new JCheckBox(); 
		panelConfigure.add(checkBoxConfigureManual);
		
		labelConfigureAuto = new JLabel("Configuração Automática");
		panelConfigure.add(labelConfigureAuto);
		
		checkBoxConfigureAuto = new JCheckBox(); 
		panelConfigure.add(checkBoxConfigureAuto);
		
		panelPath = new JPanel();
		panelPath.setLayout(new FlowLayout());
		frame.add(panelPath);
		
		labelPathRules = new JLabel("Path Rules.cf");
		panelPath.add(labelPathRules);
		
		checkBoxPathRules = new JCheckBox(); 
		panelPath.add(checkBoxPathRules);
		
		labelPathHam = new JLabel("Path Ham.log");
		panelPath.add(labelPathHam);
		
		checkBoxPathHam = new JCheckBox(); 
		panelPath.add(checkBoxPathHam);
		
		labelPathSpam = new JLabel("Path Spam.log");
		panelPath.add(labelPathSpam);
		
		checkBoxPathSpam = new JCheckBox(); 
		panelPath.add(checkBoxPathSpam);
	}
	
	private void startInterface() {
		frame.pack();
		frame.setVisible(true);
	}
	
}
