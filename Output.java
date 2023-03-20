import org.antlr.v4.runtime.TokenStreamRewriter;

public class TempJavaListener extends JavaParserBaseListener {
    TokenStreamRewriter rewriter;
    int blockNumber;

    public TempJavaListener(TokenStreamRewriter rewriter) {//block number 1
        this.rewriter = rewriter;
        this.blockNumber = 0;

        if(true){//block number 2
            if(false){//block number 3

            }

            else{//block number 4

            }
        }
        else{//block number 5

        }
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {//block number 6
        this.blockNumber++;
        rewriter.insertAfter(ctx.getStart(),"//block number " + this.blockNumber);
    }
}
