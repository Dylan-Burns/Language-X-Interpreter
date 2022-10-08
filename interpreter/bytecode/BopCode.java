package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.*;

public class BopCode extends ByteCode {
    private String operation;

    public void init(ArrayList<String> args) {
        this.name = "BOP";
        // set operators
        Set<String> operators = new HashSet<>(
                Arrays.asList("+", "-", "/", "*", "==", "!=", "<=", "<", ">=", ">", "|", "&")
        );

        // check for singleton
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        // check if value is in operators HashSet
        if (!operators.contains(args.get(0))) {
            throw new IllegalArgumentException();
        }
        operation = args.get(0);
    }

    public void execute(VirtualMachine vm) {
        int val1 = 0;
        int val2 = 0;
        int result = 0;

        try {
            val2 = vm.popRunTimeStack();
            val1 = vm.popRunTimeStack();
        } catch (EmptyStackException emptyStackException) {
            System.out.println("**** " + emptyStackException);
            System.exit(-1);
        }
        switch (operation) {
            case "+" -> result = val1 + val2;
            case "-" -> result = val1 - val2;
            case "/" -> result = val1 / val2;
            case "*" -> result = val1 * val2;
            case "==" -> result = val1 == val2 ? 1 : 0;
            case "!=" -> result = val1 != val2 ? 1 : 0;
            case "<=" -> result = val1 <= val2 ? 1 : 0;
            case "<" -> result = val1 < val2 ? 1 : 0;
            case ">=" -> result = val1 >= val2 ? 1 : 0;
            case ">" -> result = val1 > val2 ? 1 : 0;
            case "|" -> result = val1 == 1 || val2 == 1 ? 1 : 0;
            case "&" -> result = val1 == 1 && val2 == 1 ? 1 : 0;
        }
        vm.pushRunTimeStack(result);
    }

    public String toString(VirtualMachine vm) {
        return this.name + " " + operation;
    }
}