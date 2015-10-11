package io.dflabs.lib.validators;

/**
 * Created by Daniel García Alvarado on 9/13/15.
 * Gastalon - danielgarcia
 */
@SuppressWarnings("unused")
public class Regex {

    private static final String EMAIL =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String ALPHA_NUMERIC =
            "^[A-za-z0-9-\\+]+$";

    public static final String NUMERIC =
            "^[0-9]+$";

    public static final String DECIMAL =
            "^[0-9]+(\\.[0-9][0-9]?)?$";

    public static final String NOT_EMPTY =
            "^(?=\\s*\\S).*$";
}
