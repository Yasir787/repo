//program to implement cyclic redundancy check using java
import java.io.*;
class yasircrc
{
 public static void main(String args[]) throws IOException
 {
 //using BufferedReader class to take all the user input
 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 int[] data;
 int[] div;
 int[] divisor;
 int[] rem;
 int[] crc;
 int data_bits, divisor_bits, tot_length;

 //Ask the user to enter number of data bits.
 System.out.println("ENTER THE NUMBER OF DATA BITS:");
 data_bits=Integer.parseInt(br.readLine());
 data=new int[data_bits];
 System.out.println();

 //Take the input from user in 0's and 1's(Binary).
 System.out.println("ENTER THE DATA BITS:");
 for(int i=0; i<data_bits; i++)
 data[i]=Integer.parseInt(br.readLine());
 System.out.println();

 //Ask the user to enter number of the generator bits.
 System.out.println("ENTER THE NUMBER OF GENERATOR BITS:");
 divisor_bits=Integer.parseInt(br.readLine());
 divisor=new int[divisor_bits];
 System.out.println();

 //Take the user input in terms of 0's and 1's.
 System.out.println("ENTER THE GENERATOR BITS:");
 for(int i=0; i<divisor_bits; i++)
 divisor[i]=Integer.parseInt(br.readLine());
 System.out.println();
 //To calculate total length of codeword.
 tot_length=data_bits+divisor_bits-1; 
 div=new int[tot_length];
 rem=new int[tot_length];
 crc=new int[tot_length];

 /*$----------------CODE FOR GENERATION OF CRC---------------$*/
 for(int i=0;i<data.length;i++)
 div[i]=data[i];
 //print the data word after appending n zero's
 System.out.println("THE APPENDED DATA WORD IS:");
 for(int i=0; i< div.length; i++)
 System.out.print("\n"+div[i]);
 System.out.println();

 for(int j=0; j<div.length; j++){
 rem[j] = div[j];
 }

 rem=divide(div, divisor, rem); //function for modulo-2 division

 //Append dividend and remainder.
 for(int i=0;i<div.length;i++)
 {
 crc[i]=(div[i]^rem[i]);
 }

 //To print the remainder after dividing appended dataword.
 System.out.println("\nTHE REMAINDER AFTER DIVISION OF APPENDED DATAWORD BY GENERATOR BITS IS:");
 for(int i=data_bits;i<data_bits+divisor_bits-1;i++){
 div[i]=rem[i];
 System.out.println(div[i]);
 }
 System.out.println();

 //print the CRC code that is generated
 System.out.println("TRANSMITTED CODE WORD IS:");
 for(int i=0;i<crc.length;i++)
 System.out.print("\n"+crc[i]);
 System.out.println();

 /*$-----------CODE FOR ERROR DETECTION-----------$*/
 System.out.println();
 System.out.println("At Receiver side,");
 //Ask user to input received bit sequence.
 System.out.println("ENTER THE CODE WORD:");
 for(int i=0; i<crc.length; i++)
 crc[i]=Integer.parseInt(br.readLine());
 System.out.println();


 for(int j=0; j<crc.length; j++){
 rem[j] = crc[j];
 }

 rem=divide(crc, divisor, rem); //function for modulo-2 division

 //To print the remainder after dividing received codeword
 System.out.println("THE REMAINDER AFTER DIVIDING RECEIVED CODEWORD BY GENERATOR BITS IS:");
 for(int i=data_bits;i<data_bits+divisor_bits-1;i++){
 div[i]=rem[i];
 System.out.println(div[i]);
 }
 System.out.println();

 for(int i=0; i< rem.length; i++)
 {
 //checks whether the remainder is zero or not
 if(rem[i]!=0)
 {
 System.out.println("AS REMAINDER IS NON ZERO,THE RECEIVED CODE HAS ERRORS");
 break;
 }
 if(i==rem.length-1)
 System.out.println("AS REMAINDER IS ZERO ,RECEIVED CODE WORD HAS NO ERRORS");
 }

 }

 static int[] divide(int div[],int divisor[], int rem[])
 {
 int cur=0;
 while(true)
 {
 for(int i=0;i<divisor.length;i++)
 rem[cur+i]=(rem[cur+i]^divisor[i]);

 while(rem[cur]==0 && cur!=rem.length-1)
 cur++;

 if((rem.length-cur)<divisor.length)
 break;
 }
 return rem;
 }
}
