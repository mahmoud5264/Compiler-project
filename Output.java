
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {//block number 1
FileWriter write1 = new FileWriter("Output.txt",true);
write1.write("block number 1 is visited\n");
write1.close();

        int a=4;
        int b=4;

        if(a<b){//block number 2
FileWriter write2 = new FileWriter("Output.txt",true);
write2.write("block number 2 is visited\n");
write2.close();

            System.out.println("a is less than b");
            for(int i=0;i<2;i++){//block number 3
FileWriter write3 = new FileWriter("Output.txt",true);
write3.write("block number 3 is visited\n");
write3.close();


            }
        }
        else if(a==b){//block number 4
FileWriter write4 = new FileWriter("Output.txt",true);
write4.write("block number 4 is visited\n");
write4.close();

            System.out.println("a is equal to b");
            for(int i=0;i<2;i++){//block number 5
FileWriter write5 = new FileWriter("Output.txt",true);
write5.write("block number 5 is visited\n");
write5.close();


            }
        }
        else{//block number 6
FileWriter write6 = new FileWriter("Output.txt",true);
write6.write("block number 6 is visited\n");
write6.close();
//block number 4

            System.out.println("a is bigger than b");
            for(int i=0;i<3;i++){//block number 7
FileWriter write7 = new FileWriter("Output.txt",true);
write7.write("block number 7 is visited\n");
write7.close();


            }
        }
        for(int i=0;i<4;i++){//block number 8
FileWriter write8 = new FileWriter("Output.txt",true);
write8.write("block number 8 is visited\n");
write8.close();


        }
    }

}
