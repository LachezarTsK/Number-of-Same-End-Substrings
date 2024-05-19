
function sameEndSubstringCount(input: string, queries: number[][]): number[] {
    this.ALPHABET_SIZE = 26;
    const frequency: number[] = new Array(this.ALPHABET_SIZE).fill(0);
    const prefixSumFrequency: number[][] = Array.from(new Array(input.length + 1),
        () => new Array(this.ALPHABET_SIZE).fill(0));

    for (let i = 0; i < input.length; ++i) {
        ++frequency[getASCII(input.charAt(i))];
        prefixSumFrequency[i + 1] = [...frequency];
    }

    const numberOfSameEndSubstrings = new Array(queries.length);

    for (let i = 0; i < queries.length; ++i) {
        const start = queries[i][0];
        const end = queries[i][1];
        numberOfSameEndSubstrings[i] = countSameEndSubstrings(start, end, prefixSumFrequency);
    }

    return numberOfSameEndSubstrings;
};

function countSameEndSubstrings(start: number, end: number, prefixSumFrequency: number[][]): number {
    let numberOfSameEndSubstrings = 0;
    for (let i = 0; i < this.ALPHABET_SIZE; ++i) {
        let frequency = prefixSumFrequency[end + 1][i] - prefixSumFrequency[start][i];
        numberOfSameEndSubstrings += Math.floor(frequency * (frequency + 1) / 2);
    }
    return numberOfSameEndSubstrings;
}

function getASCII(letter: string): number {
    return letter.codePointAt(0) - 'a'.codePointAt(0);
}
