import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

import static java.lang.Character.isLetter;

class LexicalAnalyzer {
    private BufferedReader br;
    private int curChar;
    private int curPos;
    private Token curToken;

    LexicalAnalyzer(BufferedReader br) throws ParseException {
        this.br = br;
        curPos = 0;
        nextChar();
    }

    private void nextChar() throws ParseException {
        curPos++;

        try {
            curChar = br.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    void nextToken() throws ParseException {
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }

        if (isLetter(curChar)) {
            StringBuilder word = new StringBuilder();
            while (isLetter(curChar)) {
                word.append((char) curChar);
                nextChar();
            }

            if (word.length() == 1) {
                curToken = Token.VARIABLE;
            } else {
                switch (word.toString()) {
                    case "not":
                        curToken = Token.NOT;
                        break;
                    case "and":
                        curToken = Token.AND;
                        break;
                    case "xor":
                        curToken = Token.XOR;
                        break;
                    case "or":
                        curToken = Token.OR;
                        break;
                    default:
                        throw new ParseException(word + " - there is no such operation", curPos);
                }
            }
        } else {
            switch (curChar) {
                case '(':
                    nextChar();
                    curToken = Token.LPAREN;
                    break;
                case ')':
                    nextChar();
                    curToken = Token.RPAREN;
                    break;
                case -1:
                    curToken = Token.END;
                    break;
                default:
                    throw new ParseException("Illegal character " + (char) curChar, curPos);
            }
        }
    }

    Token getCurToken() {
        return curToken;
    }

    int getCurPos() {
        return curPos;
    }
}
