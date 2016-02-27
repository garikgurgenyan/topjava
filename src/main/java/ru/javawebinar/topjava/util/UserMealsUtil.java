package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredMealsWithExceeded.forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime
            , LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
/*
        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> groupMeaFromOneDay = new HashMap<>();

        for (UserMeal meal : mealList) {
                LocalDate localDate = meal.getDateTime().toLocalDate();
                if (!groupMeaFromOneDay.containsKey(localDate)) {
                    groupMeaFromOneDay.put(localDate, meal.getCalories());
                } else {
                    int tmpCalories = groupMeaFromOneDay.get(localDate) + meal.getCalories();
                    groupMeaFromOneDay.put(localDate, tmpCalories);
                }
        }

        for (UserMeal userMeal : mealList) {
            LocalTime localTime = userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(localTime, startTime, endTime)) {
                boolean exceed = groupMeaFromOneDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay;
                result.add(new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),exceed));
            }
        }
*/

        Map<LocalDate, Integer> sumCaloriesFromOneDate = mealList.stream()
                .collect(Collectors.groupingBy(ud -> ud.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> result = mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        sumCaloriesFromOneDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());


        return result;
    }
}
