package DoubleCompare;

public class DoubleCmp {
    private static double DEF_EPSILON = 1e-10;

    public static boolean isZero(double x, double eps){
        return Math.abs(x) < eps;
    }

    public static boolean isZero(double x){
        return isZero(x, DEF_EPSILON);
    }

    public static boolean isEqual(double x, double y, double eps){
        return Math.abs(x - y) < eps;
    }

    public static boolean isEqual(double x, double y){
        return isEqual(x, y, DEF_EPSILON);
    }

    public static boolean isGreater(double x, double y, double eps){
        if(isEqual(x, y, eps))
            return false;

        return x > y;
    }

    public static boolean isGreater(double x, double y){
        return isGreater(x, y, DEF_EPSILON);
    }

    public static boolean isLess(double x, double y, double eps){
        if(isEqual(x, y, eps))
            return false;

        return x < y;
    }

    public static boolean isLess(double x, double y){
        return isLess(x, y, DEF_EPSILON);
    }

    public static boolean isGreaterOrEqual(double x, double y, double eps){
        return !isLess(x, y, eps);
    }

    public static boolean isGreaterOrEqual(double x, double y){
        return isGreaterOrEqual(x, y, DEF_EPSILON);
    }

    public static boolean isLessOrEqual(double x, double y, double eps){
        return !isGreater(x, y, eps);
    }

    public static boolean isLessOrEqual(double x, double y){
        return isLessOrEqual(x, y, DEF_EPSILON);
    }
}
