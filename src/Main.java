import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile="Input.java";
        FileInputStream filestream=new FileInputStream(inputFile);
        ANTLRInputStream input=new ANTLRInputStream(filestream);
        JavaLexer lexer=new JavaLexer(input);
        CommonTokenStream tokens= new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new BlocksJavaListener(rewriter), tree);
        File output = new File("Updated.java");
        output.createNewFile();
        FileWriter write = new FileWriter("Updated.java");
        write.write(rewriter.getText());
        write.close();

        inputFile="Updated.java";
        filestream=new FileInputStream(inputFile);
        input=new ANTLRInputStream(filestream);
        lexer=new JavaLexer(input);
        tokens= new CommonTokenStream(lexer);
        parser = new JavaParser(tokens);
        tree = parser.compilationUnit();
        rewriter = new TokenStreamRewriter(tokens);
        walker = new ParseTreeWalker();
        walker.walk(new TempJavaListener(rewriter), tree);
        output = new File("Output.java");
        output.createNewFile();
        write = new FileWriter("Output.java");
        write.write(rewriter.getText());
        write.close();


        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("java Output.java");
        try {
            pr.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        inputFile="Updated.java";
        filestream=new FileInputStream(inputFile);
        input=new ANTLRInputStream(filestream);
        lexer=new JavaLexer(input);
        tokens= new CommonTokenStream(lexer);
        parser = new JavaParser(tokens);
        tree = parser.compilationUnit();
        rewriter = new TokenStreamRewriter(tokens);
        for (int i = 0; i < tokens.getTokens().size(); i++) {
            Token token = tokens.getTokens().get(i);
            if (token.getText().equals("<")) {
                rewriter.replace(token, "&lt;");
            }
            else if (token.getText().equals(">")) {
                rewriter.replace(token, "&gt;");
            }
            else if (token.getText().equals(">=")) {
                rewriter.replace(token, "&gt;=");
            }
            else if (token.getText().equals("<=")) {
                rewriter.replace(token, "&lt;=");
            }
        }

        walker = new ParseTreeWalker();
        walker.walk(new HTMLJavaListener(rewriter), tree);
        output = new File("final.html");
        output.createNewFile();
        write = new FileWriter("final.html");
        write.write(rewriter.getText());
        write.close();

    }

}
