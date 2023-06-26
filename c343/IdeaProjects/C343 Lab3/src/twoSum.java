public class twoSum{

        public static int[] twoSum(int target, int[] intArr) {

            int[][] map = new int[intArr.length][2];

            int sums[][] = new int[intArr.length][2];

            for (int i = 0; i < intArr.length; i++) {
                for (int j = 0; j < intArr.length; i++) {
                    if (intArr[i] + intArr[j] == target) {

                        int sum[] = new int[2];
                        sum[0] = i;
                        sum[1] = j;

                        sums[sums.length] = sum;
                        return sum;
                    }
                }
            }

            return sums[0];
        }
}

