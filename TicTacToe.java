import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static int SIZE_X = 3;
    static int SIZE_Y = 3;

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
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
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

    // ход человека
    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1 - 3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y, x, PLAYER_DOT);
    }

    // ход ПК
    private static void aiStep() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCellValid(y,x));
        setSym(y, x, AI_DOT);
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

    // проверка победы для квадратного поля
    private static boolean checkWin(char sym) {
        int i, j;
        for(i = 0; i < SIZE_X; ++i){
            for(j = 0; j < SIZE_Y; ++j) {
                if (field[j][i] != sym)
                    break;
            }

            if(j == SIZE_Y)
                return true;
        }

        for(j = 0; j < SIZE_Y; ++j){
            for(i = 0; i < SIZE_X; ++i) {
                if (field[j][i] != sym)
                    break;
            }

            if(i == SIZE_X)
                return true;
        }

        for(i = 0; i < SIZE_X; ++i){
            if(field[i][i] != sym)
                break;
        }

        if(i == SIZE_X)
            return true;

        for(i = 0; i < SIZE_X; ++i){
            if(field[i][SIZE_X - i - 1] != sym)
                break;
        }

        return i == SIZE_X;
    }

    // проверка победы для квадратного поля nxn (n>=m) с победой при m символах подряд
    private static boolean checkWin(char sym, int m) {
        int i, j;
        for(i = 0; i < SIZE_X; ++i){
            int cnt = 0;
            for(j = 0; j < SIZE_Y && cnt < m; ++j, ++cnt){
                if (field[j][i] != sym){
                    cnt = 0;
                }
            }

            if(cnt == m)
                return true;
        }

        for(j = 0; j < SIZE_Y; ++j){
            int cnt = 0;
            for(i = 0; i < SIZE_X && cnt < m; ++i, ++cnt) {
                if (field[j][i] != sym)
                    cnt = 0;
            }

            if(cnt == m)
                return true;
        }

        int cnt = 0;
        for(i = 0; i < SIZE_X && cnt < m; ++i, ++cnt){
            if(field[i][i] != sym)
                cnt =0;
        }

        if(cnt == m)
            return true;

        cnt = 0;
        for(i = 0; i < SIZE_X && cnt < m; ++i, ++cnt){
            if(field[i][SIZE_X - i - 1] != sym)
                cnt = 0;
        }

        return cnt == m;
    }

    public static void main(String[] args) {
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT)) {
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
