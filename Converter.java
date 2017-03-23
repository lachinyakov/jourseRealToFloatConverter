
public class Converter {
    public static int maxLength;
    public static int sign; 

    public static void main(String[] args) {
        maxLength = 23;
        String input     = args[0];
        String[] numbers = input.split("\\.");
        int intgr        = Integer.parseInt(numbers[0]);
        String resInt    = "0";
        int fraction     = 0;
        String resFrac   = "";
        if (numbers.length > 1) {
            fraction     = Integer.parseInt(numbers[1]);
        }

        setSign(intgr, fraction);      
        if (intgr  > 0) {
            resInt = convertInteger(intgr);
        }

        String result = resInt;
        if (fraction > 0) {
            int lenghtofFraction = log10(fraction) + 1;
            int quantityZero     = numbers[1].length() - lenghtofFraction;
            int maxQtySymbols    = lenghtofFraction + quantityZero;
            resFrac              = convertFraction(fraction, maxQtySymbols);
            result               = result + "." + resFrac;
        }

        int exponent = 0;
        if (sign == 0) {
        	exponent = normalizeIntgr(resInt);
        }


        if (sign == 1) {
           exponent = normalizeFraction(resFrac);
        }


        System.out.println("sign:" + sign);
        System.out.println("exponent:" + exponent);
        System.out.println("result:" + result);
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

    /**
     * 
     */
    public static String convertFraction(int number, int maxQtySymbols) {
        int lenghtOfNumber    = log10(number) + 1;
        int resNumber         = number * 2;
        int lenghtOfResNumber = log10(resNumber) + 1;

        String res = "";
        int nextValue = resNumber;
        if (lenghtOfResNumber > maxQtySymbols) {
               res = "1";
               nextValue = resNumber - pow(10, lenghtOfResNumber - 1);
               if (nextValue == 0) {
                    return res;
                }
        } else {
            res = "0";
        }

        if (maxLength > 0 ) {
            maxLength--;
        
            res = res + convertFraction(nextValue, maxQtySymbols);        
        }
         

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

    public static void setSign(int integer, int fraction) {
    	if (integer > 0) {
    		sign = 0;
    	}

        if (integer == 0 && fraction > 0) {
        	sign = 1;
        }
    }

    public static int normalizeIntgr (String number) {
        int result =  number.length() - 1;
        return result;
    }

    public static int normalizeFraction (String number) {

        int strLength = number.length();
        int intLength = log10(Integer.parseInt(number));
        int result = strLength - intLength + 1;
        return result;
    }
}
