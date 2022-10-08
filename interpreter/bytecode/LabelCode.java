package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String label;

    public void init(ArrayList<String> args) {
        this.name = "LABEL";

        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException();
        }
        this.label = args.get(0);
    }

    public void execute(VirtualMachine vm) {}

    public String toString(VirtualMachine vm) {
        return this.name + " " + this.getLabel();
    }

    public String getLabel() {
        return this.label;
    }
}
