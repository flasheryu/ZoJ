package Vol1;

import java.util.ArrayList;
import java.util.Scanner;

public class _1002_FireNet {

	private static Scanner in;
	private static int max=0;
	private static ArrayList<Data> al=new ArrayList<Data>();
	private static int[]visit=new int[16];
	private static int lmax = 0;

	public static void main(String[] args){
		int []x=new int[4];
		int []y=new int[4];
		in = new Scanner(System.in);
		int matrix=in.nextInt();
		while (matrix>0){
			max=0;
			lmax=0;
			for(int i=0;i<4;i++){
				x[i]=0;
				y[i]=0;
			}
			al.clear();
			
			//read the input to the matrix array list: al->[index,[binary array]]
			for(int i=0;i<matrix;i++){
				String s=in.next();
				for( int j=0; j<matrix;j++){
					char c=s.charAt(j);
					if(c=='.'){
						int a=i+x[i];
						int b=j+y[j];
						Data tmp=new Data(a,b);
						al.add(tmp);
//						System.out.println("The "+n+++"th element is: "+a+", "+b+".");
					}
					else if(c=='X'){
						x[i]+=10;
						y[j]+=10;
//						System.out.println("The "+n+++"th element is: X.");
					}
				}
			}

			//find out all the possible combinations
			DFS();
			
			System.out.println(max);

			matrix=in.nextInt();
		}
	}

	//DFS  
	public static void DFS()  
	{  
		 if( lmax > max )  
		     max = lmax;  
		 for(int i=0; i<al.size(); i++)  
			 if( (visit[i]!=1) && is_valid(i) )  
		     {  
				 visit[i] = 1;  
		         lmax++;  
		         DFS();  
		         visit[i] = 0;  
		         lmax--;  
		     }  
	}

	private static boolean is_valid(int index) {
		Data tmp=al.get(index);
		for(int i=0; i<al.size(); i++) {
			if(visit[i]==1 && al.get(i).partial(tmp)) return false;
		}
		return true;
	}
}
	

class Data{
	int x;
	int y;
		
	public Data(){
		x=0;
		y=0;
	}
		
	public Data(int x1, int y1){
		x=x1;
		y=y1;
	}
		
	public void set(int x1, int y1){
		x=x1;
		y=y1;
	}
		
	public boolean partial(Data d1, Data d2){
		return (d1.x==d2.x || d1.y==d2.y)?true:false;
	}
		
	public boolean partial(Data d){
		return (x==d.x || y==d.y)?true:false;
	}
}