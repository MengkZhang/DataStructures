package com.zhang;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0：表示没有棋子，1：表示黑子 2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1.遍历二维数组 得到非0的数据个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("二维数组中非0的数据个数sum=" + sum);

        //2，创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组 将非0数据存放到稀疏数组中
        //sparseArr[?][0] = i;第1列为i
        //sparseArr[?][1] = i;第二列为j
        //sparseArr[?][2] = chessArr1[i][j];第三列为它本身

        //count记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            //输出所在行的第一列 第二列 第三列
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }

        //将稀疏数组恢复成原始的二维数组
        //1，先读取稀疏数组的第一行 根据第一行的数据 创建原始的二维数组  比如上面的 chessArr2 = int[11][11];
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2,再读取稀疏数组后面几行的数据（从第二行开始读取） 并赋值给二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            //稀疏数组的第i行第1列是二维数组的行i
            //稀疏数组的第i行第2列是二维数组的列j
            //该二维数组的元素值是稀疏数组第i行第3列
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组为~~~~~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
