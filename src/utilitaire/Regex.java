package utilitaire;

public class Regex {

public static String getRegexSecu = "^([1-2][0-9]{2})([0-1][0-9]|2[0-9]|9[1-7])([0-9]{2})([0-9]{3}(-[0-9]{2})?)$" ;

public static final String getRegexNom = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+([-'\\s][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$";

public static final String getRegexPrenom = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$";

public static String getRegexPhone = "^(?:(?:0|\\+33)(?:[1-9]|0[1-9])[0-9]{8}|0[67][0-9]{8})$";

public static String getRegexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
}
	
