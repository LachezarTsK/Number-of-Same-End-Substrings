
class Solution {

    companion object {
        const val ALPHABET_SIZE = 26
    }

    fun sameEndSubstringCount(input: String, queries: Array<IntArray>): IntArray {
        val frequency = IntArray(ALPHABET_SIZE)
        val prefixSumFrequency = Array<IntArray>(input.length + 1) { IntArray(ALPHABET_SIZE) }

        for (i in input.indices) {
            ++frequency[input[i] - 'a']
            System.arraycopy(frequency, 0, prefixSumFrequency[i + 1], 0, ALPHABET_SIZE)
        }

        val numberOfSameEndSubstrings = IntArray(queries.size)

        for (i in queries.indices) {
            val start = queries[i][0]
            val end = queries[i][1]
            numberOfSameEndSubstrings[i] = countSameEndSubstrings(start, end, prefixSumFrequency)
        }

        return numberOfSameEndSubstrings
    }

    private fun countSameEndSubstrings(start: Int, end: Int, prefixSumFrequency: Array<IntArray>): Int {
        var numberOfSameEndSubstrings = 0
        for (i in 0..<ALPHABET_SIZE) {
            val frequency = prefixSumFrequency[end + 1][i] - prefixSumFrequency[start][i]
            numberOfSameEndSubstrings += frequency * (frequency + 1) / 2
        }
        return numberOfSameEndSubstrings
    }
}
