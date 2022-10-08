package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int n;

    public void init(ArrayList<String> args) {
        this.name = "ARGS";
        if (args != null && args.size() == 1) {
            n = Integer.parseInt(args.get(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        vm.addFrameAtRunTimeStack(n);
    }

    public String toString(VirtualMachine vm) {
        return this.name + " " + n;
    }
}
