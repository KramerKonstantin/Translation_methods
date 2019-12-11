grammar Calculator;

options {
    language = Java;
}

@header {
    import java.math.*;
    import java.util.HashMap;
    import java.util.Map;
}

@members {
    Map<String, Integer> values = new HashMap<>();
}

VAR
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

NUM
    : [0-9]+
    ;

WS: [ \n\t\r]+ -> skip;

calculate
    : statement+
	;

statement
	: VAR '=' expr ';' {
	    values.put($VAR.text, $expr.value);
	    System.out.println($VAR.text + " = " + $expr.value);
	}
	;

expr returns[int value]
    : resultFirst = xor {
        $value = $resultFirst.value;
    }
    ( '|' resultFollow = xor {
        $value |= $resultFollow.value;
    }
    )*
    ;

xor returns[int value]
    : resultFirst = and {
        $value = $resultFirst.value;
    }
    ( '^' resultFollow = and {
        $value ^= $resultFollow.value;
    }
    )*
    ;

and returns[int value]
    : resultFirst = sumAndSub {
        $value = $resultFirst.value;
    }
    ( '&' resultFollow = sumAndSub {
        $value &= $resultFollow.value;
    }
    )*
    ;

sumAndSub returns[int value]
	: resultFirst = mulAndDiv {
	    $value = $resultFirst.value;
	}
	( '+' resultFollow = mulAndDiv {
	    $value += $resultFollow.value;
	}
	| '-' resultFollow = mulAndDiv {
	    $value -= $resultFollow.value;
	}
	)*
	;

mulAndDiv returns[int value]
	: resultFirst = atomMinus {
        $value = $resultFirst.value;
    }
	( '*' resultFollow = atomMinus {
        $value *= $resultFollow.value;
    }
	| '/' resultFollow = atomMinus {
        $value /= $resultFollow.value;
    }
	)*
	;

atomMinus returns[int value]
    : result = atom {
        $value = $result.value;
    }
    | '-' result = atom {
        $value = -$result.value;
    }
    ;

atom returns[int value]
	: VAR {
	    try {
	        $value = (int) values.get($VAR.text);
        } catch (NullPointerException e) {
            System.err.println("Unresolved VAR : " + $VAR.text);
        }
	}
	| NUM {
            $value = Integer.parseInt($NUM.text);
	    }
	| '(' expr ')' {
	    $value = $expr.value;
	}
	;