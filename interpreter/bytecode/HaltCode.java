package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode {
    public void init(ArrayList<String> args) {
        this.name = "HALT";
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        vm.halt();
    }

    public String toString(VirtualMachine vm) {
        return this.name;
    }
}
