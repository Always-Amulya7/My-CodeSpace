class Solution {

    // dp[i][j] stores the maximum dot product for nums1[0..i] and nums2[0..j]
    Integer[][] dp;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        dp = new Integer[nums1.length][nums2.length];
        return solve(nums1, nums2, nums1.length - 1, nums2.length - 1);
    }

    private int solve(int[] nums1, int[] nums2, int i, int j) {

        // Base case: if either array is exhausted
        if (i < 0 || j < 0) {
            return Integer.MIN_VALUE; // invalid (non-empty constraint)
        }

        // If already computed, reuse it
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // Option 1: take current pair
        int product = nums1[i] * nums2[j];

        // Option 2: extend a previous subsequence
        int extend = solve(nums1, nums2, i - 1, j - 1);
        int take = product;
        if (extend != Integer.MIN_VALUE) {
            take = Math.max(take, product + extend);
        }

        // Option 3: skip one element
        int skip = Math.max(
            solve(nums1, nums2, i - 1, j),
            solve(nums1, nums2, i, j - 1)
        );

        // Store and return the best result
        return dp[i][j] = Math.max(take, skip);
    }
}