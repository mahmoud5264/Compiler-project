import org.antlr.v4.runtime.TokenStreamRewriter;

public class TempJavaListener extends JavaParserBaseListener {
    TokenStreamRewriter rewriter;
    int blockNumber;

    public TempJavaListener(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.blockNumber = 0;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        this.blockNumber++;
        rewriter.insertAfter(ctx.getStart(),"//block number " + this.blockNumber);
    }
}
