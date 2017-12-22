package antiSpamFilter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

	/*
	 * Classe da Interface Gráfica do projecto
	 */
@SuppressWarnings("serial")
public class GUI extends JFrame{
	
	private JCheckBox checkBoxConfAuto;
	private JCheckBox checkBoxConfManual;
	private Object[] tableLines;
	private String separator = " ";
	
	ArrayList<Object> a = new ArrayList<Object>();
	Map<String, Double> h = new HashMap<String, Double>();
	
	private JFrame frame;
	private JTable table;
	
	private JTextField textFieldPathRules;
	private JTextField textFieldPathHam;
	private JTextField textFieldPathSpam;
	private JTextField textFP;
	private JTextField textFN;

	private File fileRules = new File("rules.cf");
	private File fileHam = new File("ham.log");
	private File fileSpam = new File("spam.log");
	
	/*
	 * Construtor da classe GUI - chama de imediato os métodos:
	 * 	addContentInterface() - método que constroi a interface gráfica
	 * 	startInterface() - método que inicia a interface gráfica 
	 */
	public GUI() {
		addContentInterface();
		startInterface();
	}

	/*
	 * Método responsável pela construção da Interface Gráfica
	 */
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
		checkBoxConfAuto.setBounds(346, 12, 29, 29);
		frame.getContentPane().add(checkBoxConfAuto);
		
		checkBoxConfManual = new JCheckBox("");
		checkBoxConfManual.setBounds(590, 12, 29, 29);
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
		
		
		//textFieldPathRules = new JTextField("");
		//Para facilitar teste de código
		textFieldPathRules = new JTextField("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf");
		textFieldPathRules.setBounds(200, 97, 200, 29);
		frame.getContentPane().add(textFieldPathRules);
		
		//textFieldPathHam = new JTextField("");
		//Para facilitar teste de código
		textFieldPathHam = new JTextField("/Users/Calberto/git/ES1-2017-IC2--85/ham.log");
		textFieldPathHam.setBounds(200, 220, 200, 29);
		frame.getContentPane().add(textFieldPathHam);
		
		//textFieldPathSpam = new JTextField("");
		//Para facilitar teste de código
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
					dataLine[1] = 0.0;
				}
				tableModel.addRow(dataLine);
			}
			buffer.close();
		}catch (IOException ex) {
			System.out.println("Erro - Falha no preenchimento da tabela" );
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

		fillHashMap();
		
		JButton buttonGerarConfManual = new JButton("GERAR CONFIGURAÇÃO");
		buttonGerarConfManual.setBounds(508, 330, 221, 29);
		frame.getContentPane().add(buttonGerarConfManual);
		
		buttonGerarConfManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoxConfAuto.isSelected()) {
					@SuppressWarnings("unused")
					AntiSpamFilterAutomaticConfiguration a = new AntiSpamFilterAutomaticConfiguration();
				} else if(checkBoxConfManual.isSelected()) {
				
				String a = textFieldPathRules.getText();
				String b = textFieldPathHam.getText();
				String c = textFieldPathSpam.getText();
				
				//Tem de ser adaptado a cada computador
				if(a.equals("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf") && b.equals("/Users/Calberto/git/ES1-2017-IC2--85/ham.log")){
					Email eHam = new Email(fileHam, h);
					eHam.evaluate();
					
					textFP.setText(String.valueOf(eHam.getFP()));
				}
				//Tem de ser adaptado a cada computador
				if(a.equals("/Users/Calberto/git/ES1-2017-IC2--85/rules.cf") && c.equals("/Users/Calberto/git/ES1-2017-IC2--85/spam.log")){
					Email eSpam = new Email(fileSpam, h);
					eSpam.evaluate();
					
					textFN.setText(String.valueOf(eSpam.getFN()));
				} }
			}
		});
		
		JButton buttonGravarConfManual = new JButton("GRAVAR PESOS");
		buttonGravarConfManual.setBounds(568, 375, 130, 29);
		frame.getContentPane().add(buttonGravarConfManual);

		buttonGravarConfManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int r = 0; r != table.getRowCount(); r++){
					String s = (String) table.getValueAt(r, 0);
					Object s1 = table.getValueAt(r, 1);
					
					System.out.println(s);
					
					if(s1 instanceof String) {
						String s2 = (String) String.valueOf(table.getValueAt(r, 1));
						
						System.out.println(((String) s2).toString());
						h.put(s, Double.parseDouble(s2));
					} else if (s1 instanceof Double) {
						System.out.println(((Double) s1).doubleValue());
						h.put(s, ((Double) s1).doubleValue());
					}
				}
				
				String nomeFicheiro = fileRules.getName();
				String nomeFicheiroSave = nomeFicheiro;
				tableToFile(nomeFicheiroSave);
			}	
		});
		
	}
	
	/*
	 * Método que escreve no ficheiro "rules.cf" peso atuais com quais a Configuração Manual
	 * foi efectuada no momento.
	 */
	public void tableToFile(String nomeFicheiro) {
		try {
			File ficheiro = new File(nomeFicheiro);
			TableModel model = table.getModel();
			FileWriter fw = new FileWriter(ficheiro);
			
			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					fw.write(model.getValueAt(i, j).toString()+separator);
				}
				fw.write("\n");
			}
			
			fw.close();
		} catch (IOException ex) {
			System.out.println("Erro - Problema na escrita do ficheiro: " + nomeFicheiro);
		}
	}
	
	/*
	 * Método para preencher o Map<String, Double> - Map<Regra, Peso>
	 * Peso atribuído com valores obtidos manualmente
	 */
	public void fillHashMap() {
		for (int i = 0; i < table.getRowCount(); i++) {
			h.put((String)(table.getValueAt(i,0)), (Double) table.getValueAt(i, 1));
		}
	}
	
	/*
	 * Método que inicia a Interface Gráfica
	 */
	private void startInterface() {
		frame.setVisible(true);
	}
	
	/*
	 * Método auxiliar para lançar a classe GUI
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI g = new GUI();
	}
	
}