import java.util.Scanner;

public class HomeWork3 {

    public static class Calc {

        private boolean isValidInteger(String sStr){
            if(sStr == null)
                return false;

            try
            {
                Integer.parseInt(sStr);
            }
            catch (NumberFormatException e)
            {
                return false;
            }

            return true;
        }

        private boolean isValidOperation(String sStr){
            if(sStr == null)
                return false;

            switch(sStr){
                case "+":
                case "-":
                case "*":
                case "/":
                    return true;
            }

            return false;
        }

        private boolean isValidExpression(String[] sTokens){
            if(sTokens.length == 0)
                return true;

            if(!isValidInteger(sTokens[0]))
                return false;

            for(int i = 1; i < sTokens.length; i += 2){
                if(!isValidOperation(sTokens[i]) || i + 1 == sTokens.length || !isValidInteger(sTokens[i+1]))
                    return false;
            }

            return true;
        }

        public void consoleCalc() {
            Scanner in = new Scanner(System.in);
            String[] line = in.nextLine().split(" ");

            if(!isValidExpression(line)){
                System.out.println("Выражение некорректно и содержит ошибки");
                return;
            }

            int nRes = 0;

            if(line.length == 0){
                System.out.println("Результат: " + nRes);
                return;
            }

            nRes = Integer.parseInt(line[0]);

            for(int i = 1; i < line.length; i += 2) {
                int nRight = Integer.parseInt(line[i+1]);

                switch (line[i]) {
                    case "+":
                        nRes += nRight;
                        break;
                    case "-":
                        nRes -= nRight;
                        break;
                    case "*":
                        nRes *= nRight;
                        break;
                    case "/":
                        nRes /= nRight;
                }
            }

            System.out.println("Результат: " + nRes);
            in.close();
        }
    }

    /* 1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать
        это число. При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное,
        или меньше. После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»
        (1 – повторить, 0 – нет). */

    public static int readIntFromScanner(Scanner scan){
        if(scan == null)
            return Integer.MIN_VALUE;

        for(; !scan.hasNextInt(); scan.next());
        return scan.nextInt();
    }

    public static void guessNumber(int nMaxAttempts) {
        Scanner scan = new Scanner(System.in);
        int nUserAnswer = 0;

        do {
            System.out.println("Игра \"Угадай число\"");

            int nNum = (int)(Math.random() * 10.0);
            int i = 0;

            for (; i < nMaxAttempts; ++i) {
                System.out.println("Попытка №" + (i + 1));

                do {
                    System.out.println("Введите число от 0 до 9:");

                    nUserAnswer = readIntFromScanner(scan);
                    if(nUserAnswer >= 0 && nUserAnswer <= 9)
                        break;

                    System.out.print("Вы ввели некорректное значение. ");
                }
                while(true);

                if(nUserAnswer > nNum)
                    System.out.println("Больше!");
                else if(nUserAnswer < nNum)
                    System.out.println("Меньше!");
                else {
                    System.out.println("Поздравляем! Вы угадали, это число " + nNum + "!");
                    break;
                }
            }

            if(i == nMaxAttempts)
                System.out.println("К сожалению вы проиграли, это было число " + nNum + "!");

            do {
                System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");

                nUserAnswer = readIntFromScanner(scan);
                if (nUserAnswer == 0 || nUserAnswer == 1)
                    break;

                System.out.print("Вы ввели некорректное значение. ");
            }
            while(true);
        }
        while(nUserAnswer == 1);

        System.out.println("До свидания!");
        scan.close();
    }

    /* 2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
        "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
        "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным
        словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые
        стоят на своих местах. Играем до тех пор, пока игрок не отгадает слово. Используем только маленькие буквы */

    public static void guessWord(){
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};

        Scanner scan = new Scanner(System.in);
        int nWordIndex = (int)(Math.random() * words.length);
        StringBuilder sbOutMask = new StringBuilder();

        do{
            String sUserAnswer = "";

            System.out.println("Введите слово:");

            for(; sUserAnswer == null || sUserAnswer.isEmpty(); sUserAnswer = scan.hasNext()? scan.next() : "");

            if(sUserAnswer.equals(words[nWordIndex])) {
                System.out.println("Поздравляем! Вы угадали слово " + words[nWordIndex] + "!");
                break;
            }

            int i = 0;
            for(int nLen = Math.min(words[nWordIndex].length(), sUserAnswer.length()); i < nLen; ++i){
                if(words[nWordIndex].charAt(i) == sUserAnswer.charAt(i))
                    sbOutMask.append(sUserAnswer.charAt(i));
                else
                    sbOutMask.append('#');
            }

            for(; i < 15; ++i)
                sbOutMask.append('#');

            System.out.println("Слово не угадано, проверьте совпавшие буквы и попробуйте снова");
            System.out.println(sbOutMask);

            sbOutMask.delete(0, sbOutMask.length());
        }
        while(true);

        scan.close();
    }

    public static Calc calc = new Calc();

    public static void main(String[] args) {
        //guessWord();
        //guessNumber(3);
        //calc.consoleCalc();
    }
}
