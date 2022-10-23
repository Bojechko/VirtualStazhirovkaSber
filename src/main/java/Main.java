import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<City> cities = CityUtils.parse();
        CityUtils.findCountCitiesInRegion(cities);
    }
}

