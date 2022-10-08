package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;

public abstract class ByteCode {
    protected String name = "";

    public abstract void init(ArrayList<String> args);

    public abstract void execute(VirtualMachine vm);

    public abstract String toString(VirtualMachine vm);
}
