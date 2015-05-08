package Vol1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _1008_GnomeTetravex_BFS_TLE {

	private static Scanner in;
	private static int edge;
	private static ArrayList<Box1> boxes=new ArrayList<Box1>();
//	private static Box[][] map=new Box[5][5];
	private static Box1 box=new Box1();
	
	private static int index=0;
	private static boolean success=false;
	private static Queue<Boxnode1> q =new LinkedList<Boxnode1>();
	
	public static void main(String[] args){
		in=new Scanner(System.in);
		edge=in.nextInt();
		while(edge!=0){
			index++;
			for(int i=0;i<edge*edge;i++){
				box.setId(i);
				box.setTop(in.nextInt());
				box.setRight(in.nextInt());
				box.setBottom(in.nextInt());
				box.setLeft(in.nextInt());
				boxes.add(new Box1(box));
//				box.printself();
			}
			
			for(int i=0; i<boxes.size();i++){
				q.add(new Boxnode1(boxes.get(i)));
				while((!q.isEmpty())){
					Boxnode1 bn=q.remove();
					bn.printself();
					int size=bn.getboxes().size();
					Boxnode1 tail=new Boxnode1();
					for(Box1 b:boxes){
//						b.printself();
						if(bn.contain(b)) {
//							System.out.println("Box contained:");
//							b.printself();
							continue;
						}
						if( (bn.getboxes().size()>0) && (bn.getboxes().size()<edge) ){	//the first line: check bn.getlastbox().leftof() only
							if(bn.getlastbox().leftof(b)){
								tail=new Boxnode1(bn,b);
								q.add(tail);
							}
						}
						else if( (bn.getboxes().size()%edge)==0 ){// end of each line: check bn.topof()
							if(bn.getlastbox().topof(b)){
								tail=new Boxnode1(bn,b);
								q.add(tail);
							}
						}
						else if( (bn.getboxes().size()>edge) && ( (size/edge)%2!=0) ){ // the even line: check bn.rightof() and previous line's bn.getboxes().get(top_i-1).topof() 
							int line_no=size/edge+1;
							int top_i=2*(line_no-1)*edge-size;
									
							if( (bn.getlastbox().rightof(b)) && (bn.getboxes().get(top_i-1).topof(b)) ){
								tail=new Boxnode1(bn,b);
								q.add(tail);
							}
						}
						else if( (bn.getboxes().size()>edge) && ( (size/edge)%2==0) ){ // the odd line: check bn.leftof() and previous line's bn.getboxes().get(top_i-1).topof() 
							int line_no=size/edge+1;
							int top_i=2*(line_no-1)*edge-size;
									
							if( (bn.getlastbox().leftof(b)) && (bn.getboxes().get(top_i-1).topof(b)) ){
								tail=new Boxnode1(bn,b);
								q.add(tail);
							}
						}
						if(tail.getboxes().size()==edge*edge) {System.out.println(tail.getboxes().toString());success=true;break;}
					}
					
					if(success) break;
				}
				if(success) break;
			}
			
			if(success)	System.out.println("Game "+index+": Possible");
			else		System.out.println("Game "+index+": Impossible");
			System.out.println();
			
			success=false;
			box.reset();
			boxes.clear();
			edge=in.nextInt();
		}
	}
}

class Box1{
	private int id;
	private int top;
	private int right;
	private int bottom;
	private int left;
	
	public Box1(){
		id=0;
		top=0;
		right=0;
		bottom=0;
		left=0;
	}
	
	public Box1(int i, int t, int r, int b, int l){
		id=i;
		top=t;
		right=r;
		bottom=b;
		left=l;
	}
	
	public Box1(Box1 box){
		id=box.getId();
		top=box.getTop();
		right=box.getRight();
		bottom=box.getBottom();
		left=box.getLeft();
	}
	
	public void printself(){
		System.out.println("Id is "+id+",top is "+top+",right is "+right+",bottom is "+bottom+",left is "+left);
	}
	
	public int getId() {
		return id;
	}

	public int getTop(){
		return top;
	}
	
	public int getRight(){
		return right;
	}
	
	public int getBottom(){
		return bottom;
	}
	
	public int getLeft(){
		return left;
	}
	
	public void setId(int i){
		id=i;
	}

	public void setTop(int t){
		top=t;
	}

	public void setRight(int r){
		right=r;
	}

	public void setBottom(int b){
		bottom=b;
	}

	public void setLeft(int l){
		left=l;
	}

	public void setAll(int i, int t, int r, int b, int l){
		id=i;
		top=t;
		right=r;
		bottom=b;
		left=l;
	}
	
	public void reset(){
		id=0;
		top=0;
		right=0;
		bottom=0;
		left=0;
	}
	
	public boolean leftof(Box1 b){
		return (right==b.getLeft())?true:false;
	}
	
	public boolean rightof(Box1 b){
		return (left==b.getRight())?true:false;
	}
	
	public boolean topof(Box1 b){
		return (bottom==b.getTop())?true:false;
	}
	
	public boolean bottomof(Box1 b){
		return (top==b.getBottom())?true:false;
	}
}

class Boxnode1{
	private ArrayList<Box1> boxes=new ArrayList<Box1>();//box list in this node 
	private Box1 lastbox;	//the last box
	
	Boxnode1(){
		boxes.clear();
		lastbox=null;
	}
	
	Boxnode1(Box1 b){
		boxes.add(b);
		lastbox=b;
	}
	
	Boxnode1(Boxnode1 bn, Box1 b){
		boxes=new ArrayList<Box1>(bn.boxes); //a new list instead of using bn's
		boxes.add(b);
		lastbox=b;
	}
	
	Boxnode1(ArrayList<Box1> al, Box1 b){
		boxes=al;
		boxes.add(b);
		lastbox=b;
	}
	
	public void printself(){
		System.out.println("lastbox:");
		lastbox.printself();
		for(Box1 s : boxes) System.out.print("Box:"+s.getId()+".");
		System.out.println();
	}
	
	public void setboxes(ArrayList<Box1> al){
		boxes=al;
	}
	
	public ArrayList<Box1> getboxes(){
		return boxes;
	}
	
	public Box1 getlastbox(){
		return lastbox;
	}
	
	public boolean contain(Box1 b){
//		boolean contain=false;
		for(Box1 box:boxes){
			if(box.getId()==b.getId()) return true;
		}
		return false;
	}
	
//	public Box getlastbox(){
//		return lastbox;
//	}
}
