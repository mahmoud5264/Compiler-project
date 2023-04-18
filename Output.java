import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Main {public static boolean AddExpression(int currentNumber) {
try{
FileWriter writer = new FileWriter("expressions.txt",true);
writer.write("expression number "+currentNumber+" is visited\n");
writer.close();
}catch(Exception e){}
return false;
}


    public static void temp(){//block number 1
try{
FileWriter write1 = new FileWriter("blocks.txt",true);
write1.write("block number 1 is visited\n");
write1.close();
}catch (IOException e) {throw new RuntimeException(e);}

        if((AddExpression(1)||true)){//block number 2
try{
FileWriter write2 = new FileWriter("blocks.txt",true);
write2.write("block number 2 is visited\n");
write2.close();
}catch (IOException e) {throw new RuntimeException(e);}


        }
    }
    public static void main(String[] args) throws Exception {//block number 3
try{
FileWriter write3 = new FileWriter("blocks.txt",true);
write3.write("block number 3 is visited\n");
write3.close();
}catch (IOException e) {throw new RuntimeException(e);}

        int a=3;
        int b=4;
        if((AddExpression(2)||a<b) || (AddExpression(3)||a==b)){//block number 4
try{
FileWriter write4 = new FileWriter("blocks.txt",true);
write4.write("block number 4 is visited\n");
write4.close();
}catch (IOException e) {throw new RuntimeException(e);}

            if((AddExpression(4)||a==5)){//block number 5
try{
FileWriter write5 = new FileWriter("blocks.txt",true);
write5.write("block number 5 is visited\n");
write5.close();
}catch (IOException e) {throw new RuntimeException(e);}

                a++;
            }
        }
        if((AddExpression(5)||a>b) || (AddExpression(6)||a==b)){//block number 6
try{
FileWriter write6 = new FileWriter("blocks.txt",true);
write6.write("block number 6 is visited\n");
write6.close();
}catch (IOException e) {throw new RuntimeException(e);}

            return;
        }
    }

}
