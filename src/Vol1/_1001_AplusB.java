package Vol1;

import java.util.Scanner;

public class _1001_AplusB {

	private static Scanner in;

	public static void main(String[] args){
		in = new Scanner(System.in);
		while (in.hasNextInt()){
			int a=in.nextInt();
			int b=in.nextInt();
			int c=a+b;
			System.out.println(c);
		}
	}
}
