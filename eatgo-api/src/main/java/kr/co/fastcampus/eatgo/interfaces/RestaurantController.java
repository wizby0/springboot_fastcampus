package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {


    //    private RestaurantRepository repository = new RestaurantRepository();
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
//        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){

        Restaurant restaurant = restaurantService.getRestaurant(id);


        return restaurant;

    }

//    @PostMapping("/restaurants")
//    public ResponseEntity<?> create() throws URISyntaxException {
//        String name = "BeRyong";
//        String address = "Seoul";

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {

        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                    .name(resource.getName())
                    .address(resource.getAddress())
                    .build());

        URI location = new URI("/restaurants/" + restaurant.getId());

        return ResponseEntity.created(location).body("{}");

    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody Restaurant resource){
        String name = resource.getName();
        String address = resource.getAddress();

        restaurantService.updateRestaurant(id, name, address);

        return "{}";
    }
}
