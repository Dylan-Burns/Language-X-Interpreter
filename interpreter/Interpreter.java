package interpreter;

import interpreter.loaders.ByteCodeLoader;
import interpreter.loaders.CodeTable;
import interpreter.loaders.InvalidProgramException;
import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <pre>
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the ByteCodes from file
 *     3. Run the virtual machine
 *
 *     THIS FILE CANNOT BE MODIFIED. DO NOT
 *     LET ANY EXCEPTIONS REACH THE
 *
 * </pre>
 */
public class Interpreter {

    private ByteCodeLoader byteCodeLoader;

    public Interpreter(String codeFile) throws IOException {
        CodeTable.init();
        byteCodeLoader = new ByteCodeLoader(codeFile);
    }

    void run() throws RuntimeStackIllegalAccess {
        Program program = null;
        program = byteCodeLoader.loadCodes();
        program.resolveAddress();
        VirtualMachine virtualMachine = new VirtualMachine(program);
        virtualMachine.executeProgram();

    }

    public static void main(String[] args) throws IOException, RuntimeStackIllegalAccess {

        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }
        (new Interpreter(args[0])).run();
    }
}