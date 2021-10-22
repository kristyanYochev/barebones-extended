package uk.ac.soton.ky5u21.barebones;

import java.io.IOException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Barebones {

  public static void main(String[] args) throws IOException {
    BarebonesLexer lexer = new BarebonesLexer(CharStreams.fromStream(System.in));
    BarebonesParser parser = new BarebonesParser(new CommonTokenStream(lexer));
    Interpreter interpreter = new Interpreter();
    interpreter.visit(parser.program());
  }
}
