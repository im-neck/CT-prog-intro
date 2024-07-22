package expression.parser;

import expression.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class ExpressionParser implements TripleParser {
    private enum Operations {
        NEGATE, MULTIPLY, DIVIDE, SUBTRACT, ADD, BRACKET, AND, XOR, OR, NOT
    }

    private static final Map<Operations, Integer> priority = new HashMap<>(Map.of(
            Operations.BRACKET, 10,
            Operations.NEGATE, 10,
            Operations.NOT, 10,
            Operations.MULTIPLY, 9,
            Operations.DIVIDE, 9,
            Operations.SUBTRACT, 8,
            Operations.ADD, 8,
            Operations.AND, 7,
            Operations.XOR, 6,
            Operations.OR, 5));
    boolean lastOp = true;

    @Override
    public Generalization parse(String expression) {
        LinkedList<Generalization> operands = new LinkedList<>();
        LinkedList<Operations> operators = new LinkedList<>();
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
            } else if (ch == '(') {
                operators.push(Operations.BRACKET);
                lastOp = true;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != Operations.BRACKET) {
                    makeExpression(operators, operands);
                }
                operators.pop();
                lastOp = false;
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
        while (!operators.isEmpty()) {
            makeExpression(operators, operands);
        }
        return operands.pop();
    }

    private static void makeExpression(LinkedList<Operations> operators, LinkedList<Generalization> operands) {
        Operations operation = operators.pop();
        Generalization arg = operands.pop();
        Generalization operand;
        switch (operation) {
            case MULTIPLY -> operand = new Multiply(operands.pop(), arg);
            case DIVIDE -> operand = new Divide(operands.pop(), arg);
            case ADD -> operand = new Add(operands.pop(), arg);
            case SUBTRACT -> operand = new Subtract(operands.pop(), arg);
            case AND -> operand = new And(operands.pop(), arg);
            case XOR -> operand = new Xor(operands.pop(), arg);
            case OR -> operand = new Or(operands.pop(), arg);
            case NOT -> operand = new Not(arg);
            default -> operand = new Negate(arg);
        }
        operands.push(operand);
    }

    private static boolean isOperationReady(Operations op, LinkedList<Operations> operators, LinkedList<Generalization> operands) {
        if (operators.isEmpty()) {
            return false;
        } else {
            Operations peek = operators.peek();
            return (peek != Operations.BRACKET && (priority.get(peek) > priority.get(op) || (priority.get(op) < priority.get(Operations.NEGATE) && Objects.equals(priority.get(peek), priority.get(op))) && operands.size() >= 2));
        }
    }

    private static Operations getOp(char ch) {
        return switch (ch) {
            case '*' -> Operations.MULTIPLY;
            case '/' -> Operations.DIVIDE;
            case '+' -> Operations.ADD;
            case '-' -> Operations.SUBTRACT;
            case '&' -> Operations.AND;
            case '^' -> Operations.XOR;
            case '|' -> Operations.OR;
            case '~' -> Operations.NOT;
            default -> Operations.NEGATE;
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