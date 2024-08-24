package com.dunkware.xstream.model.script.utils;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.dunkware.utils.core.helpers.DunkRandom;
import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;

public class XScriptModelGenerator {
    private static final List<String> FORMATS = List.of("PERCENT", "CURRENCY", "TEXT", "NUMBER", "SHORT_NUMBER");
    private static final Random RANDOM = new Random();

    public static XScriptDescriptor generateModel(String modelName, String version, int numVariables, int numSignals, int numBots, int numVariableGroups, int numSignalGroups, int numBotGroups) {
        List<XScriptVariableDescriptor> variables = generateVariables(numVariables, numVariableGroups);
        List<XScriptSignalDescriptor> signals = generateSignals(numSignals, numSignalGroups);
        List<XScriptBotDescriptor> bots = generateBots(numBots, numBotGroups);

        return new XScriptDescriptor(modelName, "Generated", version, variables, signals, bots);
    }

    private static List<XScriptVariableDescriptor> generateVariables(int numVariables, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numVariables)
                .mapToObj(i -> new XScriptVariableDescriptor(
                        "",
                        i,
                        DunkRandom.generateRandomWord(),
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        getRandomElement(FORMATS)
                ))
                .collect(Collectors.toList());
    }

    private static List<XScriptSignalDescriptor> generateSignals(int numSignals, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numSignals)
                .mapToObj(i -> new XScriptSignalDescriptor(
                        i,
                        DunkRandom.generateRandomWord(),
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        ""
                ))
                .collect(Collectors.toList());
    }

    private static List<XScriptBotDescriptor> generateBots(int numBots, int numGroups) {
        List<String> groups = generateGroupNames(numGroups);
        return IntStream.rangeClosed(1, numBots)
                .mapToObj(i -> new XScriptBotDescriptor(
                		i,
                		"3.3.3",
                        DunkRandom.generateRandomWord(),
                        DunkRandom.generateRandomWord(),
                        getRandomElement(groups),
                        DunkRandom.generateRandomWord()
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
        XScriptDescriptor model = generateModel("ExampleModel", "1.0.0", 10, 5, 3, 2, 2, 1);
        String formattedOutput = XScriptModelFormatter.format(model);
        System.out.println(formattedOutput);
    }
}
