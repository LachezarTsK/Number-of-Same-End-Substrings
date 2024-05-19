
package main

import (
	"fmt"
)

var ALPHABET_SIZE = 26

func sameEndSubstringCount(input string, queries [][]int) []int {
	frequency := make([]int, ALPHABET_SIZE)
	var prefixSumFrequency = make([][]int, len(input)+1)
	for i := 0; i < len(prefixSumFrequency); i++ {
		prefixSumFrequency[i] = make([]int, ALPHABET_SIZE)
	}
	for i := range input {
		frequency[input[i]-'a']++
		copy(prefixSumFrequency[i+1], frequency)
	}

	var numberOfSameEndSubstrings = make([]int, len(queries))

	for i := range queries {
		var start = queries[i][0]
		var end = queries[i][1]
		numberOfSameEndSubstrings[i] = countSameEndSubstrings(start, end, &prefixSumFrequency)
	}

	return numberOfSameEndSubstrings
}

func countSameEndSubstrings(start int, end int, prefixSumFrequency *[][]int) int {
	var numberOfSameEndSubstrings = 0
	for i := 0; i < ALPHABET_SIZE; i++ {
		frequency := (*prefixSumFrequency)[end+1][i] - (*prefixSumFrequency)[start][i]
		numberOfSameEndSubstrings += frequency * (frequency + 1) / 2
	}
	return numberOfSameEndSubstrings
}
