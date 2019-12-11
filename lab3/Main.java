import grammar.CalculatorLexer;
import grammar.CalculatorParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathToInputFile = Paths.get("test.txt");
        ANTLRInputStream reader = new ANTLRInputStream(Files.newInputStream(pathToInputFile));
        CalculatorLexer lexer = new CalculatorLexer(reader);
        TokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        parser.calculate();
    }
}
