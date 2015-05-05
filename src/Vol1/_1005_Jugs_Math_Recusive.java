package Vol1;

import java.util.Scanner;

public class _1005_Jugs_Math_Recusive {

	private static Scanner in; 
	private static int Ca; 
	private static int Cb; 
	private static int Wb; 
	private static int Tar; 
	private static boolean success=false; 
	
	public static void main(String[] args){
		in=new Scanner(System.in);
		while(in.hasNext()){
			Ca=in.nextInt();
			Cb=in.nextInt();
			Tar=in.nextInt();
			Wb=0;
			solve(Ca,Cb,Tar);
		}
	}

	private static void solve(int ca, int cb, int tar) {
		if(ca==tar){
			if(Wb!=0){
				System.out.println("empty B");
				Wb=0;
			}
			System.out.println("fill A");
			System.out.println("pour A B");
			Wb=ca;
			success=true;
		}
		else if(ca<tar){
			if(Wb==cb){ 			
				System.out.println("empty B");
				Wb=0;
			}
			solve(ca,cb,tar-ca);
			System.out.println("fill A");
			if(ca+Wb>cb){
				System.out.println("pour A B");
				System.out.println("empty B");
				System.out.println("pour A B");
				Wb=ca+Wb-cb;
			}
			else{
				System.out.println("pour A B");
				Wb=ca+Wb;
			}
		}
		else if(ca>tar){
			if(Wb==cb){ 			
				System.out.println("empty B");
				Wb=0;
			}
			solve(ca,cb,cb-(ca-tar));
			System.out.println("fill A");
			if(ca+Wb>cb){
				System.out.println("pour A B");
				System.out.println("empty B");
				System.out.println("pour A B");
				Wb=ca+Wb-cb;
			}
			else{
				System.out.println("pour A B");
				Wb=ca+Wb;
			}
		}
		if(success&&(tar==Tar)) System.out.println("success");
	}
}
