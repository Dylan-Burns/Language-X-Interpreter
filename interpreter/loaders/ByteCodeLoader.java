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
        //create br containing fr that reads data from the x.cod file
        this.codeSource = new BufferedReader(new FileReader(file));
    }

    public Program loadCodes() {
        //initialize program obj
        Program program = new Program();

        try {
            String line;
            //read data from file line by line until the file ends
            while((line = this.codeSource.readLine()) != null) {
                //separate the data on each line into tokens
                ArrayList<String> token = new ArrayList<>(Arrays.asList(line.split(" ")));
                token.removeAll(Arrays.asList("", null));
                //save first token as ByteCode
                if (token.size() > 0) {
                    //save class name at first position for lookup in CodeTable of Bytecodes
                    Class<?> c = Class.forName("interpreter.bytecode." + CodeTable.getClassName(token.get(0)));
                    //create new instance of the ByteCode read from file
                    ByteCode bc = (ByteCode)c.getDeclaredConstructor().newInstance();
                    //if the line contains more than 1 token
                    if (token.size() > 1) {
                        //remove ByteCode since already stored
                        token.remove(0);
                        //initialize the ByteCode with its argument
                        bc.init(token);
                    } else {
                        //no arg to initialize for ByteCode
                        bc.init(null);
                    }
                    //add the ByteCode to the Program ArrayList
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
