package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	private File fileName;

	public Reader(File fileName) {
		this.fileName = fileName;
		readFile();
	}
	
	private void readFile() {
		Scanner sc = null;
		String s = null;
		
		try {
			sc = new Scanner(fileName, "UTF-8");
			
			System.out.println(fileName.getName() + ":");
			System.out.println("\n");
			
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				System.out.println(s);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}