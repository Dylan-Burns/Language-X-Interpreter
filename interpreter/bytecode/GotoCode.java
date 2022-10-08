package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class GotoCode extends JumpCode {

    public void init(ArrayList<String> args) {
        this.name = "GOTO";
        if (args != null && args.size() == 1) {
            this.setAddress(args.get(0));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        try {
            vm.setProgramCounter(this.getIndex() - 1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("///////////" + indexOutOfBoundsException + "///////////");
            System.exit(-1);
        }

    }
}
