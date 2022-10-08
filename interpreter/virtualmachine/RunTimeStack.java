package interpreter.virtualmachine;

import java.util.EmptyStackException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class RunTimeStack {

    //ArrayList used to access locations of the rts
    private final ArrayList<Integer> runTimeStack;

    //holds initial activation record for function calls
    private final Stack<Integer> framePointer;

    public RunTimeStack() {
        this.runTimeStack = new ArrayList<>();
        this.framePointer = new Stack<>();
        this.framePointer.add(0);
    }

    private int getSize() {
        return this.runTimeStack.size();
    }

    public String dump() {
        try {
            if (this.getFrameSize() == 1) {
                return "[" + this.dumpFrame(1) + "]";
            } else {
                return this.dumpFrame(1);
            }
        } catch (EmptyStackException | IllegalArgumentException exception) {
            System.out.println("///////////" + exception+ "///////////");
            System.exit(-1);
            return "";
        }
    }

    public int peek() {
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.runTimeStack.get(this.getSize() - 1);
    }

    public int push(int i) {
        this.runTimeStack.add(i);
        return this.peek();
    }

    public int pop() {
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.runTimeStack.remove(this.getSize() - 1);
    }

    /**
     * stores the top item of the rts ands its offset from active frame
     *
     * @param offset difference between frames
     * @return store the item
     */
    public int store(int offset) {
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        int i = this.pop();
        this.runTimeStack.set(this.peekFrame() + offset, i);
        return i;
    }

    /**
     * loads a value from the rts at active frame, pushes it to top of the rts
     *
     * @param offset difference between frames
     * @return load item
     */
    public int load(int offset) {
        if (this.runTimeStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.push(this.runTimeStack.get(this.peekFrame() + offset));
    }

    public int getFrameSize() {
        return this.framePointer.size();
    }

    /**
     * outputs stream of framePointer dump from current frame
     *
     * @param i starting index
     * @return string of frames at each level
     */
    public String dumpFrame(int i) {
        if (this.framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        // validate position of i in framePointerStack
        if (i < 1 || i > this.getFrameSize()) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        int nextFrame;
        // build string output for each level
        for (int j = i - 1; j < this.getFrameSize(); j++) {
            if (j + 1 < this.getFrameSize()) {
                nextFrame = this.framePointer.get(j + 1);
            } else {
                nextFrame = this.getSize();
            }
            if (i != this.getFrameSize()) {
                result.append("[");
            }
            result.append(this.runTimeStack.subList(this.framePointer.get(j), nextFrame).stream().map(Object::toString).collect(Collectors.joining(",")));
            if (i != this.getFrameSize()) {
                result.append("]");
            }
            if (j + 1 < this.getFrameSize()) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public int peekFrame() {
        if (this.framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.framePointer.peek();
    }

    public void addFrameAt(int offset) {
        this.framePointer.push(this.getSize() - offset);
    }

    //pop the current frame off the rts, then remove the frame pointer from FramePointer Stack.
    public void popFrame() {
        if (this.framePointer.isEmpty()) {
            throw new EmptyStackException();
        }
        int last = this.peek();
        int i = this.framePointer.pop();
        if (this.getSize() > i) {
            this.runTimeStack.subList(i, this.getSize()).clear();
        }
        this.push(last);
    }
}