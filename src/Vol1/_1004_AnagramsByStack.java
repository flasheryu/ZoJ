package Vol1;
//麻痹的println输出是带换行符的，次奥！！
import java.util.ArrayList;
import java.util.Scanner;

public class _1004_AnagramsByStack {

	private static final Scanner in=new Scanner(System.in);
	private static String src;
	private static String tar;
//	private static int depth=0;
	private static int stacklength=0;
	
	private static ArrayList<String> stack=new ArrayList<String>();
	private static int top=0;
	private static ArrayList<String> result=new ArrayList<String>();
	
	public static void main(String[] args){
		while(in.hasNext()){
			stack.clear();
//			depth=0;
			top=0;
			src=in.nextLine();
			tar=in.nextLine();
//			System.out.println("src is "+ src);
//			System.out.println("tar is "+ tar);
			if(src.length()!=tar.length()) {System.out.print("["+"\n"+"]"+"\n"); continue;} 			//if the length is different, return "[]"
			else{ 
				stacklength=src.length()*2;
				System.out.print("["+"\n");
				DFS(0,0,0);
				System.out.print("]"+"\n");
			}
		}
	}
	
	private static void DFS(int src_i, int res_i, int depth){
		//when to return :   
		if(depth>=stacklength){
//			System.out.println("The depth is:"+depth+"! ");
//			System.out.println("The result is:");
			for(int i=0;i<depth;i++){
				System.out.print(result.get(i)+" ");
			}
			System.out.print("\n");
			return;
		}
		
		//push[i] when src is not exhausted
		if(src_i<src.length()){
			stack.add(top++,src.substring(src_i, src_i+1));
			result.add(depth, "i");
			DFS(src_i+1,res_i,depth+1);
			stack.remove(--top);
			result.remove(depth);
		}
		
		//pop[o] when stack is not empty and the top letter==the beginning letter in target
		if((!stack.isEmpty())&&(stack.get(top-1).charAt(0)==tar.charAt(res_i))){
			String tmp=stack.get(top-1);
			stack.remove(--top);
			result.add(depth, "o");
			DFS(src_i,res_i+1,depth+1);
			stack.add(top++,tmp);
			result.remove(depth);
		}
	}
}

