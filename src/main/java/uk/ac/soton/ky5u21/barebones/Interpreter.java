package uk.ac.soton.ky5u21.barebones;

import static uk.ac.soton.ky5u21.barebones.BarebonesParser.*;
import java.util.Scanner;

/**
 * This class implements a a visitor for the parse tree of barebones and executes each node it
 * iterates over.
 */
public class Interpreter extends BarebonesBaseVisitor<Void> {

  private final Environment environment;
  private final Scanner stdin = new Scanner(System.in);

  /**
   * Initialize the interpreter with the given environment
   *
   * @param environment The environment to run
   */
  public Interpreter(Environment environment) {
    this.environment = environment;
  }

  /**
   * Initialize the interpreter with am empty environment
   */
  public Interpreter() {
    this.environment = new Environment();
  }

  /**
   * Execution of a program. Just executes each statement one by one.
   *
   * @param ctx Program parse tree node
   * @return null
   */
  @Override
  public Void visitProgram(ProgramContext ctx) {
    ctx.children.forEach(this::visit);
    return null;
  }

  /**
   * Invoked when encountering a clear node. Clears the associated variable in the environment.
   *
   * @param ctx Clear parse tree node
   * @return null
   */
  @Override
  public Void visitClear(ClearContext ctx) {
    String varName = ctx.name.getText();
    environment.clear(varName);
    return null;
  }

  /**
   * Invoked when encountering an increment node. Increments the associated variable in the
   * environment.
   *
   * @param ctx Increment parse tree node.
   * @return null
   */
  @Override
  public Void visitIncrement(IncrementContext ctx) {
    String varName = ctx.name.getText();
    environment.increment(varName);
    return null;
  }

  /**
   * Invoked when encountering a decrement node. Decrements the associated variable in the
   * environment.
   *
   * @param ctx Decrement parse tree node.
   * @return null
   */
  @Override
  public Void visitDecrement(DecrementContext ctx) {
    String varName = ctx.name.getText();
    environment.decrement(varName);
    return null;
  }

  /**
   * Invoked when encountering a while loop node. Executes the body of the loop while the condition
   * variable is not 0.
   *
   * @param ctx While loop parse tree node.
   * @return null
   */
  @Override
  public Void visitWhileLoop(WhileLoopContext ctx) {
    String conditionVariableName = ctx.conditionVariable.getText();
    while (environment.valueOf(conditionVariableName) != 0) {
      ctx.body.forEach(this::visit);
    }
    return null;
  }

  /**
   * Invoked when encountering a input node. Gets a number from the standard input and sets
   * the value of the given variable to that number.
   *
   * @param ctx Input parse tree node
   * @return null
   */
  @Override
  public Void visitInput(InputContext ctx) {
    String inputVariableName = ctx.name.getText();
    int newValue = stdin.nextInt();

    environment.set(inputVariableName, newValue);

    return null;
  }

  /**
   * Retrieves a reference to the environment of the interpreter.
   *
   * @return The environment
   */
  public Environment getEnvironment() {
    return environment;
  }
}
