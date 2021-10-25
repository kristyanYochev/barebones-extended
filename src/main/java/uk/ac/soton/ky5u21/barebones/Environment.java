package uk.ac.soton.ky5u21.barebones;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Instances of this class store the variable values during program execution
 */
public class Environment {

  private final Map<String, Integer> variables;

  /**
   * Initialize the environment with the variable values
   *
   * @param variables A map of type name->value
   */
  public Environment(Map<String, Integer> variables) {
    this.variables = variables;
  }

  /**
   * Initialize the environment with no variables
   */
  public Environment() {
    this.variables = new HashMap<>();
  }

  /**
   * Retrieves the value of a variable given the name. If the variable has not been encountered yet,
   * its value is cleared and a 0 is returned.
   *
   * @param name The name of the variable
   * @return The value of the variable
   */
  public int valueOf(String name) {
    Integer value = variables.get(name);

    if (value == null) {
      clear(name);
      return 0;
    } else {
      return value;
    }
  }

  /**
   * Sets the value of a variable to 0.
   *
   * @param name The name of the variable
   */
  public void clear(String name) {
    variables.put(name, 0);
  }

  /**
   * Adds 1 to the value of a variable and stores it back in itself.
   *
   * @param name The name of the variable
   */
  public void increment(String name) {
    variables.put(name, valueOf(name) + 1);
  }

  /**
   * Subtracts 1 from the value of the variable and stores it back in itself.
   *
   * @param name The name of the variable
   */
  public void decrement(String name) {
    variables.put(name, valueOf(name) - 1);
  }

  /**
   * Returns true if the variable has ever been used. Used for tests. This method *does not* create
   * a variable.
   *
   * @param name The name of the variable
   * @return `true` if the variable has been used, otherwise `false`
   */
  public boolean variableExists(String name) {
    return variables.get(name) != null;
  }

  @Override
  public String toString() {
    return variables.entrySet().stream()
        .map(variable -> variable.getKey() + '=' + variable.getValue())
        .collect(Collectors.joining("\n"));
  }
}
