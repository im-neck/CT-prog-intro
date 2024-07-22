package expression.exceptions;

import expression.Const;
import expression.Generalization;
import expression.Variable;
import expression.parser.TripleParser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class ExpressionParser implements TripleParser {
    private enum Operations {
        NEGATE, POW2, LOG2, MULTIPLY, DIVIDE, SUBTRACT, ADD, BRACKET
    }

    private final Map<Operations, Integer> priority = new HashMap<>(Map.of(
            Operations.BRACKET, 10,
            Operations.NEGATE, 10,
            Operations.POW2, 10,
            Operations.LOG2, 10,
            Operations.MULTIPLY, 9,
            Operations.DIVIDE, 9,
            Operations.SUBTRACT, 8,
            Operations.ADD, 8));
    private boolean lastOp = true;

    @Override
    public Generalization parse(String expression) {
        LinkedList<Generalization> operands = new LinkedList<>();
        LinkedList<Operations> operators = new LinkedList<>();
        int openedBrackets = 0;
        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            } else if ((i + 1 < expression.length()) && lastOp && ch == '-' && Character.isDigit(expression.charAt(i + 1)) ||
                    Character.isDigit(ch)) {
                String num = parseInt(expression, i);
                i += num.length() - 1;
                if (num.charAt(0) != '-' && !operators.isEmpty() && operators.peek() == Operations.NEGATE) {
                    operands.push(new Const(Integer.parseInt("-" + num)));
                    operators.pop();
                } else {
                    operands.push(new Const(Integer.parseInt(num)));
                }
                lastOp = false;
            } else if (ch == 'x' || ch == 'y' || ch == 'z') {
                operands.push(new Variable("" + ch));
                lastOp = false;
            } else if (isPowLog(expression, i, "pow")) {
                i += getPowLog(expression, operators, i, "pow");
            } else if (isPowLog(expression, i, "log")) {
                i += getPowLog(expression, operators, i, "log");
            } else if (ch == '(') {
                operators.push(Operations.BRACKET);
                lastOp = true;
                openedBrackets++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != Operations.BRACKET) {
                    makeExpression(operators, operands);
                }
                if (operators.isEmpty()) {
                    throw new IncorrectBracketSequencException("not enough opened brackets", "incorrect BRACKET sequence");
                }
                operators.pop();
                lastOp = false;
                openedBrackets--;
            } else if (priority.containsKey(getOp(ch))) {
                if (ch == '-' && (operands.isEmpty() || lastOp)) {
                    ch = '!';
                }
                while (isOperationReady(getOp(ch), operators, operands)) {
                    makeExpression(operators, operands);
                }
                operators.push(getOp(ch));
                lastOp = true;
            }
            i++;
        }
        if (openedBrackets != 0) {
            throw new IncorrectBracketSequencException("not enough closed brackets", "incorrect BRACKET sequence");
        }
        while (!operators.isEmpty()) {
            makeExpression(operators, operands);
        }
        if (operands.size() > 1) {
            throw new IncorrectOperationException("no operations", "incorrect expression");
        } else if (operands.isEmpty()) {
            throw new ParsingException("empty expression", "incorrect expression");
        }
        return operands.pop();
    }

    private void makeExpression(LinkedList<Operations> operators, LinkedList<Generalization> operands) {
        Operations operation = operators.pop();
        if ((priority.get(operation) < priority.get(Operations.NEGATE)) && operands.size() == 1) {
            throw new IncorrectOperationException("not enough arguments", "incorrect expression");
        } else if (operands.isEmpty()) {
            throw new IncorrectOperationException("no arguments", "incorrect expression");
        }
        Generalization arg = operands.pop();
        Generalization operand;
        switch (operation) {
            case MULTIPLY -> operand = new CheckedMultiply(operands.pop(), arg);
            case DIVIDE -> operand = new CheckedDivide(operands.pop(), arg);
            case ADD -> operand = new CheckedAdd(operands.pop(), arg);
            case SUBTRACT -> operand = new CheckedSubtract(operands.pop(), arg);
            case POW2 -> operand = new CheckedPow2(arg);
            case LOG2 -> operand = new CheckedLog2(arg);
            case NEGATE -> operand = new CheckedNegate(arg);
            default -> throw new IncorrectOperationException("not correct operation", "incorrect expression");
        }
        operands.push(operand);
    }

    private boolean isOperationReady(Operations op, LinkedList<Operations> operators, LinkedList<Generalization> operands) {
        if (operators.isEmpty()) {
            return false;
        } else {
            Operations peek = operators.peek();
            return (peek != Operations.BRACKET &&
                    (priority.get(peek) > priority.get(op) || (priority.get(op) < priority.get(Operations.NEGATE) &&
                            Objects.equals(priority.get(peek), priority.get(op))) && operands.size() >= 2));
        }
    }

    private boolean isPowLog(String expression, int i, String op) {
        if (expression.charAt(i) == op.charAt(0) && (i + 4 < expression.length())) {
            return (expression.charAt(i + 1) == op.charAt(1) && expression.charAt(i + 2) == op.charAt(2) && expression.charAt(i + 3) == '2');
        } else {
            return false;
        }
    }

    private int getPowLog(String expression, LinkedList<Operations> operators, int i, String op) {
        Operations operation;
        if (Objects.equals(op, "pow")) {
            operation = Operations.POW2;
        } else {
            operation = Operations.LOG2;
        }
        if (Character.isWhitespace(expression.charAt(i + 4)) || expression.charAt(i + 4) == '(') {
            operators.push(operation);
            lastOp = true;
            return 3;
        } else {
            throw new IncorrectOperationException(expression.substring(i, i + 5), "incorrect " + op + " argument");
        }
    }

    private Operations getOp(char ch) {
        return switch (ch) {
            case '*' -> Operations.MULTIPLY;
            case '/' -> Operations.DIVIDE;
            case '+' -> Operations.ADD;
            case '-' -> Operations.SUBTRACT;
            case 'p' -> Operations.POW2;
            case 'l' -> Operations.LOG2;
            case '!' -> Operations.NEGATE;
            default -> throw new IncorrectSymbolException("" + ch, "incorrect symbol");
        };
    }

    private static String parseInt(String s, int pos) {
        StringBuilder num = new StringBuilder();
        if (s.charAt(pos) == '-') {
            num.append(s.charAt(pos));
            pos++;
        }
        for (int i = pos; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num.append(ch);
            } else {
                return num.toString();
            }
        }
        return num.toString();
    }
}