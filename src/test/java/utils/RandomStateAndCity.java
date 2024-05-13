package utils;

import java.util.Objects;

import static utils.RandomUtils.getRandomInt;

public class RandomStateAndCity {

    public String state;
    public String city;

    public void getRandomStateAndCity() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        state = getRandomItemFromArray(states);
        city = getRandomCityInState(state);
    }

    public String  getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);
        return array[index];
    }

    public String getRandomCityInState(String state) {
        String[] ncrCities = {"Delhi", "Gurgaon", "Noida"};
        String[] uttarPradeshCities = {"Agra", "Lucknow", "Merrut"};
        String[] haryanaCities = {"Karnal", "Panipat"};
        String[] rajasthanCities = {"Jaipur", "Jaiselmer"};

        if (Objects.equals(state, "NCR")) {
            city = getRandomItemFromArray(ncrCities);
        } else if (Objects.equals(state, "Uttar Pradesh")) {
            city = getRandomItemFromArray(uttarPradeshCities);
        } else if (Objects.equals(state, "Haryana")) {
            city = getRandomItemFromArray(haryanaCities);
        } else if (Objects.equals(state, "Rajasthan")) {
            city = getRandomItemFromArray(rajasthanCities);
        }
        return city;
    }
}
