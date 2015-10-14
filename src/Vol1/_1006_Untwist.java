package Vol1;

import java.util.Scanner;

// Take care of the newline symbol.
// The formula is ciphercode[i] = (plaincode[ki mod n] - i) mod 28. 
// Another method to calculate plainCode[(i*k)%len]=(cipherCode[i]+i)%28.   
public class _1006_Untwist {
	private static Scanner in;

	static int PlainCode(int index, int[] cipherCode){
		int tmp=cipherCode[index]+index;
		while(tmp<0){
			tmp+=28;
		}
		while(tmp>27){
			tmp-=28;
		}
		return tmp;
	}
	
	static int IndexOfPlainCode(int index, int key, int stringLength){
		return (key*index)%stringLength;
	}
	
	public static void main(String[] args){
		int[] cipherCode=new int[70];
		int[] plainCode=new int[70];
		char[] plainText=new char[70];
		
		in = new Scanner(System.in);
		while (in.hasNextInt()){
			for(int i=0; i<70;i++)
			{
				cipherCode[i]=plainCode[i]=-1;
				plainText[i]='0';
			}
			int key=in.nextInt();
			if(key==0) break;
			String cipher=in.next();
			int stringLength=cipher.length();
//			System.out.println("cipher is "+cipher);
//			System.out.println("length of cipher is "+stringLength);
			
			for(int i=0; i<stringLength;i++)
			{
				char tmp=cipher.charAt(i);
				if((tmp>='a')&&(tmp<='z'))
					cipherCode[i]=tmp-'a'+1;
				else if(tmp=='_')
					cipherCode[i]=0;
				else if(tmp=='.')
					cipherCode[i]=27;
					
				int pindex=IndexOfPlainCode(i,key,stringLength);
				plainCode[pindex]=PlainCode(i,cipherCode);
				if(plainCode[pindex]==0)
					plainText[pindex]='_';
				else if(plainCode[pindex]==27)
					plainText[pindex]='.';
				else
					plainText[pindex]=(char)('a'+plainCode[pindex]-1);
				
			}
			
			for(int i=0;i<stringLength;i++)
			{
				System.out.print(plainText[i]);			
			}
			System.out.print('\n');			
//
//			for(int i=0; i<plainCode.length && plainCode[i]>-1;i++)
//			{
//				System.out.print(plainCode[i]);
//				System.out.print(" ");
//			}
		}
	}
}
