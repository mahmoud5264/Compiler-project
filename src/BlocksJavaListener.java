import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

public class BlocksJavaListener extends JavaParserBaseListener {
    TokenStreamRewriter rewriter;
    boolean isElse;

    public BlocksJavaListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.isElse = false;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (node.getText().equals("else")) isElse = true;
        if (node.getText().equals("if")) isElse = false;

    }


    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        String text = ctx.getChild(0).getText();
        if (text.equals("if") || text.equals("while") || text.equals("do") || text.equals("for")) {
            if (!ctx.statement(0).start.getText().equals("{")) {
                rewriter.insertBefore(ctx.statement(0).start, "{");
                rewriter.insertAfter(ctx.statement(0).stop, "}");
            }
        } else if (isElse) {
            isElse = false;
            if (ctx.getText().length() >= 3 && ctx.getText().substring(0, 3).equals("if(")) return;
            if (ctx.getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "}");
            }
        }

    }



}
