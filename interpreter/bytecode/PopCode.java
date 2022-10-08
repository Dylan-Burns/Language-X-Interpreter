package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class PopCode extends ByteCode {
    private int number;

    public void init(ArrayList<String> args) {
        this.name = "POP";
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.number = Integer.parseInt(args.get(0));
    }

    public void execute(VirtualMachine vm) {
        try {
            for (int i = 0; i < this.number; i++) {
                vm.popRunTimeStack();
            }
        }  catch (EmptyStackException emptyStackException) {
            System.out.println(("///////////" + emptyStackException + "///////////"));
            System.exit(-1);
        }
    }

    public String toString(VirtualMachine vm) {
        return this.name + " " + this.number;
    }
}
