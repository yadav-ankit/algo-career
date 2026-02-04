import java.util.*;

https://algo.monster/liteproblems/1628

You need to build a binary expression tree from a postfix expression and evaluate it.

Input: An array of strings representing a postfix expression, where:

Numbers (operands) are represented as strings like "4", "5", "7", "2"
Operators are represented as "+", "-", "*", "/"
Output: A binary expression tree (implemented using the Node interface) that represents the given postfix expression.

Example: For the infix expression 4*(5-(7+2)), 
the postfix representation is ["4","5","7","2","+","-","*"]. 
  You need to construct a tree that evaluates to the correct result.

  
public class ExpressionTree {

    // ---- Node interface ----
    interface Node {
        int evaluate();
    }

    // ---- Operand node ----
    static class OperandNode implements Node {
        int value;

        OperandNode(int value) {
            this.value = value;
        }

        public int evaluate() {
            return value;
        }
    }

    // ---- Operator node ----
    static class OperatorNode implements Node {
        char op;
        Node left, right;

        OperatorNode(char op, Node left, Node right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }

        public int evaluate() {
            int l = left.evaluate();
            int r = right.evaluate();

            return switch (op) {
                case '+' -> l + r;
                case '-' -> l - r;
                case '*' -> l * r;
                case '/' -> l / r; // assume valid input
                default -> throw new IllegalStateException("Invalid operator");
            };
        }
    }

    // ---- Build tree from postfix ----
    public Node buildTree(String[] postfix) {
        Deque<Node> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (isOperator(token)) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new OperatorNode(token.charAt(0), left, right));
            } else {
                stack.push(new OperandNode(Integer.parseInt(token)));
            }
        }
        return stack.pop(); // root
    }

    private boolean isOperator(String s) {
        return s.length() == 1 && "+-*/".contains(s);
    }

    // ---- Demo ----
    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        String[] postfix = {"4", "5", "7", "2", "+", "-", "*"};
        Node root = et.buildTree(postfix);
        System.out.println(root.evaluate()); // -16
    }
}
