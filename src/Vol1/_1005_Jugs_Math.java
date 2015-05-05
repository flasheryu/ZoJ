package Vol1;

import java.util.Scanner;

public class _1005_Jugs_Math {

	private static Scanner in;
	private static int sm;	//Capacity of small jug
	private static int wsm;	//Water in small jug
	private static int big;	//Capacity of big jug
	private static int wbig;//Water in big jug
	private static int tar;	//Target water
	
	public static void main(String[] args){
		in=new Scanner(System.in);
		while(in.hasNext()){
			sm=in.nextInt();
			big=in.nextInt();
			tar=in.nextInt();
			wbig=0;
			wsm=0;
			while(wbig!=tar){
				if(wbig==big){
					System.out.println("empty B");
					wbig=0;
				}
				if(wsm==0){
					System.out.println("fill A");
					wsm=sm;					
				}
				if((wbig+wsm)<=big)
				{
					System.out.println("pour A B");
					wbig+=wsm;
					wsm=0;
				}
				else{
					System.out.println("pour A B");
					wsm=wbig+wsm-big;
					wbig=big;
				}
			}
			System.out.println("success");
		}
		
	}
}
