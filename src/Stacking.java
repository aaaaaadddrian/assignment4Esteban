import java.util.*;

public class Stacking {

    public static void main(String[] args) {
        System.out.println(infixPostfix("1+2*(3^4-5)"));
    }

    public int findIndex(Object T, Deque deque){
        for(int i = 0; i < deque.size(); i++){
            if(deque.pollFirst() == T){
                return i;
            }
            if(deque.pollLast() == T){
                return deque.size() - i;
            }
        }
        return -1;
    }

    public boolean balancedBrackets(String s){
        Stack<Character> stack = new Stack<>();

        if(s.length() == 1){
            return false;
        }

        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else if(c == ')'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop() != '('){
                    return false;
                }
            }else if(c == '}'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop() != '{'){
                    return false;
                }
            }else if(c == ']'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop() != '['){
                    return false;
                }
            }else{
                return true;
            }
        }
        if(!stack.empty()){
            return false;
        }
        return true;
    }

    public String decodeString(String s){
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackInt = new Stack<>();
        String current = "";
        int count = 0;

        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                count = count * 10 + (c - '0');
            }else if(c == '['){
                stackInt.push(count);
                stackStr.push(current);
                count = 0;
                current = "";
            }else if(c == ']'){
                int k = stackInt.pop();
                String temp = stackStr.pop();
                for(int i = 0; i < k; i++){
                    temp = temp + current;
                }
                current = temp;

            }else{
                current = current + c;
            }
        }
        return current;
    }

    public static int priority(char c){
        if(c == '+' || c == '-'){
            return 1;
        }else if(c == '*' || c == '/'){
            return 2;
        }else if(c == '^'){
            return 3;
        }
        return -1;
    }

    public static String infixPostfix(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (Character.isDigit(current)) {
                sb.append(current);
            } else if (current == '(') {
                stack.push(current);

            } else if (current == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (current == '+' || current == '-' || current == '*' || current == '/' || current == '^'){
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(current)) {
                    sb.append(stack.pop());
                }
                stack.push(current);
            }

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


}
