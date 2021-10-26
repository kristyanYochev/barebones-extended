package uk.ac.soton.ky5u21.barebones;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ProgramArguments {

  private static String generateErrorMessage(String message, Options options) {
    HelpFormatter formatter = new HelpFormatter();

    StringWriter usageMessage = new StringWriter();
    PrintWriter writer = new PrintWriter(usageMessage);

    formatter.printHelp(writer, 80, "barebones", "", options, 0, 0, "");

    return message + "\n" + usageMessage.toString();
  }

  private static Environment environmentFromInputArgs(String[] inputArgs) throws InvalidArguments {
    if (inputArgs == null) {
      return new Environment();
    }

    if (inputArgs.length % 2 != 0) {
      throw new InvalidArguments("Cannot parse input variables");
    }

    Map<String, Integer> variableValues = new HashMap<>();

    for (int i = 0; i < inputArgs.length; i += 2) {
      String variableName = inputArgs[i];
      int variableValue;
      try {
        variableValue = Integer.parseInt(inputArgs[i + 1]);
      } catch (NumberFormatException ex) {
        throw new InvalidArguments("Cannot set value of variable to " + inputArgs[i + 1], ex);

      }

      variableValues.put(variableName, variableValue);
    }

    return new Environment(variableValues);
  }

  public static class InvalidArguments extends Exception {

    public InvalidArguments(String message, Throwable cause) {
      super(message, cause);
    }

    public InvalidArguments(String message) {
      super(message);
    }
  }

  private final String filename;
  private final Environment environment;

  public ProgramArguments(String[] args) throws InvalidArguments {
    Options options = new Options();

    Option input = Option.builder("I")
        .required(false)
        .hasArgs()
        .argName("variable=value")
        .valueSeparator()
        .desc("Key-value pairs to set the initial values of variables")
        .build();

    options.addOption(input);

    CommandLineParser parser = new DefaultParser();
    CommandLine line;

    try {
      line = parser.parse(options, args);
    } catch (ParseException parseException) {
      throw new InvalidArguments(
          generateErrorMessage("Cannot parse command line arguments", options),
          parseException
      );
    }

    List<String> leftoverArgs = line.getArgList();

    if (leftoverArgs.size() != 1) {
      throw new InvalidArguments(
          generateErrorMessage("Expected one file, got " + leftoverArgs.size(), options)
      );
    }

    filename = leftoverArgs.get(0);

    environment = environmentFromInputArgs(line.getOptionValues('I'));
  }

  public String getFilename() {
    return filename;
  }

  public Environment getEnvironment() {
    return environment;
  }
}
