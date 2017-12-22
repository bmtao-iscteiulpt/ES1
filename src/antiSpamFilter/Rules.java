package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rules {

	private ArrayList<Rules> rule = new ArrayList<Rules>();
	private File fileHam = new File("ham.log");
	//private String r;
	private String [][] m; 
	
	Rules(){
		procurarRules();
	}
	
	//ERRADO!!!
		private void procurarRules(){
			Scanner sc;
			try {
				sc = new Scanner (fileHam);
				while(sc.hasNextLine()){
					
				}
					sc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	
	
	
	
	
//	//ERRADO!!!
//	private void procurarRules(){
//		String s = null;
//		Scanner sc = null;
//		int count = 0;
//		int l = 365;
//		int c = 9;
//		String [] vetor = null;
//		
//		try {
//			sc = new Scanner(fileHam, "UTF-8");
//			
//			while(sc.hasNextLine()) {
//				String []a = s.split("/n");
//			
//				for (int i=0; i<l; i++){
//		            for (int j=0; j<c; j++){	
//		            	s = sc.nextLine();
//		            	vetor = s.split(" ");
//		            }
//				}
//				s = sc.nextLine();
//				System.out.println(vetor);
//			
//		}
//			sc.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	
}
