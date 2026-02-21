//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long currentTime1 = System.nanoTime();
        Scanner scanIntrare = new Scanner(System.in);
        int dimensiune = scanIntrare.nextInt();
        int[][] rectangleImage = new int[dimensiune][dimensiune];
        int[][] circleImage = new int[dimensiune][dimensiune];
        int[] colFrecv = new int[dimensiune];

        for(int i = 0; i < dimensiune; ++i) {
            rectangleImage[i][0] = rectangleImage[0][i] = rectangleImage[i][dimensiune - 1] = rectangleImage[dimensiune - 1][i] = 0;
        }

        for(int i = 1; i < dimensiune - 1; ++i) {
            for(int j = 1; j < dimensiune - 1; ++j) {
                rectangleImage[i][j] = 255;
            }
        }

        int cx = dimensiune / 2;
        int cy = dimensiune / 2;
        int radius = (int)((double)dimensiune * 0.45);

        for(int i = 0; i < dimensiune; ++i) {
            for(int j = 0; j < dimensiune; ++j) {
                int dx = i - cx;
                int dy = j - cy;
                if (dx * dx + dy * dy <= radius * radius) {
                    circleImage[i][j] = 255;
                } else {
                    circleImage[i][j] = 0;
                }
            }
        }

        long currentTime2 = System.nanoTime();
        if (dimensiune < 100) {
            for(int i = 0; i < dimensiune; ++i) {
                for(int j = 0; j < dimensiune; ++j) {
                    if (rectangleImage[i][j] == 0) {
                        System.out.print("@ ");
                    } else {
                        System.out.print("* ");
                    }
                }

                System.out.println();
            }

            System.out.println();
            System.out.println();

            for(int i = 0; i < dimensiune; ++i) {
                for(int j = 0; j < dimensiune; ++j) {
                    if (circleImage[i][j] == 0) {
                        System.out.print("@ ");
                    } else {
                        System.out.print("* ");
                    }
                }

                System.out.println();
            }
        } else {
            System.out.println(currentTime2 - currentTime1);
        }

    }
}
