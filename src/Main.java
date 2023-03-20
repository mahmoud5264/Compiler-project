import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile="Input.java";
        FileInputStream filestream=new FileInputStream(inputFile);
        ANTLRInputStream input=new ANTLRInputStream(filestream);
        JavaLexer lexer=new JavaLexer(input);
        CommonTokenStream tokens= new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new TempJavaListener(rewriter), tree);
        File output = new File("Output.java");
        output.createNewFile();
        FileWriter write = new FileWriter("Output.java");
        write.write(rewriter.getText());
        write.close();
    }

}