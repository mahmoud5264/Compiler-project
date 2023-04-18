import org.antlr.v4.runtime.TokenStreamRewriter;


public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), "import java.io.*;\n");
    }
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
        rewriter.insertAfter(ctx.getStart(), "//block number " + this.blockNumber + "\n");
        rewriter.insertAfter(ctx.getStart(), "try{\n");
        rewriter.insertAfter(ctx.getStart(), "FileWriter write" + this.blockNumber + " = new FileWriter(\"blocks.txt\",true);\n");
        rewriter.insertAfter(ctx.getStart(), "write" + this.blockNumber + ".write(\"block number " + this.blockNumber + " is visited\\n\");\n");
        rewriter.insertAfter(ctx.getStart(), "write" + this.blockNumber + ".close();\n");
        rewriter.insertAfter(ctx.getStart(), "}catch (IOException e) {throw new RuntimeException(e);}\n");
    }
}
