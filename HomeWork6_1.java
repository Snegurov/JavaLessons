public class HomeWork6_1 {

    public static String normalizeSentence(String s){
        if(s.length() == 0)
            return "";

        String s1 = s.replaceAll(" +", " ");
        StringBuilder s2 = new StringBuilder(s1);

        if(s2.charAt(0) == ' ') {
            s2.deleteCharAt(0);

            if(s2.length() == 0)
                return "";
        }

        s2.setCharAt(0, Character.toUpperCase(s2.charAt(0)));

        for (int i = 1; i < s2.length(); ++i) {
            if(!Character.isUpperCase(s2.charAt(i)))
                continue;

            if(s2.charAt(i - 1) == ' ') {
                if(s2.charAt(i - 2) == '.')
                    continue;

                s2.insert(i - 1, '.');
                ++i;
            }
            else if(s2.charAt(i - 1) == '.'){
                s2.insert(i, ' ');
                ++i;
            }
        }

        if(s2.charAt(s2.length() - 1) == ' ') {
            s2.deleteCharAt(s2.length() - 1);
        }

        if(s2.length() > 0 && s2.charAt(s2.length() - 1) != '.')
            s2.append('.');

        return s2.toString().replaceAll(" +\\.", "\\.");
    }

    public static void main(String[] args) {
        System.out.println(normalizeSentence(""));
        System.out.println(normalizeSentence(" "));
        System.out.println(normalizeSentence("  "));
        System.out.println(normalizeSentence("п"));
        System.out.println(normalizeSentence("П"));
        System.out.println(normalizeSentence(" п "));
        System.out.println(normalizeSentence(" П "));
        System.out.println(normalizeSentence(" п р. С"));
        System.out.println(normalizeSentence(" П р. С ."));
        System.out.println(normalizeSentence("  Предложение  один     Теперь предложение    два   Предложение   три  "));
        System.out.println(normalizeSentence("  Предложение  один     .Теперь предложение    два   Предложение   три ."));
		System.out.println(normalizeSentence(normalizeSentence("  Предложение  один     .Теперь предложение    два   Предложение   три .")));
    }
}
