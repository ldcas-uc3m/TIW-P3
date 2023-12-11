package es.uc3m.tiw.utils;

import java.util.Arrays;
import java.util.regex.Pattern;


public class ValidadorDNI {

	private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
	private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
	private static final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };

	public static boolean validDNI(String dni) {
		return
			Arrays.binarySearch(INVALIDOS, dni) < 0
    		&& REGEXP.matcher(dni).matches()
    		&& dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23)
    	;
  }

}
