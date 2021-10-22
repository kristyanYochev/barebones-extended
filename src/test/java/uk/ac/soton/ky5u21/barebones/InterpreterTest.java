package uk.ac.soton.ky5u21.barebones;

import static org.junit.jupiter.api.Assertions.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class InterpreterTest {
  private ParseTree parseProgram(String program) {
    BarebonesLexer lexer = new BarebonesLexer(CharStreams.fromString(program));
    BarebonesParser parser = new BarebonesParser(new CommonTokenStream(lexer));
    return parser.program();
  }

  @Test
  public void interpreterClearsVariable() {
    Interpreter interpreter = new Interpreter();
    ParseTree tree = parseProgram("clear x;");
    interpreter.visit(tree);

    Environment output = interpreter.getEnvironment();

    assertTrue(output.variableExists("x"));
    assertEquals(0, output.valueOf("x"));
  }

  @Test
  public void interpreterIncrementsVariable() {
    Interpreter interpreter = new Interpreter();
    ParseTree tree = parseProgram("clear x; clear y; incr x; incr x; incr y;");
    interpreter.visit(tree);

    Environment output = interpreter.getEnvironment();

    assertEquals(2, output.valueOf("x"));
    assertEquals(1, output.valueOf("y"));
  }

  @Test
  public void interpreterDecrementsVariable() {
    Interpreter interpreter = new Interpreter();
    ParseTree tree = parseProgram("clear x; clear y; incr x; incr x; decr x; incr y;");
    interpreter.visit(tree);

    Environment output = interpreter.getEnvironment();

    assertEquals(1, output.valueOf("x"));
    assertEquals(1, output.valueOf("y"));
  }

  @Test
  public void interpreterLoopsWhileAVariableIsNotZero() {
    Interpreter interpreter = new Interpreter();
    ParseTree tree = parseProgram(
        "clear x;\n"
            + "clear y;\n"
            + "incr x; incr x; incr x;\n"
            + "while x not 0 do;\n"
            + "incr y; incr y;\n"
            + "decr x;\n"
            + "end;\n"
    );
    interpreter.visit(tree);

    Environment output = interpreter.getEnvironment();

    assertEquals(0, output.valueOf("x"));
    assertEquals(6, output.valueOf("y"));
  }
}