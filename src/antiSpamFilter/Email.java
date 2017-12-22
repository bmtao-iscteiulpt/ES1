package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

	/*
	 * Classe de apoio à Configuração Manual
	 * Classe Email que devolve uma lista de todos os mails (seja ham ou spam) e suas respectivas
	 * regras atribuídas
	 */
public class Email {

	public static File fileHam = new File("ham.log");
	public static File fileSpam = new File("spam.log");
	
	private File fileName;
	private Map<String, Double> h;
	
	public int FN=0;
	public int FP=0;
	
	/*
	 * Contrutor da classe Email
	 */
	public Email(File fileName, Map<String, Double> h) {
		this.fileName = fileName;
		this.h = h;
	}

	/*
	 * Método para calcular e contabilizar Falsos Positivos e Falsos Negativos - Configuração Manual
	 */
	public void evaluate() {
		Scanner sc = null;
		String s = null;
			
		try {
			sc = new Scanner(fileName, "UTF-8");

			while(sc.hasNextLine()) {
				s = sc.nextLine();
				String []s1 = s.split("\\s");
							
				double a = 0;
					
				for (int i = 1; i != s1.length; i++) {
					Double value = h.get(s1[i]);
					if(value == null) continue;
					a += value;
				}
				if(fileName.getName().equals("ham.log") && a>5) {
					FP++;
				}
				if(fileName.getName().equals("spam.log") && a<5) {
					FN++;
				}
			}
			
			if(fileName.getName().equals("ham.log")) {
				System.out.println("FP: " + FP);
			}
			if(fileName.getName().equals("spam.log")) {
				System.out.println("FN: " + FN);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro - Leitura de ficheiro inválida!");
		}	
	}
	
	/*
	 * Método que devolve o número de Falsos Positivos
	 */
	public int getFP() {
		return FP;
	}
	
	/*
	 * Método que devolve o número de Falsos Negativos
	 */
	public int getFN() {
		return FN;
	}
	
}