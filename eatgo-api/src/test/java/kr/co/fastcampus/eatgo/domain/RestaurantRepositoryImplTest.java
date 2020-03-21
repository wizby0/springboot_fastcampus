package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RestaurantRepositoryImplTest {

    @Test
    public void save() {
        RestaurantRepository repository = new RestaurantRepositoryImpl();

        Restaurant restaurant = new Restaurant("Beryong","Seoul");
        int oldCount =repository.findAll().size();


        repository.save(restaurant);

        assertThat(restaurant.getId(),is(1234L));

        int newCount =repository.findAll().size();

        assertThat(newCount - oldCount, is(1));
    }
}