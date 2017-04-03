
public class Geter {
	public void main(String[] args) {
		String firstString = args[0];
		String secondString = args[1];
		String[] arg1 = firstString.split("\\.");
    	String[] arg2 = secondString.split("\\.");
    	String[]binArg1 = convertArrInt(arg1);
    	String[]binArg2 = convertArrInt(arg2);

		// Options options = new Options();
		// Option ip = new Option("i", "input", true, "input ipAdrr");
		// ip.setRequired(true);
		// options.addOption(ip);

		// Option mask = new Option("m", "input", true, "input Mask");
		// mask.setRequired(true);
		// options.addOption(mask);
	}

	public static String[] convertArrInt(String[] arrInt) {
		String[] result;
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

        int mod       = number % 2;
        int nextValue = (number - mod) / 2;

        return convertInteger(nextValue) + Integer.toString(mod);
    }

    public static String binConunction(String arg1, String arg2) {
    		char[] charArg1 = arg1.toCharArray();
    		char[] charArg2 = arg2.toCharArray();
    		int[] result;
    		for (int i; i < charArg1.length; i++) {
    			result[i] = (int)charArg1[i] * (int)charArg2[i];
    		}

    		return String.valueOf(result);
    }

    public static String addZero(String converted) {
			int length = 8; 
			int diffrence = length - converted.length();
			
			if (diffrence < 0) {
				for (int i  = 0; i < diffrence; i++) {
					converted = "0" + converted;
				}
			}

			return converted;
    }
}