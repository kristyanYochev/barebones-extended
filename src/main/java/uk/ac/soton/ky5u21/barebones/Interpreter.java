package uk.ac.soton.ky5u21.barebones;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.ClearContext;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.DecrementContext;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.IncrementContext;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.ProgramContext;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.StatementContext;
import uk.ac.soton.ky5u21.barebones.BarebonesParser.WhileLoopContext;

public class Interpreter implements BarebonesListener {

  @Override
  public void enterProgram(ProgramContext ctx) {
    System.out.println("Entering program");
  }

  @Override
  public void exitProgram(ProgramContext ctx) {
    System.out.println("Exiting program");
  }

  @Override
  public void enterStatement(StatementContext ctx) {

  }

  @Override
  public void exitStatement(StatementContext ctx) {

  }

  @Override
  public void enterClear(ClearContext ctx) {

  }

  @Override
  public void exitClear(ClearContext ctx) {

  }

  @Override
  public void enterIncrement(IncrementContext ctx) {

  }

  @Override
  public void exitIncrement(IncrementContext ctx) {

  }

  @Override
  public void enterDecrement(DecrementContext ctx) {

  }

  @Override
  public void exitDecrement(DecrementContext ctx) {

  }

  @Override
  public void enterWhileLoop(WhileLoopContext ctx) {

  }

  @Override
  public void exitWhileLoop(WhileLoopContext ctx) {

  }

  @Override
  public void visitTerminal(TerminalNode node) {

  }

  @Override
  public void visitErrorNode(ErrorNode node) {

  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {

  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {

  }
}
