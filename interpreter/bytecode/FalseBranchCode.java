package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class FalseBranchCode extends JumpCode {

    public void init(ArrayList<String> args) {
        this.name = "FALSEBRANCH";
        if (args != null && args.size() == 1) {
            this.setAddress(args.get(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        try {
            if (vm.popRunTimeStack() == 0) {
                vm.setProgramCounter(this.getIndex() - 1);
            }
        } catch (IndexOutOfBoundsException | EmptyStackException exception) {
            System.out.println("///////////" + exception + "///////////");
            System.exit(-1);
        }

    }
}
