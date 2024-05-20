package utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String  getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);
        return array[index];
    }

    public static String[] getRandomUniqueItemsFromArray(String[] array, int numberOfItems) {
        Set<String> items = new HashSet<>();
        while (items.size() < numberOfItems) {
            items.add((array[getRandomInt(0, array.length - 1)]));
        }

        return items.toArray(new String[0]);
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public static String getRandomNumberString(int len) {
        String numbers = "1234567890";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(numbers.charAt(rnd.nextInt(numbers.length())));
        }

        return sb.toString();
    }

    public static String[] getRandomSubject(int numberOfSubjects) {
        String[] subjects = {"Maths", "Accounting", "Arts", "Social Studies", "English", "Chemistry", "Computer Science", "Economics"};
        return getRandomUniqueItemsFromArray(subjects, numberOfSubjects);
    }

    public static String[] getRandomHobbies(int numberOfHobbies) {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomUniqueItemsFromArray(hobbies, numberOfHobbies);
    }

}