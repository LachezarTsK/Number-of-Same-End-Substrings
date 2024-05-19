
public class Solution {

    private static final int ALPHABET_SIZE = 26;

    public int[] sameEndSubstringCount(String input, int[][] queries) {
        int[] frequency = new int[ALPHABET_SIZE];
        int[][] prefixSumFrequency = new int[input.length() + 1][ALPHABET_SIZE];

        for (int i = 0; i < input.length(); ++i) {
            ++frequency[input.charAt(i) - 'a'];
            System.arraycopy(frequency, 0, prefixSumFrequency[i + 1], 0, ALPHABET_SIZE);
        }

        int[] numberOfSameEndSubstrings = new int[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            int start = queries[i][0];
            int end = queries[i][1];
            numberOfSameEndSubstrings[i] = countSameEndSubstrings(start, end, prefixSumFrequency);
        }

        return numberOfSameEndSubstrings;
    }

    private int countSameEndSubstrings(int start, int end, int[][] prefixSumFrequency) {
        int numberOfSameEndSubstrings = 0;
        for (int i = 0; i < ALPHABET_SIZE; ++i) {
            int frequency = prefixSumFrequency[end + 1][i] - prefixSumFrequency[start][i];
            numberOfSameEndSubstrings += frequency * (frequency + 1) / 2;
        }
        return numberOfSameEndSubstrings;
    }
}
