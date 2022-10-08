package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class ReturnCode extends JumpCode {
    public void init(ArrayList<String> args) {
        this.name = "RETURN";

        if (args != null && args.size() != 1) {
            throw new IllegalArgumentException();
        }
        if (args != null) {
            this.setAddress(args.get(0));
        }
    }

    public void execute(VirtualMachine vm) {
        try {
            vm.popFrameRunTimeStack();
            vm.setProgramCounter(vm.popReturnAddress());
        }  catch (EmptyStackException emptyStackException) {
            System.out.println(("///////////" + emptyStackException + "///////////"));
            System.exit(-1);
        }
    }

    @Override
    public String toString(VirtualMachine vm) {
        try {
            if (this.getAddress() != null) {
                return this.name + " " + this.getAddress() + "  EXIT " + this.getAddress().split("<<")[0]
                        + " : " + vm.peekRunTimeStack();
            } else {
                return this.name + "  EXIT " + vm.peekRunTimeStack();
            }
        }

        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return this.name + " " + this.getAddress();
        }
    }
}
