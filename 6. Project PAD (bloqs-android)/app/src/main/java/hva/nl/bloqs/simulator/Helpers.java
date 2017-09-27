package hva.nl.bloqs.simulator;

public class Helpers {

    public static <T> T coalesce(T... params)
    {
        for (T param : params)
            if (param != null)
                return param;
        return null;
    }

    public static boolean hasTrue(Boolean[] array) {
        for(Boolean bool : array) {
            if(bool != null && bool == true)
                return  true;
        }

        return false;
    }
}
