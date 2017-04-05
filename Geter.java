
public class Geter {
	public static void main(String[] args) {
        String firstString  = args[0];
        String[] requestNetwork = args[0].split("\\.");
        System.out.println("input network address: " + args[0]);

        String secondString = args[1];

        System.out.println("input host address in CIDR notation: " + args[1]);

        String[] ipMask     = secondString.split("/");
        String[] ip        = ipMask[0].split("\\.");
        String[] mask         = binMask(ipMask[1]);
        String[] arg1       = ip;
        String[]binArg2    = mask;
        String[]binArg1     = convertArrInt(arg1);
    	String[] result  = new String[4];	
    	for (int i =0; i < result.length; i++) {
   			String res   = binConunction(binArg1[i], binArg2[i]);
   			int    decimal = binToDec(res);
   			result[i] = Integer.toString(decimal);
    	}


    	String res = strArrToStr(result);	
    	boolean equal = (requestNetwork == result);
       System.out.println("Result: " + res + " - " + equal);
	}

	public static String[] convertArrInt(String[] arrInt) {
		String[] result = new String[4];
		for (int i = 0; i < arrInt.length; i++) {
			String converted = convertInteger(Integer.parseInt(arrInt[i]));
			result[i] = addZero(converted);
		}
	
		return result;
	}


	public static String convertInteger(int number) {
        if (number == 1) {
            return "1";
        }

        if (number == 0) {
            return "0";
        }


        int mod       = number % 2;
        int nextValue = (number - mod) / 2;

        return convertInteger(nextValue) + Integer.toString(mod);
    }

    public static String binConunction(String arg1, String arg2) {
    		char[] charArg1 = arg1.toCharArray();
    		char[] charArg2 = arg2.toCharArray();
    		String result   = "";
    		for (int i = 0; i < charArg1.length; i++) {
    			int intA = Character.getNumericValue(charArg1[i]);
    			int intB = Character.getNumericValue(charArg2[i]);
    			int res  = intA * intB;
    			result   = result + res;
    		}

    		return result;
    }

    public static String addZero(String converted) {
			int length = 8; 
			int diffrence = length - converted.length();
			if (diffrence > 0) {
				for (int i  = 0; i < diffrence; i++) {
					converted = "0" + converted;
				}
			}

			return converted;
    }


    public static int binToDec(String binNum) {
    	char[] chars = binNum.toCharArray();
    	int result = 0;
    	for (int i = 0	; i < chars.length; i++) {    		
    		int number = Character.getNumericValue(chars[i]);
    		if (number == 1) {
    			int exponent = (chars.length - 1) - i;

    			result = result + pow(2, exponent);
    		}
    	}

    	return result;
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

   	public static String strArrToStr(String[] strArr) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < strArr.length; i++) {
   			strBuilder.append(strArr[i]);
   			if (i < strArr.length - 1) {
   				strBuilder.append(".");
   			}
		}

		return strBuilder.toString();
   	}

    public static String[] binMask(String number) {
        int quantity    = Integer.parseInt(number);
        String[] result = new String[4];
        for (int i = 0; i < 4; i++ ) {
            String res = "";
            for (int k = 0; k < 8; k++) {
                if (quantity > 0) {
                    res = res + "1";
                    quantity = quantity -1;
                } else {
                    res = res + "0";
                }
            
            }

            result[i] = res;
        }

        return result;
    }
}