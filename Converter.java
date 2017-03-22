
public class Converter {
    public static void main(String[] args) {
        String input     = args[0];
        String[] numbers = input.split("\\.");
        int intgr        = Integer.parseInt(numbers[0]);
        String resInt    = "0";
        int fraction     = Integer.parseInt(numbers[1]);
        String resFrac   = "";
        if (intgr  > 0) {
            resInt = convertInteger(intgr);
        }

        if (fraction > 0) {
            resFrac = convertFraction(fraction, 1);
        }

        System.out.println(resInt);

        String result = resInt;
        if (resFrac != "") {
            result = result + "." + resFrac;
        }   

        System.out.println("mantisa:" + result);
    }

    public static String convertInteger(int number) {
        if (number == 1) {
            return "1";
        }

        String result = "";
        int mod       = number % 2;
        int nextValue = (number - mod) / 2;

        return convertInteger(nextValue) + Integer.toString(mod);
    }

    public static String convertFraction(int number, int quantityZero) {
        int lenghtOfNumber = log10(number) + 1;
        int maxLength  = 23;
        int resNumber  = number * 2;
        int lenghtOfResNumber = log10(resNumber) + 1;
        int difference        = lenghtOfResNumber - lenghtOfNumber;

        String mod = Integer.toString(difference);
        
        String res = "" + mod;
        int nextValue = resNumber;
        // System.out.println( " lenghtof "+ resNumber + " is " + lenghtOfResNumber);
        // pow(10, 2 + quantityZero) <= resNumber
        if (difference == 1) {
               nextValue = resNumber - pow(10, lenghtOfResNumber - 1);
               // System.out.println(mod + " " + number + " " + pow(10, lenghtOfResNumber - 1));
               if (nextValue == 0) {
                    return res;
                }
        }
        res = res + convertFraction(nextValue, quantityZero);  

        return res;
    }

    /**
     * Работает не корректно. Требуется для определния количества знаков.
     */
    public static int log10 (int number) {

        if ( number < 10 ) {
            return 0;
        }

        int powedNumber = 10;
        int resExponent = 1;
        for (int exponent = 1; powedNumber <= number; exponent++) {
            powedNumber = pow(10, exponent);
            resExponent = exponent;
        }

        return resExponent - 1;   
    }

    /**
     * Возводит число в степень.
     *
     * @param input    Число.
     * @param exponent Требуемая степень.
     */  
    public static int pow(int input, int exponent) {
        int res = input;
        if (exponent > 0) {
            for(int i = 1; i < exponent; i++) {
                res = res * input;
            }
        }
        if (exponent < 0) {
            res = 1 / pow(input, (exponent * -1));
        }

        if (exponent == 0) {
            return 1;
        }

        return  res;
    }
}
