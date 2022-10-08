package interpreter.loaders;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ByteCodeLoader {
    private final BufferedReader codeSource;

    public ByteCodeLoader(String file) throws IOException {
        this.codeSource = new BufferedReader(new FileReader(file));
    }

    public Program loadCodes() {
        Program program = new Program();

        try {
            String line;
            while((line = this.codeSource.readLine()) != null) {
                ArrayList<String> token = new ArrayList<>(Arrays.asList(line.split(" ")));
                token.removeAll(Arrays.asList("", null));
                if (token.size() > 0) {
                    Class<?> c = Class.forName("interpreter.bytecode." + CodeTable.getClassName(token.get(0)));
                    ByteCode bc = (ByteCode)c.getDeclaredConstructor().newInstance();
                    if (token.size() > 1) {
                        token.remove(0);
                        bc.init(token);
                    } else {
                        bc.init(null);
                    }

                    program.addCode(bc);
                }
            }

            program.resolveAddress();
        } catch (Exception exception) {
            System.out.println("///////////" + exception + "///////////");
            System.exit(-1);
        }

        return program;
    }
}