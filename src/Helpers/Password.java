package Helpers;

public class Password {

    String password;
    char policyChar;
    int policyVal1;
    int policyVal2;

    public Password(String policyPassword) {
        String strPolicyMin = "";
        String strPolicyMax = "";
        String strPolicyChar = "";
        password = "";

        int i = 0;
        while(policyPassword.charAt(i) != '-') {
            strPolicyMin += policyPassword.charAt(i);
            i++;
        }
        i++;
        while(policyPassword.charAt(i) != ' ') {
            strPolicyMax += policyPassword.charAt(i);
            i++;
        }
        i++;
        while(policyPassword.charAt(i) != ':') {
            strPolicyChar += policyPassword.charAt(i);
            i++;
        }
        i+=2;
        while(i < policyPassword.length()) {
            password += policyPassword.charAt(i);
            i++;
        }

        policyVal1 = Integer.parseInt(strPolicyMin);
        policyVal2 = Integer.parseInt(strPolicyMax);
        policyChar = strPolicyChar.charAt(0);
    }

    public boolean isValid(int policyType) {
        if(policyType == 0) return isValid0();
        else if (policyType == 1) return isValid1();
        return false;
    }

    private boolean isValid0() {
        int charCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == policyChar) {
                charCount++;
            }
        }
        if (!(policyVal1 <= charCount && charCount <= policyVal2)) {
            return false;
        }
        return true;
    }

    private boolean isValid1() {
        if(password.charAt(policyVal1 - 1) == policyChar ^ password.charAt(policyVal2 - 1) == policyChar) {
            return true;
        }
        return false;
    }
}
