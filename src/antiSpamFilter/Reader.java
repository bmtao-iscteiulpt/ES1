package antiSpamFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	private String fileName;

	public Reader(String fileName) {
		this.fileName = fileName;
		readFile();
	}
	
	private void readFile() {
		Scanner sc = null;
		String s = null;
		
		sc = new Scanner(fileName);
		
		System.out.println(fileName + ":");
		System.out.println("\n");
		
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			System.out.println(s);
		}
		
		sc.close();
	}
	
}