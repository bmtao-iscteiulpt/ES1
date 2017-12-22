package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Email {

	public static File fileHam = new File("ham.log");
	public static File fileSpam = new File("spam.log");
	
	private File fileName;
	private Map<String, Double> h;
	//private ArrayList<String> mailName;
	//private Rules rules;
	
	public int FN=0;
	public int FP=0;
	
	public Email(File fileName, Map<String, Double> h) {
		this.fileName = fileName;
		this.h = h;
		//this.mailName = mailName;
		//this.rules = rules;
	}
	
	

	/**
	 * funciona para email e rules, temos de adaptar
	 */

	public void evaluate() {
		Scanner sc = null;
		String s = null;
			
		try {
			sc = new Scanner(fileName, "UTF-8");
			//System.out.println(fileName);
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				String []s1 = s.split("\\s");
				//System.out.println(s);
				//System.out.println("email: " + s1[0]);
				
				double a = 0;
					
				for (int i = 1; i != s1.length; i++) {
					//System.out.println(s1[i]);
					Double value = h.get(s1[i]);
					//System.out.println(value);
					if(value == null) continue;
					a += value;
				}
				if(fileName.getName().equals("ham.log") && a>5) {
					FP++;
				}
				if(fileName.getName().equals("spam.log") && a<5) {
					FN++;
				}
				//System.out.println("peso final: " + a);
			}
			
			if(fileName.getName().equals("ham.log")) {
				System.out.println("FP: " + FP);
			}
			if(fileName.getName().equals("spam.log")) {
				System.out.println("FN: " + FN);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public int getFN() {
		return FN;
	}
	
	public int getFP() {
		return FP;
	}
	
//	public static void main(String[] args) {
//		//ArrayList<String> mailName = new ArrayList<String>();
//		Map<String, Double> h = new HashMap<String, Double>();
//		
//		Email eHam = new Email(fileHam, h);
//		eHam.evaluate();
//		
//		Email eSpam = new Email(fileSpam, h);
//		eSpam.evaluate();
//		
//	}
	
}
