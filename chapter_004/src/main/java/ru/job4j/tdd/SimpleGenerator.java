package ru.job4j.tdd;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for change string with spec words on aliases.
 *
 * @author agavrikov
 * @version 1
 * @since 24.08.2017
 */
public class SimpleGenerator {

    /**
     * Start spec word symbols.
     */
    private static final String START_ALIASE_PLACE = "${";

    /**
     * Finish spec word symbols.
     */
    private static final String FINISH_ALIASE_PLACE = "}";

    /**
     * Method for change spec word to aliases.
     *
     * @param value   string with spec words
     * @param aliases map with spec word in key and alias in value.
     * @return converted string from param value
     * @throws Exception if value has spec word and map has not this word in keys. Also
     *                   if map has key, but param value has not this word.
     */
    public String getGenerateString(String value, Map<String, String> aliases) throws Exception {
        Set<String> usesKeys = new HashSet<>(aliases.keySet());
        StringBuilder sb = new StringBuilder(value);
        int indexStart = sb.indexOf(START_ALIASE_PLACE);
        while (indexStart != -1) {
            int indexFinish = sb.indexOf(FINISH_ALIASE_PLACE, indexStart);
            String var = sb.substring(indexStart + START_ALIASE_PLACE.length(),
                    indexFinish);
            if (aliases.containsKey(var)) {
                if (usesKeys.contains(var)) {
                    usesKeys.remove(var);
                }
                sb.replace(indexStart, indexFinish + FINISH_ALIASE_PLACE.length(), aliases.get(var));
                indexStart = sb.indexOf(START_ALIASE_PLACE);
            } else {
                throw new Exception(String.format("Key %s not founded in current aliases map!"));
            }
        }
        if (usesKeys.size() > 0) {
            throw new Exception(String.format("Founded not used key in map!"));
        }
        return sb.toString();
    }


}
