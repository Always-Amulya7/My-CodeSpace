class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0 ;

        int n = matrix.length ;

        int min = 100001 ;

        int negCount = 0 ;

        for (int[] row : matrix)
        {
            for (int val : row)
            {
                if (val < 0)
                {
                    negCount++ ;
                    val = -val ;
                }

                if (val < min) min = val ;

                sum += val ;
            }
        }

        return negCount % 2 == 0 ? sum : sum - 2L * min ;
    }
}