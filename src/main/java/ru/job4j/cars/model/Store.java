package ru.job4j.cars.model;

import java.util.List;

public interface Store {

    List<Vacancy> getVacancyToday();

    List<Advertisement> getAdvertisementToday();

    List<Advertisement> getBodyTape(String bodyStyle);

    List<Advertisement> getImage();
}
