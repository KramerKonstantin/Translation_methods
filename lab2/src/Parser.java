import java.io.BufferedReader;
import java.text.ParseException;

class Parser {
    private LexicalAnalyzer lex;

    private Tree S() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("S");

        switch (token) {
            case LPAREN:
            case NOT:
            case VARIABLE:
                tree.addChild(A());
                tree.addChild(SPrime());
                break;
            case END:
                tree.addChild(new Tree("eps"));
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree SPrime() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("S'");

        switch (token) {
            case OR:
                tree.addChild(new Tree("or"));
                lex.nextToken();
                tree.addChild(A());
                tree.addChild(SPrime());
                break;
            case RPAREN:
            case END:
                tree.addChild(new Tree("eps"));
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree A() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("A");

        switch (token) {
            case LPAREN:
            case NOT:
            case VARIABLE:
                tree.addChild(B());
                tree.addChild(APrime());
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree APrime() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("A'");

        switch (token) {
            case XOR:
                tree.addChild(new Tree("xor"));
                lex.nextToken();
                tree.addChild(B());
                tree.addChild(APrime());
                break;
            case RPAREN:
            case END:
            case OR:
                tree.addChild(new Tree("eps"));
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree B() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("B");

        switch (token) {
            case LPAREN:
            case NOT:
            case VARIABLE:
                tree.addChild(C());
                tree.addChild(BPrime());
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree BPrime() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("B'");

        switch (token) {
            case AND:
                lex.nextToken();
                tree.addChild(new Tree("and"));
                tree.addChild(C());
                tree.addChild(BPrime());
                break;
            case END:
            case RPAREN:
            case XOR:
            case OR:
                tree.addChild(new Tree("eps"));
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree C() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("C");

        switch (token) {
            case NOT:
                tree.addChild(new Tree("not"));
                lex.nextToken();
                tree.addChild(D());
                break;
            case LPAREN:
            case VARIABLE:
                tree.addChild(D());
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    private Tree D() throws ParseException {
        Token token = lex.getCurToken();
        Tree tree = new Tree("D");

        switch (token) {
            case LPAREN:
                tree.addChild(new Tree("("));

                lex.nextToken();
                tree.addChild(S());

                token = lex.getCurToken();
                if (token != Token.RPAREN) {
                    throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
                }

                lex.nextToken();
                tree.addChild(new Tree(")"));
                break;
            case VARIABLE:
                tree.addChild(new Tree("var"));
                lex.nextToken();
                break;
            default:
                throw new ParseException("Incorrect token " + token + " at position " + lex.getCurPos(), lex.getCurPos());
        }

        return tree;
    }

    Tree parse(BufferedReader br) throws ParseException {
        lex = new LexicalAnalyzer(br);
        lex.nextToken();
        Tree result = S();
        if (lex.getCurToken() == Token.END) {
            return result;
        } else {
            throw new ParseException("End of expression", lex.getCurPos());
        }
    }
}
