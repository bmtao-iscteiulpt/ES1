package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.table.TableModel;

	/*
	 * Classe para calcular e contabilizar n�mero de Falsos Positivos e Falsos Negativos - Configura��o Autom�tica
	 */
public class AutomaticCalculator {

	private File fileHam = new File("ham.log");
	private File fileSpam = new File("spam.log");
	private File fileRules = new File("rules.cf");
	private Map<String, Double> h = new HashMap<String, Double>();
	
	/*
	 * M�todo para calcular Falsos Positivos
	 */
	public double evaluateFP(double[] x){
		fillHashMap(x);
		
		int FP = 0;
		
		Scanner sc = null;
		String s = null;
			
		try {
			sc = new Scanner(fileHam, "UTF-8");
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				String []s1 = s.split("\\s");
				
				double a = 0;
					
				for (int i = 1; i != s1.length; i++) {
					Double value = h.get(s1[i]);
					if(value == null) continue;
					a += value;
				}
				if(fileHam.getName().equals("ham.log") && a>5) {
					FP++;
				}
			}
			if(fileHam.getName().equals("ham.log")) {
				System.out.println("FP: " + FP);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro - Leitura de ficheiro inv�lida!");
		}	
		return FP;
	}
	
	/*
	 * M�todos para calcular Falsos Negativos
	 */
	public double evaluateFN(double[] x){
		fillHashMap(x);
		
		int FN = 0;
		
		Scanner sc = null;
		String s = null;
			
		try {
			sc = new Scanner(fileSpam, "UTF-8");
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				String []s1 = s.split("\\s");
				
				double a = 0;
					
				for (int i = 1; i != s1.length; i++) {
					Double value = h.get(s1[i]);
					if(value == null) continue;
					a += value;
				}
				if(fileSpam.getName().equals("spam.log") && a<5) {
					FN++;
				}
			}
			if(fileSpam.getName().equals("spam.log")) {
				System.out.println("FN: " + FN);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro - Leitura de ficheiro inv�lida!");
		}	
		return FN;
	}

	/*
	 * M�todo para preencher o Map<String, Double> - Map<Regra, Peso>
	 * Peso atribu�do com valores obtidos automaticamente
	 */
	public void fillHashMap(double[] x) {
		Scanner sc = null;
		String s = null;
			
		try {
			sc = new Scanner(fileRules, "UTF-8");
			
			int i = 0;
			
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				h.put(s, x[i]);
				i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro - Preenchimento com dados inv�lida!");
		}
	}

}