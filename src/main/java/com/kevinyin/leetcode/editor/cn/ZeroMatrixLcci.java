//编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
//
// 
//
// 示例 1： 
//
// 输入：
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出：
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2： 
//
// 输入：
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出：
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//]
// 
// Related Topics 数组 哈希表 矩阵 👍 47 👎 0


package com.kevinyin.leetcode.editor.cn;

public class ZeroMatrixLcci {
    public static void main(String[] args) {
        Solution solution = new ZeroMatrixLcci().new Solution();
//        solution.setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        solution.setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[][] edit = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        if (edit[i][j]) {
                            continue;
                        }
                        edit[i][j] = true;
                        dfs(i, j, m, n, matrix, edit);
                    }
                }
            }
            System.out.println(matrix);
        }

        void dfs(int i, int j, int m, int n, int[][] matrix, boolean[][] edit) {
            for (int k = 0; k < m; k++) {
                if (edit[k][j]) {
                    continue;
                }
                edit[k][j] = true;
                if (matrix[k][j] == 0) {
                    dfs(k, j, m, n, matrix, edit);
                } else {
                    matrix[k][j] = 0;
                }
            }
            for (int k = 0; k < n; k++) {
                if (edit[i][k]) {
                    continue;
                }
                edit[i][k] = true;
                if (matrix[i][k] == 0) {
                    dfs(i, k, m, n, matrix, edit);
                } else {
                    matrix[i][k] = 0;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class MySolution {
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[] row = new boolean[m];
            boolean[] col = new boolean[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        row[i] = true;
                        col[j] = true;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (row[i] || col[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

    }


}