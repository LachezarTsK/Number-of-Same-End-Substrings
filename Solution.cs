
using System;

public class Solution
{
    static readonly int ALPHABET_SIZE = 26;
    public int[] SameEndSubstringCount(string input, int[][] queries)
    {
        int[] frequency = new int[ALPHABET_SIZE];
        int[][] prefixSumFrequency = new int[input.Length + 1][];
        for (int i = 0; i < input.Length + 1; ++i)
        {
            prefixSumFrequency[i] = new int[ALPHABET_SIZE];
        }

        for (int i = 0; i < input.Length; ++i)
        {
            ++frequency[input[i] - 'a'];
            Array.Copy(frequency, prefixSumFrequency[i + 1], ALPHABET_SIZE);

        }

        int[] numberOfSameEndSubstrings = new int[queries.Length];

        for (int i = 0; i < queries.Length; ++i)
        {
            int start = queries[i][0];
            int end = queries[i][1];
            numberOfSameEndSubstrings[i] = CountSameEndSubstrings(start, end, prefixSumFrequency);
        }

        return numberOfSameEndSubstrings;
    }

    private int CountSameEndSubstrings(int start, int end, int[][] prefixSumFrequency)
    {
        int numberOfSameEndSubstrings = 0;
        for (int i = 0; i < ALPHABET_SIZE; ++i)
        {
            int frequency = prefixSumFrequency[end + 1][i] - prefixSumFrequency[start][i];
            numberOfSameEndSubstrings += frequency * (frequency + 1) / 2;
        }
        return numberOfSameEndSubstrings;
    }
}
