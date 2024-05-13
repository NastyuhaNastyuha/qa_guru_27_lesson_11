package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RandomDate {

    public String day;
    public String month;
    public String year;

    public void getRandomBirthDate() {
        Faker faker = new Faker();
        Date date = faker.date().birthday();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        day = simpleDateFormat.format(date);

        simpleDateFormat = new SimpleDateFormat("LLLL", Locale.ENGLISH);
        month = simpleDateFormat.format(date);

        simpleDateFormat = new SimpleDateFormat("yyyy");
        year = simpleDateFormat.format(date);
    }
}
