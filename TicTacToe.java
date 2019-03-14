import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static int SIZE_X = 6;
    static int SIZE_Y = 5;
    static int WIN_SERIES = 4;
    static int MAX_DEEP_LEVEL = 3;
    static int MAX_ELEMENTS_VIEW = 5;

    static char[][] field = new char[SIZE_Y][SIZE_X];

    static char PLAYER_DOT = 'X';
    static char AI_DOT = 'O';
    static char EMPTY_DOT = '.';

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // заполнить поле
    private static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // метод для вывода на консоль поля
    private static void printField() {
        System.out.println("-------");
        System.out.print("__|");

        for (int j = 0; j < SIZE_X; ++j) {
            System.out.print((j + 1) + " ");
        }

        System.out.println();

        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    // метод для установки символа
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // проверка валидности ячейки
    private static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }

    private static void doRandomStep(char sym) {
        int x, y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        }
        while(!isCellValid(y,x));
        setSym(y, x, sym);
    }

    // ход человека
    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X (1 - " + SIZE_X + "), Y (1 - " + SIZE_Y + ")");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y, x, PLAYER_DOT);

        //doBestStep_v2(PLAYER_DOT); // для проверки игры ИИ против другого ИИ
    }

    // ход ПК
    private static void aiStep() {
        // doBestStep(AI_DOT);
        doBestStep_v2(AI_DOT);
    }

    /*
     * если не встретили пустую ячейку
     * это значит что все поле заполнено
     * */
    private static boolean isDraw() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if(field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // проверка победы для квадратного поля и полного кол-ва фишек
    private static boolean checkWinOld(char sym) {
        int i, j;

        for(i = 0; i < SIZE_X; ++i){
            for(j = 0; j < SIZE_Y && field[j][i] == sym; ++j);

            if(j == SIZE_Y)
                return true;
        }

        for(j = 0; j < SIZE_Y; ++j){
            for(i = 0; i < SIZE_X && field[j][i] == sym; ++i);

            if(i == SIZE_X)
                return true;
        }

        for(i = 0; i < SIZE_X && field[i][i] == sym; ++i);

        if(i == SIZE_X)
            return true;

        for(i = 0; i < SIZE_X && field[i][SIZE_X - i - 1] == sym; ++i);

        return i == SIZE_X;
    }

    private static boolean checkVertLine(int x, int y, int nWinSeries, char sym){
        int count = 0;
        for (int j = y; j < SIZE_Y && field[j][x] == sym; ++j){
            if(++count == nWinSeries)
                return true;
        }

        for (--y; y >= 0 && field[y][x] == sym; --y){
            if(++count == nWinSeries)
                return true;
        }

        return false;
    }

    private static boolean checkHorzLine(int x, int y, int nWinSeries, char sym) {
        int count = 0;
        for (int i = x; i < SIZE_X && field[y][i] == sym; ++i) {
            if(++count == nWinSeries)
                return true;
        }

        for (--x; x >= 0 && field[y][x] == sym; --x) {
            if(++count == nWinSeries)
                return true;
        }

        return false;
    }

    private static boolean checkDiagLine(int x, int y, int nWinSeries, char sym) {
        int count = 0;
        for(int i = x, j = y; i < SIZE_X && j < SIZE_Y && field[j][i] == sym; ++i, ++j) {
           if(++count == nWinSeries)
               return true;
        }

        for(--x, --y; x >= 0 && y >= 0 && field[y][x] == sym; --x, --y) {
            if(++count == nWinSeries)
                return true;
        }

        return false;
    }

    private static boolean checkInvDiagLine(int x, int y, int nWinSeries, char sym) {
        int count = 0;
        for (int i = x, j = y; i < SIZE_X && j >= 0 && field[j][i] == sym; ++i, --j) {
            if(++count == nWinSeries)
                return true;
        }

        for (--x, ++y; x >= 0 && y < SIZE_Y && field[y][x] == sym; --x, ++y) {
            if(++count == nWinSeries)
                return true;
        }

        return false;
    }

    private static boolean checkWinInCell(int x, int y, int nWinSeries, char sym){
        if(field[y][x] != sym)
            return false;

        if(checkVertLine(x, y, nWinSeries, sym))
            return true;

        if(checkHorzLine(x, y, nWinSeries, sym))
            return true;

        if(checkDiagLine(x, y, nWinSeries, sym))
            return true;

        return checkInvDiagLine(x, y, nWinSeries, sym);
    }

    private static boolean checkWin(char sym, int nWinSeries) {
        if(nWinSeries <= 0)
            return true;

        for(int y = 0; y < SIZE_Y; ++y){
            for(int x = 0; x < SIZE_X; ++x){
                if(checkWinInCell(x, y, nWinSeries, sym))
                    return true;
            }
        }

        return false;
    }

    private static boolean findWinStepInCell(int x, int y, char sym, char sym_to_set, int nWinSeries) {
        if (!isCellValid(y, x))
            return false;

        setSym(y, x, sym);

        if(checkWinInCell(x, y, nWinSeries, sym)) {
            setSym(y, x, sym_to_set);
            return true;
        }

        setSym(y, x, EMPTY_DOT);
        return false;
    }

    private static boolean findWinStep(char sym, char sym_to_set, int nWinSeries){
        for(int y = 0; y < SIZE_Y; ++y) {
            for (int x = 0; x < SIZE_X; ++x) {
                if(findWinStepInCell(x, y, sym, sym_to_set, nWinSeries))
                    return true;
            }
        }

        return false;
    }

    private static void doBestStep(char sym) {
        doBestStepImpl(sym, WIN_SERIES, 3);
    }

    private static void doBestStepImpl(char sym, int nWinSeries, int nDeepLevel) {
        if (nDeepLevel < 0 || nWinSeries <= 1) {
            doRandomStep(sym);
            return;
        }

        if(findWinStep(sym, sym, nWinSeries))    // ищем победный ход
            return;

        if(findWinStep(sym == AI_DOT? PLAYER_DOT : AI_DOT, sym, nWinSeries))   // мешаем победить
            return;

        doBestStepImpl(sym, nWinSeries - 1, nDeepLevel - 1);
    }

    private static void doBestStep_v2(char sym) {
        if(WIN_SERIES <= 1){
            doRandomStep(sym);
            return;
        }

        if(findWinStep(sym, sym, WIN_SERIES))    // ищем победный ход
            return;

        char symRival = sym == AI_DOT? PLAYER_DOT : AI_DOT;

        if(findWinStep(symRival, sym, WIN_SERIES))  // ищем победный ход соперника
            return;

        int nMaxDeep = Math.min(WIN_SERIES - 1, MAX_DEEP_LEVEL);

        for(int deep = 1; deep <= nMaxDeep; ++deep){
            if(findWinStepRecursive(symRival, sym, deep))   // мешаем сопернику
                return;
        }

        for(int deep = 1; deep <= nMaxDeep; ++deep){
            if(findWinStepRecursive(sym, sym, deep))   // ищем хороший ход
                return;
        }

        doRandomStep(sym);
    }

    private static boolean findWinStepRecursive(char sym, char sym_to_set, int nDeepLevel){
        for(int y = 0; y < SIZE_Y; ++y) {
            for (int x = 0; x < SIZE_X; ++x) {

                if (field[y][x] != sym)
                    continue;

                if(doBestStepImpl_v2(x, y, sym, sym_to_set, nDeepLevel))
                    return true;
            }
        }

        return false;
    }

    private static boolean doBestStepImpl_v2(int x, int y, char sym, char sym_to_set, int nDeepLevel) {
        if(nDeepLevel <= 0)
            return false;

        int nMaxViewElems = Math.min(WIN_SERIES, MAX_ELEMENTS_VIEW);

        for(int j = y - nMaxViewElems; j <= y + nMaxViewElems; ++j) {
            for(int i = x - nMaxViewElems; i <= x + nMaxViewElems; ++i) {
                if(findWinStepInCell(i, j, sym, sym_to_set, WIN_SERIES))
                    return true;
             }
        }

        for(int j = y - nMaxViewElems; j <= y + nMaxViewElems; ++j) {
            for (int i = x - nMaxViewElems; i <= x + nMaxViewElems; ++i) {

                if (!isCellValid(j, i))
                    continue;

                setSym(j, i, sym);

                if(doBestStepImpl_v2(i, j, sym, sym_to_set, nDeepLevel - 1)) {
                    setSym(j, i, EMPTY_DOT);
                    return true;
                }

                setSym(j, i, EMPTY_DOT);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT, WIN_SERIES)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT, WIN_SERIES)) {
                System.out.println("SKYNET WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }
        }
    }
}
