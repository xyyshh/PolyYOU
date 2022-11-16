package comp4342.android.polyyou.utils;

public class Str {

    public static boolean isValidEmail(String email) {
        if(email.length() < 17)
            return false;
        String email_end = email.substring(email.length() - 17);
        if(email_end.equals("@connect.polyu.hk"))
            return true;
        return false;
    }

    public static boolean isValidPassword(String password) {
        if(password.length()>16 || password.length() < 8)
            return false;
        boolean hasNum = false;
        boolean hasChar = false;
        for(int i=0;i<password.length();i++){
            int s = (int)password.charAt(i);
            if(!((s>=48&&s<=57) || (s>=65&&s<=90) || (s>=97&&s<=122)))
                return false;
            if(s>=48&&s<=57)    hasNum = true;
            if((s>=65&&s<=90) || (s>=97&&s<=122)) hasChar = true;
        }
        if(!hasNum || !hasChar)
            return false;
        return true;
    }
}
