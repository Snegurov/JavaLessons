public class Main {

    public static double CalcExpression(double a, double b, double c, double d){
        return a * (b + (c / d));
    }

    public static boolean CheckSum(int a, int b){
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static void PrintNumPositiveStatus(int num){
        System.out.println(num >= 0? "положительное" : "отрицательное");
    }

    public static boolean IsNegative(int num) {
        return num < 0;
    }

    public static void PrintHelloName(String sName){
        System.out.println("Привет, " + sName + '!');
    }

    public static void PrintIsLeapYear(long nYear){
        String sText = (nYear % 4 == 0 && nYear % 100 != 0) || nYear % 400 == 0? "" : " не";
        System.out.println(nYear + " год" + sText + " является високосным");
    }

    public static void main(String[] args) {
        byte nByte = 127;
        short nShort = 32000;
        int nInt = 100500;
        long nLong = 100500100500L;

        float dFloat = 1.077f;
        double dDouble = 1.333333777;

        boolean bBool = true;
        char c = 'z';

        String str = "Hi, peoples!";

        System.out.println("CalcExpression: " + CalcExpression(dDouble, 3.14, 2.71, Math.log(2)));
        System.out.println("CheckSum(15, 5): " + CheckSum(15, 5));
        System.out.println("CheckSum(26, -5): " + CheckSum(26, -5));
        PrintNumPositiveStatus(0);
        PrintNumPositiveStatus(1);
        PrintNumPositiveStatus(-1);
        PrintHelloName("Antonio Banderos");
        PrintIsLeapYear(2000);
        PrintIsLeapYear(1999);
        PrintIsLeapYear(2016);
    }
}
