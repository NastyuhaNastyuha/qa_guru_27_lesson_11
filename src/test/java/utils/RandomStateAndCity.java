package utils;

import java.util.*;

import static utils.RandomUtils.getRandomItemFromArray;

public class RandomStateAndCity {
    public String state;
    public String city;

    public Map<String, String[]> statesAndCities = Map.of(
            "NCR", new String[] {"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[] {"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[] {"Karnal", "Panipat"},
            "Rajasthan", new String[] {"Jaipur", "Jaiselmer"}
    );

    public void getRandomStateAndCity() {
        String[] states = statesAndCities.keySet().toArray(new String[0]);
        state = getRandomItemFromArray(states);
        city = getRandomItemFromArray(statesAndCities.get(state));
    }
}