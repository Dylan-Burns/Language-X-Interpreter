package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.HashMap;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.JumpCode;
import interpreter.bytecode.LabelCode;

public class Program {
    private final ArrayList<ByteCode> program;

    public Program() {
        this.program = new ArrayList<>();
    }

    /**
     * This function returns the ByteCode at a given index
     *
     * @param  pc index
     * @return ByteCode object
     */
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    /**
     * This function puts ByteCode to program ArrayList
     *
     * @param bc ByteCode instance
     */
    public void addCode(ByteCode bc) {
        this.program.add(bc);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently, all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-structure bytecodes are stored in.
     */
    public void resolveAddress() {
        // check if program ArrayList is not empty
        if (!this.program.isEmpty()) {
            HashMap<String,Integer> labels = new HashMap<>();
            ArrayList<Integer> unresolved = new ArrayList<>();

            // check every item on program ArrayList
            for (int i = 0; i < this.program.size(); i++) {
                //check each item in the ArrayList of ByteCodes to see if they match JumpCodes
                switch (this.program.get(i).getClass().getSimpleName()) {
                    //if matched add the ByteCode to the unresolved ArrayList to resolve once current process finishes
                    case "CallCode", "FalseBranchCode", "GotoCode" -> unresolved.add(i);
                    case "LabelCode" -> {
                        LabelCode lc = (LabelCode) this.program.get(i);
                        //store <ByteCode label, ByteCode position> into labels HashMap
                        labels.put(lc.getLabel(), i);
                    }
                }
            }

            JumpCode jc;
            for (int i : unresolved) {
                jc = (JumpCode) this.program.get(i);
                jc.setIndex(labels.get(jc.getAddress()));
            }
        }
    }
}
