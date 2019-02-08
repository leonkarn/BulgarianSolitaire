package lab5;
import java.util.Scanner;
import java.util.ArrayList;
public class ReadTester {

	public static void main(String[] args) {
		
		 ArrayList <Integer> list = new ArrayList<Integer>();
		Scanner in=new Scanner(System.in);
		while (true) {
		System.out.println("Enter a space seperated list of nuumbers");
		
		String s1 = in.nextLine();
		
		    
		Scanner lineScanner = new Scanner(s1);   
		  list = new ArrayList<Integer>();
		while(lineScanner.hasNextInt()) {
			
			int population = lineScanner.nextInt();

				
		
			list.add(population);
			
		}
		System.out.println(list);
	}
}
}
	