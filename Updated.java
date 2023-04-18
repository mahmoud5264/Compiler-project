import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        int a=3;
        int b=4;
        if(a<=b) {a++;}
        if(a>b) {a--;}
        else if(b>a) {a++;}
        else {a++;}
        if(a==b){
            a--;
        }
    }

}
