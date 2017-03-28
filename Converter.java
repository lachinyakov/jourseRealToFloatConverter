
public class Converter {
    public static long maxLength;
    public static long sign; 

    public static void main(String[] args) {
        maxLength = 23;
        String input     = args[0];
        String[] numbers = input.split("\\.");
        long intgr        = Long.parseLong(numbers[0]);
        String resInt    = "0";
        long fraction     = 0;
        String resFrac   = "";
        if (numbers.length > 1) {
            fraction     = Long.parseLong(numbers[1]);
        }

        setSign(intgr, fraction);      
        if (intgr  > 0) {
            resInt = convertInteger(intgr);
        }

        String result = resInt;
        if (fraction > 0) {
            long lenghtofFraction = log10(fraction) + 1;
            long quantityZero     = numbers[1].length() - lenghtofFraction;
            long maxQtySymbols    = lenghtofFraction + quantityZero;
            resFrac              = convertFraction(fraction, maxQtySymbols);
            result               = result + resFrac;
        }

        long exponent = 127;
        if (sign == 0) {
        	exponent = 127 + getExponentForIntgr(resInt);
        }


        if (sign == 1) {
           exponent = 127 - getExponentForFrctn(resFrac);
        }


        System.out.println("sign:" + sign);
        System.out.println("exponent:" + convertInteger(exponent));

        System.out.println("result:" + result.substring(1));
    }

    public static String convertInteger(long number) {
        if (number == 1) {
            return "1";
        }

        String result = "";
        long mod       = number % 2;
        long nextValue = (number - mod) / 2;

        return convertInteger(nextValue) + Long.toString(mod);
    }

    /**
     * 
     */
    public static String convertFraction(long number, long maxQtySymbols) {
        long lenghtOfNumber    = log10(number) + 1;
        long resNumber         = number * 2;
        long lenghtOfResNumber = log10(resNumber) + 1;

        String res = "";
        long nextValue = resNumber;
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
    public static long log10 (long number) {

        if ( number < 10 ) {
            return 0;
        }

        long powedNumber = 10;
        long resExponent = 1;
        for (long exponent = 1; powedNumber <= number; exponent++) {
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
    public static long pow(long input, long exponent) {
        long res = input;
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

    public static void setSign(long integer, long fraction) {
    	if (integer > 0) {
    		sign = 0;
    	}

        if (integer == 0 && fraction > 0) {
        	sign = 1;
        }
    }


    public static long getExponentForIntgr (String number) {
        long result =  number.length() - 1;
        return result;
    }

    public static long getExponentForFrctn (String numberi) {
    	int result    = 0;
        int strLength = numberi.length();
        char[] strArr = numberi.toCharArray();
		for (int i = 0; i < strLength; i++) {
			result = result + 1;
			char requiredSymbol = strArr[i];
			if (requiredSymbol == '1')
			{
				break;
			}
		}

        return result;
    }
}
