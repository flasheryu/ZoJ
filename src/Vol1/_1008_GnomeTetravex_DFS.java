package Vol1;

import java.util.ArrayList;
import java.util.Scanner;

//need to pruning before DFS, here to combine the same square to one
public class _1008_GnomeTetravex_DFS {

	private static Scanner in;
	private static int edge;
	private static ArrayList<Boxnode> boxes=new ArrayList<Boxnode>();
	private static Box ibox=new Box();
	
	private static int index=1;
	private static boolean success=false;
	private static ArrayList<Box> res=new ArrayList<Box>();
	
	public static void main(String[] args){
		in=new Scanner(System.in);
		edge=in.nextInt();
		while(edge!=0){
			for(int i=0;i<edge*edge;i++){
				ibox.setTop(in.nextInt());
				ibox.setRight(in.nextInt());
				ibox.setBottom(in.nextInt());
				ibox.setLeft(in.nextInt());
				boolean box_new=true;
				for(Boxnode bn: boxes){
					if(bn.box.sameof(ibox)){
						bn.pluscount();
						box_new=false;
						break;
					}
				}
				if(box_new) boxes.add(new Boxnode(ibox));
			}
			
			//for every square, try dfs
			for(int i=0; i<boxes.size();i++){
				res.add(boxes.get(i).getbox());
				boxes.get(i).minuscount();
				
//				System.out.println("Start with:");
//				boxes.get(i).getbox().printself();
	
				dfs();
				boxes.get(i).pluscount();
				res.clear();
			}
			
			if(index!=1) System.out.println();
			if(success)	System.out.println("Game "+index+++": Possible");
			else		System.out.println("Game "+index+++": Impossible");
			
			success=false;
			ibox.reset();
			boxes.clear();
			res.clear();
			edge=in.nextInt();
		}
	}

	private static void dfs() {
		//return when the result is found
		if((res.size()==edge*edge)||success){
//			System.out.println("Result is");
//			for(Box b:res) 
//				b.printself();
			success=true;
			return;
		}
		for(int i=0; i<boxes.size();i++){
			Box b=new Box(boxes.get(i).getbox());
			Boxnode bn=boxes.get(i);
			
			if(bn.getcount()==0) {
//				System.out.println("The box is exhausted:");
//				b.printself();
				continue;
			}
			
			if( (res.size()>0) && (res.size()<edge) ){	//the first line: check bn.getlastbox().leftof() only
				if(res.get(res.size()-1).leftof(b)){
					res.add(b);
					bn.minuscount();
					dfs();
					res.remove(b);
					bn.pluscount();
				}
			}
			else if( (res.size()%edge)==0 ){// end of each line: check bn.topof()
				if(res.get(res.size()-1).topof(b)){
					res.add(b);
					bn.minuscount();
					dfs();
					res.remove(b);
					bn.pluscount();
				}
			}
			else if( (res.size()>edge) && ( (res.size()/edge)%2!=0) ){ // the even line: check bn.rightof() and previous line's bn.getboxes().get(top_i-1).topof() 
//				int line_no=res.size()/edge+1;
//				int top_i=2*(line_no-1)*edge-res.size();
				int top_i=2*(res.size()/edge)*edge-res.size();
							
				if( (res.get(res.size()-1).rightof(b)) && (res.get(top_i-1).topof(b)) ){
					res.add(b);
					bn.minuscount();
					dfs();
					res.remove(b);
					bn.pluscount();
				}
			}
			else if( (res.size()>edge) && ( (res.size()/edge)%2==0) ){ // the odd line: check bn.leftof() and previous line's bn.getboxes().get(top_i-1).topof() 
//				int line_no=res.size()/edge+1;
//				int top_i=2*(line_no-1)*edge-res.size();
				
				int top_i=2*(res.size()/edge)*edge-res.size();
								
				if( (res.get(res.size()-1).leftof(b)) && (res.get(top_i-1).topof(b)) ){
					res.add(b);
					bn.minuscount();
					dfs();
					res.remove(b);
					bn.pluscount();				
				}
			}
		}
	}		
}

class Box{
	private int top;
	private int right;
	private int bottom;
	private int left;
	
	public Box(){
		top=0;
		right=0;
		bottom=0;
		left=0;
	}
	
	public Box(int t, int r, int b, int l){
		top=t;
		right=r;
		bottom=b;
		left=l;
	}
	
	public Box(Box box){
		top=box.getTop();
		right=box.getRight();
		bottom=box.getBottom();
		left=box.getLeft();
	}
	
	public void printself(){
		System.out.println("Top is "+top+",right is "+right+",bottom is "+bottom+",left is "+left);
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

	public void setAll(int t, int r, int b, int l){
		top=t;
		right=r;
		bottom=b;
		left=l;
	}
	
	public void reset(){
		top=0;
		right=0;
		bottom=0;
		left=0;
	}
	
	public boolean sameof(Box b){
		return ( (top==b.getTop())&&(right==b.getRight())&&(bottom==b.getBottom())&&(left==b.getLeft()) )?true:false;
	}
	
	public boolean leftof(Box b){
		return (right==b.getLeft())?true:false;
	}
	
	public boolean rightof(Box b){
		return (left==b.getRight())?true:false;
	}
	
	public boolean topof(Box b){
		return (bottom==b.getTop())?true:false;
	}
	
	public boolean bottomof(Box b){
		return (top==b.getBottom())?true:false;
	}
}


class Boxnode{
	private int count;
	Box box = new Box();

	Boxnode(Box b){
		count=1;
		box=new Box(b);
	}

	public int getcount(){
		return count;
	}
	
	public Box getbox(){
		return box;
	}
	
	public void pluscount(){
		count++;
	}
	
	public void minuscount(){
		count--;
	}
	
}
