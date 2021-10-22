package uk.ac.soton.ky5u21.barebones;

import java.io.IOException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Barebones {
  private static final int EXIT_FAILURE = 1;

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: barebones [filename]");
      System.exit(EXIT_FAILURE);
    }

    runFile(args[0]);
  }

  private static void runFile(String filename) throws IOException {
    BarebonesLexer lexer = new BarebonesLexer(CharStreams.fromFileName(filename));

    BarebonesParser parser = new BarebonesParser(new CommonTokenStream(lexer));

    Interpreter interpreter = new Interpreter();
    interpreter.visit(parser.program());

    Environment output = interpreter.getEnvironment();

    System.out.println(output);
  }
}
