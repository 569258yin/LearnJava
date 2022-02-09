package com.kevinyin.algorithm.matrix;

/**
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 *
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/7 09:19
 */
public class MatrixSetZeroes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {
                        0, 1, 2, 0
                }, {
                3, 4, 5, 2
        }, {
                1, 3, 1, 5
        }
        };
        new MatrixSetZeroes().setZeroes(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println();
        }
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        setZero(matrix, 0, 0, 0, 0, m, n);
    }


    public void setZero(int[][] matrix, int x, int y, int startX, int startY, int m, int n) {
        if (x > m && y > n) {
            return;
        }
        boolean serZero = false;
        if (matrix[x][y] == 0) {
            serZero = true;
            for (int i = startX; i < matrix.length; i++) {
                int[] line = matrix[i];
                for (int j = startY; j < line.length; j++) {
                    if (i == x || y == j) {
                        matrix[i][j] = 0;
                    }
                }
            }

        }
        x++;
        y++;
        if (x > m && y <= n) {
            x = m;
        }
        if (y > n && x <= m) {
            y = n;
        }
        if (serZero) {
            startX = x;
            startY = y;
        }
        setZero(matrix, x, y, startX, startY, m, n);
    }


}
