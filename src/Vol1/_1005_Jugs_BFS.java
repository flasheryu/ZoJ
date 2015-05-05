package Vol1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _1005_Jugs_BFS {
	
	private static boolean success;
	private static int ca;
	private static int cb;
	private static int tar;
	private static Scanner in;
	private static final String fa="fill A";	//the next operation can only be "pour A B" (pa)
	private static final String fb="fill B";	//the next operation can only be "pour B A" (pb)
	private static final String ea="empty A";	//the next operation can be "fill A"(fa),"fill B"(fb) and "pour B A" (pb)
	private static final String eb="empty B";	//the next operation can be "fill A"(fa),"fill B"(fb) and "pour A B" (pa)
	private static final String pa="pour A B";	//the next operation can be "fill A"(fa) and "empty B"(eb)
	private static final String pb="pour B A";	//the next operation can be "fill B"(fb) and "empty A"(ea)
	
	private static ArrayList<String> spath=new ArrayList<String>();
	
	public static void main(String[] args){
		in = new Scanner(System.in);
		while(in.hasNext()){
			spath.clear();
			success=false;
			ca=in.nextInt();
			cb=in.nextInt();
			tar=in.nextInt();
			bfs();
		}
	}

	private static void bfs(){
		Queue<qnode> q=new LinkedList<qnode>();
		q.add(new qnode(fa));
		while((!q.isEmpty()) && (!success)){
//			System.out.println(q.size());
//			for(qnode qn:q){
//				qn.printself();
//			}
			qnode tmp=q.remove();
//			tmp.printself();
			switch(tmp.getlastop()){
				case fa:{
					tmp.setwa(ca);
//					System.out.println("fill A");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,pa));
					break;
				}
				case fb:{
					tmp.setwb(cb);
//					System.out.println("fill B");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,pb));
					break;
				}
				case ea:{
					tmp.setwa(0);
//					System.out.println("empty A");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,fa));
					q.add(new qnode(tmp,fb));
					q.add(new qnode(tmp,pb));
					break;
				}
				case eb:{
					tmp.setwb(0);
//					System.out.println("empty B");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,fa));
					q.add(new qnode(tmp,fb));
					q.add(new qnode(tmp,pa));
					break;
				}
				case pa:{
					if(tmp.getwa()+tmp.getwb()>cb){
						tmp.setwa(tmp.getwa()+tmp.getwb()-cb);
						tmp.setwb(cb);
					}
					else{
						tmp.setwb(tmp.getwa()+tmp.getwb());
						tmp.setwa(0);
					}
//					System.out.println("pour A B");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,fa));
					q.add(new qnode(tmp,eb));
					break;
				}
				case pb:{
					if(tmp.getwa()+tmp.getwb()>ca){
						tmp.setwb(tmp.getwa()+tmp.getwb()-ca);
						tmp.setwa(ca);
					}
					else{
						tmp.setwa(tmp.getwa()+tmp.getwb());
						tmp.setwb(0);
					}
//					System.out.println("pour B A");
					if((tmp.getwa()==tar)||(tmp.getwb()==tar)) {success=true;spath=tmp.getops();break;}
					q.add(new qnode(tmp,fb));
					q.add(new qnode(tmp,ea));
					break;
				}
			}
		}
		//print the result
		for(String s:spath){
			switch(s){
				case fa:{
					System.out.println("fill A");
					break;
				}
				case fb:{
					System.out.println("fill B");
					break;
				}
				case ea:{
					System.out.println("empty A");
					break;
				}
				case eb:{
					System.out.println("empty B");
					break;
				}
				case pa:{
					System.out.println("pour A B");
					break;
				}
				case pb:{
					System.out.println("pour B A");
					break;
				}
			}
		}
		System.out.println("success");
	}
}

class qnode{
	private int wa;	//water in a
	private int wb;	//water in b
	private ArrayList<String> ops=new ArrayList<String>();//operations list in this node 
	private String lastop;	//the last operation
	
	qnode(String s){
		wa=0;
		wb=0;
		ops.add(s);
		lastop=s;
	}
	
	qnode(qnode qn, String s){
		wa=qn.wa;
		wb=qn.wb;
		ops=new ArrayList<String>(qn.ops); //a new list instead of using qn's
		ops.add(s);
		lastop=s;
	}
	
	qnode(int a, int b, ArrayList<String> al, String s){
		wa=a;
		wb=b;
		ops=al;
		ops.add(s);
		lastop=s;
	}
	
	public void printself(){
		System.out.println("wa:"+wa+",wb:"+wb);
		System.out.println("lastop:"+lastop);
		for(String s : ops) System.out.print("ops:"+s+".");
		System.out.println();
	}
	
	public void setops(ArrayList<String> al){
		ops=al;
	}
	
	public ArrayList<String> getops(){
		return ops;
	}
	
	public int getwa(){
		return wa;
	}
	
	public int getwb(){
		return wb;
	}
	
	public String getlastop(){
		return lastop;
	}
	
	public void setwa(int i){
		wa=i;
	}
	
	public void setwb(int i){
		wb=i;
	}
}
