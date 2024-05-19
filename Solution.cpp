
#include <span>
#include <array>
#include <ranges>
#include <string>
#include <vector>
using namespace std;

class Solution {

    static const int ALPHABET_SIZE = 26;
    using Frequency = array<int, ALPHABET_SIZE>;

public:
    vector<int> sameEndSubstringCount(const string& input, const vector<vector<int>>& queries)const {
        Frequency frequency{};
        vector<Frequency> prefixSumFrequency(input.size() + 1);

        for (size_t i = 0; i < input.length(); ++i) {
            ++frequency[input[i] - 'a'];
            // Alternatively, prior to C++20:
            // copy(frequency.begin(), frequency.end(), prefixSumFrequency[i + 1].begin());
            ranges::copy(frequency, prefixSumFrequency[i + 1].begin());
        }

        vector<int> numberOfSameEndSubstrings(queries.size());

        for (size_t i = 0; i < queries.size(); ++i) {
            int start = queries[i][0];
            int end = queries[i][1];
            numberOfSameEndSubstrings[i] = countSameEndSubstrings(start, end, prefixSumFrequency);
        }

        return numberOfSameEndSubstrings;
    }

private:
    int countSameEndSubstrings(int start, int end, span<const Frequency> prefixSumFrequency) const {
        int numberOfSameEndSubstrings = 0;
        for (size_t i = 0; i < ALPHABET_SIZE; ++i) {
            int frequency = prefixSumFrequency[end + 1][i] - prefixSumFrequency[start][i];
            numberOfSameEndSubstrings += frequency * (frequency + 1) / 2;
        }
        return numberOfSameEndSubstrings;
    }
};
