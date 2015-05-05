package Vol1;

	import  java.io.BufferedInputStream;  
	import  java.util.Scanner;  
	  
	public   class  _1004_sample {  
	     static  String start; // record the first str   
	     static  String end; // record the rearranged str   
	  
	     public   static   void  dfs( char [] stack,  char [] sequence,  int  posInStart,  int  posInEnd,  int  posInStack) {  
	         if  (posInStart + posInEnd == start.length() *  2 ) { // if all the letter has been rearranged   
	             for  ( int  i =  0 ; i < sequence.length; i++) {  
	                System.out.print(sequence[i] +  " " ); // output the sequence   
	            }  
	            System.out.println();  
	        }  
	         if  (posInStart < start.length()) {  
	            sequence[posInStart + posInEnd] =  'i' ;  
	            stack[++posInStack] = start.charAt(posInStart); // push in   
	            dfs(stack, sequence, posInStart +  1 , posInEnd, posInStack);  
	            posInStack--; // pop out   
	        }  
	         if  (posInStack >=  0  && posInEnd < end.length() && stack[posInStack] == end.charAt(posInEnd)) { // pop out   
	            sequence[posInStart + posInEnd] =  'o' ;  
	            posInStack--; // pop out   
	            dfs(stack, sequence, posInStart, posInEnd +  1 , posInStack);  
	            stack[++posInStack] = end.charAt(posInEnd);  
	        }  
	    }  
	  
	     public   static   void  main(String[] args) {  
	        Scanner sc =  new  Scanner( new  BufferedInputStream(System.in));  
	         while  (sc.hasNext()) {  
	            start = sc.next();  
	            end = sc.next();  
	             char [] stack =  new   char [start.length() *  2 ]; // use array as the stack   
	             char [] sequence =  new   char [start.length() *  2 ];   
	            System.out.println( "[" );  
	            dfs(stack, sequence,  0 ,  0 , - 1 );  
	            System.out.println( "]" );  
	        }  
	    }  
	}  
