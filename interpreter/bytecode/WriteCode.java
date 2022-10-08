package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class WriteCode extends ByteCode {
    public void init(ArrayList<String> args) {
        this.name = "WRITE";

        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekRunTimeStack());
    }

    public String toString(VirtualMachine vm) {
        return this.name;
    }
}
