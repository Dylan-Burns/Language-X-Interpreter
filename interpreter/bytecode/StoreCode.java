package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class StoreCode extends ByteCode {
    private int value;
    private String id = null;

    public void init(ArrayList<String> args) {
        this.name = "STORE";

        if (args == null || args.size() < 1 || args.size() > 2) {
            throw new IllegalArgumentException();
        }
        switch (args.size()) {
            case 1 -> this.value = Integer.parseInt(args.get(0));
            case 2 -> {
                this.value = Integer.parseInt(args.get(0));
                this.id = args.get(1);
            }
        }
    }

    public void execute(VirtualMachine vm) {
        try {
            vm.storeRunTimeStack(this.value);
        }  catch (EmptyStackException emptyStackException) {
            System.out.println(("///////////" + emptyStackException + "///////////"));
            System.exit(-1);
        }
    }

    public String toString(VirtualMachine vm) {
        // if one arg
        if (this.id == null) {
            return this.name + " " + this.value;
        }
        // if two args
        else {
            return this.name + " " + this.value + " " + this.id + "    " + this.id + "=" + vm.peekRunTimeStack();
        }
    }
}
