import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Challenge {

    private static Map<String, Integer> originalIndexMap;
    private static Map<String, ArrayList<String>> map;

    public static String sortCsvColumns( String csv_data ) {
            map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            List<String> inputLines = Arrays.asList(csv_data.split("\n"));
            insertKeys(inputLines.get(0));

            List<String> outputLines = new ArrayList<>();
            outputLines.add(String.join(",", originalIndexMap.keySet()));

            for (int i = 1; i < inputLines.size(); i++) {
                List<String> values = Arrays.asList(inputLines.get(i)
                                                              .split(",", -1));
                values = rearrange(values);
                String line = String.join(",", values);
                outputLines.add(line);
            }

            String output = String.join("\n", outputLines);
            return output;
    }

    private static List<String> rearrange(List<String> values) {
        List<String> reArrangedValues = new ArrayList<>();
        for (String header : map.keySet()) {
            int index = originalIndexMap.get(header);
            reArrangedValues.add(values.get(index));
        }
        return reArrangedValues;
    }

    private static void insertKeys(String firstLine) {
        originalIndexMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        String[] headers = firstLine.split(",", -1);
        int i = 0;
        for(String header: headers) {
            map.put(header, new ArrayList<String>());
            originalIndexMap.put(header,i++);
        }
    }
}
