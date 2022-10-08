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
            ByteCode code = this.program.getCode(this.programCounter);
            code.execute(this);
            if (this.dumpState) {
                System.out.println(code.toString(this));
                // check if ByteCode is not DUMP
                if (!code.getClass().getSimpleName().equals("DumpCode")) {
                    // dump runTimeStack
                    System.out.println(this.dumpRunTimeStack());
                }
            }
            this.programCounter++;
        }
    }

    public void halt() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    public void DumpOn() {
        if (!this.dumpState) {
            this.dumpState = true;
        }
    }

    public void DumpOff() {
        if (this.dumpState) {
            this.dumpState = false;
        }
    }

    public void pushReturnAddress(int programCounter) {
        this.returnAddress.push(programCounter);
        this.returnAddress.peek();
    }

    public int popReturnAddress() {
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