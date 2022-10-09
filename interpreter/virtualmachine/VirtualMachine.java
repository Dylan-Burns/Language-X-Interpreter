package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import java.util.EmptyStackException;
import java.util.Stack;

public class VirtualMachine {
    private final RunTimeStack runTimeStack;
    private final Stack<Integer> returnAddress;
    private final Program program;
    private int programCounter;
    private boolean isRunning;
    private boolean dumpState;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
        this.isRunning = true;
        this.dumpState = false;
        while (this.isRunning) {
            //assign first item in program ArrayList to the corresponding ByteCode at index programCounter
            ByteCode bc = this.program.getCode(this.programCounter);
            //call the corresponding ByteCode to execute its task for the virtual machine
            bc.execute(this);
            //if dump is on, print out the current ByteCode in the virtual machine
            if (this.dumpState) {
                System.out.println(bc.toString(this));
                // check if ByteCode is not DUMPING
                if (!bc.getClass().getSimpleName().equals("DumpCode")) {
                    // dump runTimeStack
                    System.out.println(this.dumpRunTimeStack());
                }
            }
            //increment programCounter to update the ByteCode being processed for the virtual machine
            this.programCounter++;
        }
    }

    //halts execution
    public void halt() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    //turn on dump
    public void DumpOn() {
        if (!this.dumpState) {
            this.dumpState = true;
        }
    }

    //turn off dump
    public void DumpOff() {
        if (this.dumpState) {
            this.dumpState = false;
        }
    }

    //push the programCounter to the returnAddress Stack
    public void pushReturnAddress(int programCounter) {
        this.returnAddress.push(programCounter);
        this.returnAddress.peek();
    }

    //pop the top item in the returnAddress Stack
    public int popReturnAddress() {
        //if returnAddress stack is empty
        if (this.returnAddress.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.returnAddress.pop();
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getProgramCounter() {
        return this.programCounter;
    }

    private String dumpRunTimeStack() {
        return this.runTimeStack.dump();
    }

    public int peekRunTimeStack() {
        return this.runTimeStack.peek();
    }

    public void pushRunTimeStack(int i) {
        this.runTimeStack.push(i);
    }

    public int popRunTimeStack() {
        return this.runTimeStack.pop();
    }

    public void storeRunTimeStack(int offset) {
        this.runTimeStack.store(offset);
    }

    public void loadRunTimeStack(int offset) {
        this.runTimeStack.load(offset);
    }

    public int getFrameSizeRunTimeStack() {
        return this.runTimeStack.getFrameSize();
    }

    public String dumpFrameRunTimeStack(int i) {
        return this.runTimeStack.dumpFrame(i);
    }

    public void addFrameAtRunTimeStack(int offset) {
        this.runTimeStack.addFrameAt(offset);
    }

    public void popFrameRunTimeStack() {
        this.runTimeStack.popFrame();
    }
}
