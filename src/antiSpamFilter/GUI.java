package antiSpamFilter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;

public class GUI extends JFrame{
	
	private JCheckBox checkBoxConfAuto;
	private JCheckBox checkBoxConfManual;
	private Object[] tableLines;
	private Object[] test;
	
	ArrayList<Object> a = new ArrayList<Object>();
	Map<String, Double> h = new HashMap<String, Double>();
	
	private JFrame frame;
	private JCheckBox checkBoxPathRules;
	private JCheckBox checkBoxPathHam;
	private JCheckBox checkBoxPathSpam;
	private JTextField textFPConfAuto;
	private JTextField textFNConfAuto;
	private JTextField textFPConfManual;
	private JTextField textFNConfManual;
	
	private JTable table;
	
	private JTextField textFieldPathRules;
	private JTextField textFieldPathHam;
	private JTextField textFieldPathSpam;
	private JTextField textFP;
	private JTextField textFN;

	
	private File fileRules = new File("rules.cf");
	private File fileHam = new File("ham.log");
	private File fileSpam = new File("spam.log");
	
	@SuppressWarnings("unused")
	private Reader r = null;
	
	public GUI() {
		addContentInterface();
		startInterface();
		checkBox();
	}

	private void addContentInterface() {
		frame = new JFrame();
		frame.setSize(850, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel labelConfAuto = new JLabel("Configuração Automática");
		labelConfAuto.setBounds(165, 16, 184, 20);
		frame.getContentPane().add(labelConfAuto);
		
		JLabel labelConfManual = new JLabel("Configuração Manual");
		labelConfManual.setBounds(439, 16, 184, 20);
		frame.getContentPane().add(labelConfManual);
		
		checkBoxConfAuto = new JCheckBox("");
		checkBoxConfAuto.setBounds(590, 12, 29, 29);
		frame.getContentPane().add(checkBoxConfAuto);
		
		checkBoxConfManual = new JCheckBox("");
		checkBoxConfManual.setBounds(346, 12, 29, 29);
		frame.getContentPane().add(checkBoxConfManual);
		
		JLabel labelPathRules = new JLabel("Path rules.cf");
		labelPathRules.setBounds(110, 100, 98, 20);
		frame.getContentPane().add(labelPathRules);
		
		JLabel labelPathHam = new JLabel("Path ham.log");
		labelPathHam.setBounds(110, 223, 98, 20);
		frame.getContentPane().add(labelPathHam);
		
		JLabel labelPathSpam = new JLabel("Path spam.log");
		labelPathSpam.setBounds(110, 346, 98, 20);
		frame.getContentPane().add(labelPathSpam);
		
		textFieldPathRules = new JTextField("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf");
		textFieldPathRules.setBounds(200, 97, 200, 29);
		frame.getContentPane().add(textFieldPathRules);
		
		textFieldPathHam = new JTextField("/Users/Calberto/git/ES1-2017-IC2--85/ham.log");
		textFieldPathHam.setBounds(200, 220, 200, 29);
		frame.getContentPane().add(textFieldPathHam);
		
		textFieldPathSpam = new JTextField("/Users/Calberto/git/ES1-2017-IC2--85/spam.log");
		textFieldPathSpam.setBounds(200, 342, 200, 29);
		frame.getContentPane().add(textFieldPathSpam);

		//TABLE
		String [] colunas = {"Rules", "Weight [-5, 5]"};
		DefaultTableModel tableModel = new DefaultTableModel(0,2);
		
		tableModel.setColumnIdentifiers(colunas);
		table = new JTable(tableModel);
		
		try{
			File file = new File("rules.cf");
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			tableLines = buffer.lines().toArray();
			
			for(int i=0; i<tableLines.length; i++){
				String line = tableLines[i].toString().trim();
				String[] startLine = line.split(" ");
				Object[] dataLine = new Object[2];
				dataLine[0] = startLine[0];
				if(startLine.length > 1){
					dataLine[1] = new Double(startLine[1]);
				}else{
					Double max = 5.0;
					Double min = -5.0;
					Random random = new Random(); 
					double value1 = min + (max - min) * random.nextDouble();
					dataLine[1] = value1;
					//dataLine[1] = 0.0;
				}
				tableModel.addRow(dataLine);
			}
			buffer.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}

		table.setPreferredScrollableViewportSize(new Dimension(500,500));
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model =(TableModel) e.getSource();
				Object data = model.getValueAt(row, column);
				System.out.println(data.toString());
				
			}
		});
		
		table.addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent e) {
				saveToHashMap(table.getModel(), h);
			}

			@Override
			public void componentAdded(ContainerEvent e) {
			
			}
		});
		
		JScrollPane scrollPaneTableConfManual = new JScrollPane(table);
		scrollPaneTableConfManual.setOpaque(true);
		scrollPaneTableConfManual.setBounds(476, 56, 313, 200);
		frame.getContentPane().add(scrollPaneTableConfManual);
		
		JLabel labelFPConfManual = new JLabel("FP");
		labelFPConfManual.setBounds(527, 265, 69, 20);
		frame.getContentPane().add(labelFPConfManual);
		
		JLabel labelFNConfManual = new JLabel("FN");
		labelFNConfManual.setBounds(527, 294, 69, 20);
		frame.getContentPane().add(labelFNConfManual);
		
		textFP = new JTextField();
		textFP.setColumns(10);
		textFP.setBounds(560, 262, 146, 26);
		frame.getContentPane().add(textFP);

		textFN = new JTextField();
		textFN.setColumns(10);
		textFN.setBounds(560, 291, 146, 26);
		frame.getContentPane().add(textFN);

		//BOTÃO
		JButton buttonGerarConfManual = new JButton("GERAR CONFIGURAÇÃO");
		buttonGerarConfManual.setBounds(508, 330, 221, 29);
		frame.getContentPane().add(buttonGerarConfManual);
		
		buttonGerarConfManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillHashMap();
				String a = textFieldPathRules.getText();
				String b = textFieldPathHam.getText();
				String c = textFieldPathSpam.getText();
				if(a.equals("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf") && b.equals("/Users/Calberto/git/ES1-2017-IC2--85/ham.log")){
					Email eHam = new Email(fileHam, h);
					eHam.evaluate();
					
					textFP.setText(String.valueOf(eHam.getFP()));
				}
				if(a.equals("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf") && c.equals("/Users/Calberto/git/ES1-2017-IC2--85/spam.log")){
					Email eSpam = new Email(fileSpam, h);
					eSpam.evaluate();
					
					textFN.setText(String.valueOf(eSpam.getFN()));
				}
				if(a.equals("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf") &&
						b.equals("/Users/Calberto/git/ES1-2017-IC2--85/ham.log") &&
						c.equals("/Users/Calberto/git/ES1-2017-IC2--85/spam.log")){
					Email eHam = new Email(fileHam, h);
					eHam.evaluate();
					System.out.println(" ");
					Email eSpam = new Email(fileSpam, h);
					eSpam.evaluate();
					
					textFP.setText(String.valueOf(eHam.getFP()));
					//textFN.setText(String.valueOf(eSpam.getFN()));
				}
		
			}
		});
		
		JButton buttonGravarConfManual = new JButton("GRAVAR");
		buttonGravarConfManual.setBounds(568, 375, 115, 29);
		frame.getContentPane().add(buttonGravarConfManual);

		buttonGravarConfManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i=0; i < table.getRowCount(); i++){		//NAO FUNCIONA
					Object d = tableModel.getValueAt(i, 1);
					if (d instanceof Double) {
						System.out.println(((Double) d).doubleValue());
					}
				}
					
			}	
		});
		
	}
	
	public void fillHashMap() {

		System.out.println(table.getRowCount());
		
		for (int i = 0; i < table.getRowCount(); i++) {
			h.put((String)(table.getValueAt(i,0)), (Double) table.getValueAt(i, 1));
		}
		
		Set keys = h.keySet();
		for (java.util.Iterator i = keys.iterator(); i.hasNext(); ) {
		       String key = (String) i.next();
		       Double value = h.get(key);
		       //System.out.println(key + " = " + value);
		}
	}

	public void readRulesFromHam(){
		try{
			File file = new File("ham.log");
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			test = buffer.lines().toArray();
			
			for(int i=0; i<test.length; i++){
				String line = test[i].toString().trim();
				String[] startLine = line.split(" ");
				Object[] dataLine = new Object[2];
				dataLine[i] = startLine[i];
				if(startLine.length > 1){
					Double max = 5.0;
					Double min = -5.0;
					Random random = new Random(); 
					double value1 = min + (max - min) *random.nextDouble();
					dataLine[1] = value1;
				}
				//System.out.println(dataLine[i]);
				//tableModel.addRow(dataLine);
			}
			buffer.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void startInterface() {
		frame.setVisible(true);
	}
	
	//CRIAR PESOS ALEATÓRIOS E FAZER A SOMA DOS PESOS
	private void checkBox() {
		int []v = new int[5];
		double value = 0.0;
		for(int i=0; i<v.length; i++) {
			Double max = 5.0;
			Double min = -5.0;
			Random random = new Random(); 
			double value1 = min + (max - min) *random.nextDouble();
			//System.out.println("Valor "+ i + ": " + value1);
			value = value + value1;
		}
		//System.out.println("Resultado final:" + value);
	}

	//N�o funciona bem
	public void saveToHashMap(TableModel table, Map<String, Double> h) {
		h.clear();
		System.out.println(table);
		
		for(int r = 0; r != table.getRowCount(); r++) {
			String s = (String) table.getValueAt(r,  0);
			String b = (String) String.valueOf(table.getValueAt(r, 1));
			
			h.put(s, Double.parseDouble(b));
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI g = new GUI();
		Rules r = new Rules();
	}
	
}
