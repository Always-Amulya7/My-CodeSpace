class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;         // Number of rows
        int m = strs[0].length();    // Number of columns

        // isSorted[i] tracks the relationship between row i and row i+1
        // false = they are currently TIED (identical prefixes)
        // true  = they are STRICTLY SORTED (row i < row i+1)
        boolean[] isSorted = new boolean[n - 1];
        int delCnt = 0;

        // Iterate through each column from left to right
        for (int col = 0; col < m; col++) {
            boolean isColBad = false;

            // CHECK PHASE: Does this column break any existing ties?
            for (int row = 0; row < n - 1; row++) {
                // We only check pairs that are NOT yet strictly sorted
                if (!isSorted[row]) {
                    // If the top character is greater than bottom, order is invalid
                    if (strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                        isColBad = true;
                        break; // Optimization: Stop checking this column immediately
                    }
                }
            }

            // DECISION PHASE
            if (isColBad) {
                // If the column is invalid, we must "delete" it
                delCnt++;
            } else {
                // If we keep the column, we must update the state of our rows
                for (int i = 0; i < n - 1; i++) {
                    // If they were tied, but this column breaks the tie (top < bottom)...
                    if (!isSorted[i] && strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                        isSorted[i] = true; // Mark as strictly sorted (SAFE)
                    }
                }
            }
        }
        return delCnt;
    }
}