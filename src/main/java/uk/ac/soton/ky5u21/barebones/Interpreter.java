package uk.ac.soton.ky5u21.barebones;

import static uk.ac.soton.ky5u21.barebones.BarebonesParser.*;

public class Interpreter extends BarebonesBaseVisitor<Void> {

  private final Environment environment = new Environment();

  @Override
  public Void visitProgram(ProgramContext ctx) {
    ctx.children.forEach(this::visit);
    return null;
  }

  @Override
  public Void visitClear(ClearContext ctx) {
    String varName = ctx.name.getText();
    environment.clear(varName);
    return null;
  }

  @Override
  public Void visitIncrement(IncrementContext ctx) {
    String varName = ctx.name.getText();
    environment.increment(varName);
    return null;
  }

  @Override
  public Void visitDecrement(DecrementContext ctx) {
    String varName = ctx.name.getText();
    environment.decrement(varName);
    return null;
  }

  @Override
  public Void visitWhileLoop(WhileLoopContext ctx) {
    String conditionVariableName = ctx.conditionVariable.getText();
    while (environment.valueOf(conditionVariableName) != 0) {
      ctx.children.forEach(this::visit);
    }
    return null;
  }

  public Environment getEnvironment() {
    return environment;
  }
}
