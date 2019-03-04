package ru.geekbrains.homework2;

import java.util.Arrays;
import java.util.Random;

public class Main {

    /* 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
      метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
      Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
      граница показана символами ||, эти символы в массив не входят. */

    public static boolean checkBalance(int[] arr){
        if(arr.length <= 1)
            return false;

        long nSumL = arr[0], nSumR = 0;

        for(int i = 1; i < arr.length; ++i)
            nSumR += arr[i];

        int i = 1;

        do {
            if(nSumR == nSumL)
                return true;

            if(i + 1 == arr.length)
                break;

            nSumL += arr[i];
            nSumR -= arr[i];

            ++i;
        } while(true);

        return false;
    }

    /* 7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или
          отрицательным), при этом метод должен сместить все элементы массива на n позиций. Для усложнения задачи нельзя
          пользоваться вспомогательными массивами. */

    public static void ShiftArray(int[] arr, int n){
        if(arr.length <= 1 || n == 0)
            return;

        int nOffset = n % arr.length;

        if(nOffset == 0)
            return;

        if(nOffset < 0)
            nOffset += arr.length;

        for(int k = 0, nMoved = 0; nMoved < arr.length; ++k){

            int i = k;
            int nNewInd = (i + nOffset) % arr.length;
            int nTmpF = arr[i];
            int nTmpS = arr[nNewInd];

            do {
                arr[nNewInd] = nTmpF;
                ++nMoved;

                if(nNewInd == k)
                    break;

                i = nNewInd;
                nNewInd = (i + nOffset) % arr.length;
                nTmpF = nTmpS;
                nTmpS = arr[nNewInd];
            } while(true);
        }
    }

    public static void main(String[] args) {

        /* 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
              С помощью цикла и условия заменить 0 на 1, 1 на 0 */

	    int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

	    for(int i = 0; i < arr.length; ++i)
            arr[i] = (arr[i] == 1)? 0 : 1;

        /* 2. Задать пустой целочисленный массив размером 8.
              С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21; */

        int[] arr2 = new int[8];

        for(int i = 0; i < arr2.length; ++i)
            arr2[i] = i * 3;

        /* 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
              и числа меньшие 6 умножить на 2; */

        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

        for(int i = 0; i < arr3.length; ++i) {
            if (arr3[i] < 6)
                arr3[i] *= 2;
        }

        /* 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
              и с помощью цикла(-ов) заполнить его диагональные элементы единицами; */

        int[][] arr4 = new int[5][5];

        for(int i = 0; i < arr4.length; ++i) {
            arr4[i][i] = 1;
            arr4[i][arr4.length - i - 1] = 1;
        }

        /* 5.** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета) */

        int[] arr5 = { 9, 5, 7, 5, 6, -8, -7, 6, 58, 7, 6, 9, -1, 7, 59, -10, 59, 65, 87, -61, -70, 9, 48, -9, 0, 17,
                       5, 97, -6, 58, 76, -19, 5, 87 };

        int nMax = arr5[0]; // инициализируем значения nMax и nMin первым элементом
        int nMin = arr5[0];

        for(int i = 1; i < arr5.length; ++i){
            if(arr5[i] < nMin)
                nMin = arr5[i];
            else if(arr5[i] > nMax)
                nMax = arr5[i];
        }

        /* Создайте массив из всех нечётных чисел от 1 до 99, выведите его на экран в строку,
            (заполнить массив с помощью цикла) */

        int[] arr6 = new int[50];
        for(int i = 0; i < arr6.length; ++i)
            arr6[i] = i + i + 1;

        System.out.println(Arrays.toString(arr6));

        /* Создайте массив из 15 случайных целых чисел из отрезка [0;9]. Выведите массив на экран. Подсчитайте
            сколько в массиве чётных элементов и выведете это количество на экран на отдельной строке. */

        int[] arr7 = new int[15];
        int nEvens = 0;

        for(int i = 0; i < arr7.length; ++i){
            arr7[i] = (int)(Math.random() * 10.0);

            if(arr7[i] % 2 == 0)
                ++nEvens;
        }

        System.out.println(Arrays.toString(arr7));
        System.out.println(nEvens);

        /* Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый, выведите массивы на экран в двух
            отдельных строках. Посчитайте среднее арифметическое элементов каждого массива и сообщите, для какого
            из массивов это значение оказалось больше (либо сообщите, что их средние арифметические равны). */

        int[] mas1 = new int[5];
        int[] mas2 = new int[5];
        double dAvg1 = 0, dAvg2 = 0;

        for(int i = 0; i < mas1.length; ++i){
            mas1[i] = (int)(Math.random() * 6.0);
            mas2[i] = (int)(Math.random() * 6.0);
            dAvg1 += mas1[i];
            dAvg2 += mas2[i];
        }

        dAvg1 /= 5.0;  // на самом деле для сравнения можно не делить и было бы проще, но по заданию нужно посчитать
        dAvg2 /= 5.0;

        System.out.println(Arrays.toString(mas1));
        System.out.println(Arrays.toString(mas2));

        double dEps = 1e-7;
        if(Math.abs(dAvg1 - dAvg2) < dEps)
            System.out.println("Средние арифметические массивов равны");
        else
            System.out.println("Среднее арифметическое массива " + (dAvg1 > dAvg2? 1 : 2) + " больше");
    }
}
