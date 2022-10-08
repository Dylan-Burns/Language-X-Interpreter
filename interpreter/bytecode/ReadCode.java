package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {
    public void init(ArrayList<String> args) {
        this.name = "READ";
        if (args != null) {
            throw new IllegalArgumentException();
        }
    }

    public void execute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        // prompt user until valid input is given
        System.out.print("Enter an integer: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid Input -- Enter an integer: ");
            scanner.next();
        }
        vm.pushRunTimeStack(scanner.nextInt());
        scanner.close();
    }

    public String toString(VirtualMachine vm) {
        return this.name;
    }
}
