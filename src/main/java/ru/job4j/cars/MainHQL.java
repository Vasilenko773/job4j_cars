package ru.job4j.cars;

import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Vacancy;

import java.util.List;

public class MainHQL {

    public static void main(String[] args) {

        List<Advertisement> advertisementList = AddRepository.instOf().getAdvertisementToday();
        for (Advertisement a : advertisementList) {
            System.out.println(a);
        }

        List<Advertisement> bodyList = AddRepository.instOf().getBodyTape("Sedan");
        for (Advertisement a : bodyList) {
            System.out.println(a);
        }

        List<Advertisement> imageList = AddRepository.instOf().getImage();
        for (Advertisement a : imageList) {
            System.out.println(a);
        }
    }
}
