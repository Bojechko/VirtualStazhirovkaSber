import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CityUtils {
    public static List<City> parse() throws FileNotFoundException {
        ArrayList<City> cities = new ArrayList<>();
        String data = new String();
        String year = "";
        try {
            Scanner input = new Scanner(new File("src/main/resources/city_ru.csv"));
            input.useDelimiter("\n");
            while (input.hasNext()){
                data = input.next();
                String [] splitData = data.split(";");
                if (splitData.length == 5){
                    year = "";
                } else {
                   year = splitData[5].substring(0, splitData[5].length() - 1);
                }
                cities.add(new City(splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4]), year));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void  printCity (List<City> cities){
        cities.forEach(System.out::println);
    }

    public static List<City> sortByNameDesc(List<City> cities){
        cities.sort(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER));
        return cities;
    }

    public static List<City> sortByDistrictAndName(List<City> cities){
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        return cities;
    }

    public static void findMaxPopulation(List<City> cities){
        City[] array = cities.toArray(new City[0]);
        int maxPopulation = 0, index = 0;
        for (int i = 0; i < array.length; i++){
            if (maxPopulation < array[i].getPopulation()){
                maxPopulation = array[i].getPopulation();
                index = i;
            }
        }
        System.out.println("[" + index + "]=" + maxPopulation);
    }

    public static void findCountCitiesInRegion(List<City> cities){
        cities.sort(Comparator.comparing(City::getRegion));
        int count = 0 ;
        String currentRegion = cities.get(0).getRegion();

        printCity(cities);

        for(int i = 0; i < cities.size(); i++){
            if(cities.get(i).getRegion().equals(currentRegion)){
                count++;
            } else {
                System.out.println(currentRegion + " - " + count);
                count = 1;
                currentRegion = cities.get(i).getRegion();
            }
        }
        System.out.println(currentRegion + " - " + count);
    }

}
