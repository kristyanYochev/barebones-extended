package uk.ac.soton.ky5u21.barebones;

import java.io.IOException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import uk.ac.soton.ky5u21.barebones.ProgramArguments.InvalidArguments;

public class Barebones {
  private static final int EXIT_FAILURE = 1;

  public static void main(String[] args) throws IOException {
    ProgramArguments arguments;
    try {
      arguments = new ProgramArguments(args);
    } catch (InvalidArguments invalidArguments) {
      System.err.println(invalidArguments.getMessage());
      System.exit(EXIT_FAILURE);
      // Suppress Intellij null warning
      // Intellij apparently does not know that Sys.exit kills the process
      return;
    }

    run(arguments);
  }

  private static void run(ProgramArguments arguments) throws IOException {
    BarebonesLexer lexer = new BarebonesLexer(CharStreams.fromFileName(arguments.getFilename()));

    BarebonesParser parser = new BarebonesParser(new CommonTokenStream(lexer));

    Interpreter interpreter = new Interpreter(arguments.getEnvironment());
    interpreter.visit(parser.program());

    Environment output = interpreter.getEnvironment();

    System.out.println(output);
  }
}
