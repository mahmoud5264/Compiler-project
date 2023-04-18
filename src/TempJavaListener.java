import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;

public class TempJavaListener extends JavaParserBaseListener {
    TokenStreamRewriter rewriter;
    int blockNumber;
    int expressionNumber;
    boolean isInside;

    public TempJavaListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.blockNumber = 0;
        this.expressionNumber = 0;
        this.isInside = false;
    }


    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        this.blockNumber++;
        rewriter.insertAfter(ctx.getStart(), "//block number " + this.blockNumber + "\n");
        rewriter.insertAfter(ctx.getStart(), "try{\n");
        rewriter.insertAfter(ctx.getStart(), "FileWriter write" + this.blockNumber + " = new FileWriter(\"blocks.txt\",true);\n");
        rewriter.insertAfter(ctx.getStart(), "write" + this.blockNumber + ".write(\"block number " + this.blockNumber + " is visited\\n\");\n");
        rewriter.insertAfter(ctx.getStart(), "write" + this.blockNumber + ".close();\n");
        rewriter.insertAfter(ctx.getStart(), "}catch (IOException e) {throw new RuntimeException(e);}\n");
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), "import java.io.*;\n");
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        rewriter.insertAfter(ctx.getStart(), "public static boolean AddExpression(int currentNumber) {\n" +
                "try{\n" +
                "FileWriter writer = new FileWriter(\"expressions.txt\",true);\n" +
                "writer.write(\"expression number \"+currentNumber+\" is visited\\n\");\n" +
                "writer.close();\n}" +
                "catch(Exception e){}\n" +
                "return false;\n" +
                "}\n");
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (node.getText().equals("||") || node.getText().equals("&&")) {
            isInside = true;
        }
    }


    @Override
    public void enterExpression(JavaParser.ExpressionContext ctx) {
        if (isInside && ctx.AND() == null && ctx.OR() == null) {
            expressionNumber++;
            rewriter.insertBefore(ctx.getStart(), "(AddExpression(" + expressionNumber + ")||");
            rewriter.insertAfter(ctx.getStop(), ")");
            isInside = false;
            if (ctx.getText().charAt(0) == '(') isInside = true;
        }
    }
    @Override
    public void enterParExpression(JavaParser.ParExpressionContext ctx) {
        isInside = true;
    }


}
