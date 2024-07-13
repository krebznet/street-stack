package com.dunkware.xstream.model.script.utils;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.dunkware.utils.core.helpers.DunkRandom;
import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import com.dunkware.xstream.model.util.XScriptModelFormatter;

public class XScriptModelGenerator {
    private static final List<String> FORMATS = List.of("PERCENT", "CURRENCY", "TEXT", "NUMBER", "SHORT_NUMBER");
    private static final Random RANDOM = new Random();

    public static XScriptModel generateModel(String modelName, String version, int numVariables, int numSignals, int numBots, int numVariableGroups, int numSignalGroups, int numBotGroups) {
        List<XScriptModelVariable> variables = generateVariables(numVariables, numVariableGroups);
        List<XScriptModelSignal> signals = generateSignals(numSignals, numSignalGroups);
        List<XScriptModelBot> bots = generateBots(numBots, numBotGroups);

        return new XScriptModel(modelName, version, variables, signals, bots);
    }

    private static List<XScriptModelVariable> generateVariables(int numVariables, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numVariables)
                .mapToObj(i -> new XScriptModelVariable(
                        "",
                        i,
                        DunkRandom.generateRandomWord(),
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        getRandomElement(FORMATS)
                ))
                .collect(Collectors.toList());
    }

    private static List<XScriptModelSignal> generateSignals(int numSignals, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numSignals)
                .mapToObj(i -> new XScriptModelSignal(
                        i,
                        DunkRandom.generateRandomWord(),
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        ""
                ))
                .collect(Collectors.toList());
    }

    private static List<XScriptModelBot> generateBots(int numBots, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numBots)
                .mapToObj(i -> new XScriptModelBot(
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        ""
                ))
                .collect(Collectors.toList());
    }

    private static List<String> generateGroupNames(int numGroups) {
        return IntStream.rangeClosed(1, numGroups)
                .mapToObj(i -> "Group" + i)
                .collect(Collectors.toList());
    }

    private static <T> T getRandomElement(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel model = generateModel("ExampleModel", "1.0.0", 10, 5, 3, 2, 2, 1);
        String formattedOutput = XScriptModelFormatter.format(model);
        System.out.println(formattedOutput);
    }
}
