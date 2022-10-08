package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class CallCode extends JumpCode {

    public void init(ArrayList<String> args) {
        this.name = "CALL";
        if (args != null && args.size() == 1) {
            this.setAddress(args.get(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter());

        try {
            vm.setProgramCounter(this.getIndex() - 1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("///////////" + indexOutOfBoundsException + "///////////");
            System.exit(-1);
        }

    }

    public String toString(VirtualMachine vm) {
        try {

            return this.name + " " + this.getAddress() + "   " + this.getAddress().split("<<")[0] + "(" +
                    vm.dumpFrameRunTimeStack(vm.getFrameSizeRunTimeStack()) + ")";
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return this.name + " " + this.getAddress();
        }
    }
}
