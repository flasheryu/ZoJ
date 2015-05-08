package Vol1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class _1003_CrashingBallon {

	private static int [] primes_100={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
			
	private static ArrayList<Integer> factor(int n){
		ArrayList<Integer> factors=new ArrayList<Integer>();
		int index=0;
		for(int i=1;i<101;i++){
			if(n%i==0) factors.add(index++,new Integer(i));
		}
//		for(Integer i: factors){ System.out.println("factor includes "+i);}
//		System.out.println("Factors Done!");
		return factors;
	}
	
	private static comb process_defactor(int n){
		ArrayList<Integer> factors=factor(n);
		comb sum=new comb();
		
		for(int i=0;i<primes_100.length;i++)
			if(n==primes_100[i]) {
				ArrayList<Integer> tmp=new ArrayList<Integer>();
//				tmp.add(1);
				tmp.add(n);
				comb primes_factor=new comb();
				primes_factor.add(tmp);
				return primes_factor;
			}
		
		for(Integer i :factors){
			if(i==1||i==n){
				if(n<101) sum=sum.plus(new comb(n));
				else continue;
			} 
			else {
//				sum.printCombs();
//				System.out.println("Next defactoring "+n/i);
				comb tmp=process_defactor(n/i).append(i.intValue());
				sum=sum.plus(tmp);
			}
		}
		return sum;
	}
	
	private static comb defactor(int n){
		comb result=new comb();
		comb pre_re=process_defactor(n);
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		int mulres=1;
		for(ArrayList<Integer> a: pre_re.aal){
			for(Integer i:a)
				mulres*=i;
			tmp=a;
//			System.out.println("n is "+n+", and mulres is "+mulres);
			if(mulres==n) result.add(tmp);
			mulres=1;
		}
		result.refactor();
//		result.printCombs();
		return result;
	}

	private static Scanner in;
	private static int big;
	private static int small;
	public static void main(String[] args){
		in = new Scanner(System.in);
		int i1;
		int i2;
		while(in.hasNext()){
			i1=in.nextInt();
			i2=in.nextInt();
			big=(i1>i2)?i1:i2;
			small=(i1>i2)?i2:i1;
			comb cs=defactor(small);
			comb cb=defactor(big);
			if(cs.aal.isEmpty()) {System.out.println(big); continue;} //if the small one is a lier, big wins!
			else if(cs.exclusive(cb)) System.out.println(big); //if big and small one can have exclusive factors, big wins!
			else System.out.println(small); //if big one can not have exclusive factors, small wins!
		}
//		ArrayList<Integer> factors=factor(100);
//		for(Integer i:factors)
//			System.out.println(i.intValue());
		
//		defactor(45123);
//		comb c1=defactor(1003);
//		System.out.println(c1.getCombs());
//		comb c2=defactor(101);
		
//		if(c2.aal.isEmpty()) System.out.println("C2 is telling lies! C1 wins!");
//		System.out.println(c1.exclusive(c2));
		
		
//		ArrayList<Integer> al1=new ArrayList<Integer>();
//		ArrayList<Integer> al2=new ArrayList<Integer>();
//		al1.add(1);al1.add(2);al1.add(3);
//		al2.add(1);al2.add(5);
//		System.out.println(new comb().al_exclusive(al1, al2));
		
	} 
}

class comb{
	ArrayList<Integer> ali=new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> aal=new ArrayList<ArrayList<Integer>>();
	
	public comb(){
		aal.clear();
		ali.clear();
	}
	
	public comb(int i){
		aal.clear();
		ali.clear();
//		ali.add(1);
		ali.add(i);
		aal.add(ali);
	}
	
	public comb(int [] a){
		aal.clear();
		ali.clear();
		for(int i=0; i<a.length;i++){
			ali.add(a[i]);
		}
		aal.add(ali);
	}

	public comb(ArrayList<Integer>[] a){
		aal.clear();
		ali.clear();
		for(int i=0; i<a.length;i++){
			aal.add(a[i]);
		}
	}
	
	public comb(ArrayList<ArrayList<Integer>> a){
		aal.clear();
		ali.clear();
		aal=a;
	}
	
	public ArrayList<ArrayList<Integer>> getCombs(){
		return aal;
	}
	
	//add an al to aal
	public void add(ArrayList<Integer> a){
		aal.add(a);
	}
	
	//add an integer i to every member of the comb
	public comb append(int i){
		for(ArrayList<Integer> al: aal)
			al.add(i);
		return new comb(aal);
	}
		
	//combine two combs to one
	public comb plus(comb a){
		for(ArrayList<Integer> al :a.aal){
			this.aal.add(al);
		}
		return new comb(aal);
	}
	
	public void printCombs(){
		for(ArrayList<Integer> al :aal){
			for(Integer i: al)
				System.out.println(i);
			System.out.println("End.");
		}
	}
	
	//sort all the elements in aal
	public void sort(){
		for(ArrayList<Integer> al :aal){
			Collections.sort(al);
		}
	}
	
	//remove duplicate elements in aal
	public void rmDup(){
		HashSet<ArrayList<Integer>> h  =   new  HashSet<ArrayList<Integer>>(aal); 
	    aal.clear(); 
	    aal.addAll(h); 
	}
	
	//remove al if it has duplicate integers inside 
	public void rmDupInt() {
		boolean flag=false;
		Integer temp;
		HashSet<ArrayList<Integer>> h  =   new  HashSet<ArrayList<Integer>>(aal); 

		ArrayList<ArrayList<Integer>> delList=new ArrayList<ArrayList<Integer>>(); 
		ArrayList<Integer> al=new ArrayList<Integer>(); 
		for(int index=0; index<aal.size();index++){
			al=aal.get(index);
			for (int i=0; i<al.size()-1; i++)
			{
				temp = al.get(i);
				for (int j=i+1; j<al.size(); j++)
				{
					if (temp.equals(al.get(j)))
					{
						flag=true;
						delList.add(al);
//						System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + temp);
						break;
					}
					if(flag){flag=false;break;}
				}
			}
		}
		h.removeAll(delList);
		aal.clear();
		aal.addAll(h);
	}
	
	public void refactor(){
		sort();
		rmDup();
		rmDupInt();
	}

	public boolean exclusive(comb a){
		boolean exclusive=false;
		for(ArrayList<Integer> al1: aal){
			for(ArrayList<Integer>al2:a.aal){
				if(al_exclusive(al1,al2)){exclusive=true;break;}
			}
			if(exclusive) break;
		}
		return exclusive;	
	}
	
	//return true if al1 is exclusive to al2
	public boolean al_exclusive(ArrayList<Integer> al1, ArrayList<Integer> al2){
		if(al1.isEmpty()||al2.isEmpty()) return false;
		boolean exclusive=true;
		for(Integer i1:al1){
			for(Integer i2:al2){
				if(i1==i2) {exclusive=false; break;}
			}
			if(exclusive==false) break;
		}
		return exclusive;
	}

}