package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public abstract class JumpCode extends ByteCode {
    private String address;
    private int index = -1;

    protected void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    protected int getIndex() {
        if (this.index == -1) {
            throw new IndexOutOfBoundsException();
        }
        return this.index;
    }

    public String toString(VirtualMachine vm) {
        return this.name + " " + this.getAddress();
    }
}
