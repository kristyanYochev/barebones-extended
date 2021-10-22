package uk.ac.soton.ky5u21.barebones;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnvironmentTest {
  @Test
  public void theValueOfAVariableIsZeroByDefault() {
    Environment environment = new Environment();

    assertEquals(0, environment.valueOf("x"));
    assertEquals(0, environment.valueOf("variable"));
    assertEquals(0, environment.valueOf("another_variable"));
  }

  @Test
  public void incrementingAdds1ToTheValueOfAVariable() {
    Environment environment = new Environment();

    assertEquals(0, environment.valueOf("x"));

    environment.increment("x");
    assertEquals(1, environment.valueOf("x"));

    environment.increment("x");
    assertEquals(2, environment.valueOf("x"));

    environment.increment("x");
    assertEquals(3, environment.valueOf("x"));
  }

  @Test
  public void decrementingSubtracts1FromTheValueOfAVariable() {
    Environment environment = new Environment();

    assertEquals(0, environment.valueOf("x"));

    environment.increment("x");
    environment.increment("x");
    environment.increment("x");

    assertEquals(3, environment.valueOf("x"));

    environment.decrement("x");
    assertEquals(2, environment.valueOf("x"));

    environment.decrement("x");
    assertEquals(1, environment.valueOf("x"));

    environment.decrement("x");
    assertEquals(0, environment.valueOf("x"));
  }

  @Test
  public void clearingAVariableResetsItsValueTo0() {
    Environment environment = new Environment();

    assertEquals(0, environment.valueOf("x"));

    environment.increment("x");
    environment.increment("x");
    environment.increment("x");

    assertEquals(3, environment.valueOf("x"));

    environment.clear("x");

    assertEquals(0, environment.valueOf("x"));
  }

  @Test
  public void usingAVariableCreatesIt() {
    Environment environment = new Environment();

    assertFalse(environment.variableExists("x"));
    assertFalse(environment.variableExists("y"));

    assertEquals(0, environment.valueOf("x"));

    assertTrue(environment.variableExists("x"));
    assertFalse(environment.variableExists("y"));
  }
}