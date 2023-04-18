import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.Scanner;

public class HTMLJavaListener extends JavaParserBaseListener {
    TokenStreamRewriter rewriter;
    int blockNumber;
    int parExpressionNumber;
    int expressionNumber;


    int startExpression = 0;
    int endExpression = 0;
    boolean isInside = false;

    public HTMLJavaListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.blockNumber = 0;
        this.expressionNumber = 0;
        this.parExpressionNumber = 0;

    }

    public boolean isBlockExist() {
        boolean exist = false;
        try {
            File file = new File("blocks.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("block number " + this.blockNumber + " is visited")) {
                    exist = true;
                }
            }
            scanner.reset();
            scanner.close();
            return exist;
        } catch (IOException e) {
            return false;
        }
    }


    public boolean isOrange() {
        boolean isAllExpreesionsVisited = true;
        boolean isVisitedPar = false;
        for (int i = startExpression; i <= endExpression; i++) {
            String s = "expression number " + i + " is visited";
            try {
                File file = new File("expressions.txt");
                Scanner reader = new Scanner(file);
                boolean cur = false;
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if (data.equals(s)) {
                        cur = true;
                    }
                }
                if (cur) isVisitedPar = true;
                else isAllExpreesionsVisited = false;
                reader.close();
            } catch (FileNotFoundException e) {

            }
        }
        if (!isAllExpreesionsVisited && isVisitedPar) return true;
        else return false;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        this.blockNumber++;
        if (!isBlockExist()) {
            rewriter.insertBefore(ctx.getStart(), "<pre style=\"background-color:red;\">");
            rewriter.insertAfter(ctx.getStop(), "</pre>\n");
        }
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), "<pre>\n");
        rewriter.insertBefore(ctx.getStart(), "<body style=\"background-color:green;\">\n");
        rewriter.insertBefore(ctx.getStart(), "</head>\n");
        rewriter.insertBefore(ctx.getStart(), "<head>\n");
        rewriter.insertBefore(ctx.getStart(), "<html>\n");
    }

    @Override
    public void exitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertAfter(ctx.getStop(), "</pre>\n");
        rewriter.insertAfter(ctx.getStop(), "</body>\n");
        rewriter.insertAfter(ctx.getStop(), "</html>\n");
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (node.getText().equals("||") || node.getText().equals("&&")) {
            isInside = true;
        }
    }

    @Override
    public void enterParExpression(JavaParser.ParExpressionContext ctx) {
        isInside = true;
        startExpression = endExpression + 1;
    }

    @Override
    public void exitParExpression(JavaParser.ParExpressionContext ctx) {
        if (isOrange()) {
            rewriter.insertBefore(ctx.getStart(), "<span style=\"background-color:orange;\">");
            rewriter.insertAfter(ctx.getStop(), "</span>");
        }
    }

    @Override
    public void enterExpression(JavaParser.ExpressionContext ctx) {
        System.out.println(ctx.getText());
        if (isInside && ctx.AND() == null && ctx.OR() == null) {
            endExpression++;
            isInside = false;
        }
    }
}
