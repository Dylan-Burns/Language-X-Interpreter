package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private boolean dumpState;

    public void init(ArrayList<String> args) {
        this.name = "DUMP";
        if (args != null && args.size() == 1) {
            if ((args.get(0)).equals("ON")) {
                dumpState = true;
            } else {
                if (!(args.get(0)).equals("OFF")) {
                    throw new IllegalArgumentException();
                }

                dumpState = false;
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        if (dumpState) {
            vm.DumpOn();
        } else {
            vm.DumpOff();
        }

    }

    public String toString(VirtualMachine vm) {
        return dumpState ? this.name + " ON" : this.name + " OFF";
    }
}
