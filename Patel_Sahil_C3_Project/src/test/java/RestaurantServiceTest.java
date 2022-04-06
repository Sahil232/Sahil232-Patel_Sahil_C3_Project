import com.RestaurantFinder.Restaurant;
import com.RestaurantFinder.RestaurantService;
import com.RestaurantFinder.restaurantNotFoundException;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    public void restaurant_adding_for_testing() {
        restaurant = service.addRestaurant("Mom's cafe", "New Jersey", LocalTime.parse("09:30:00"), LocalTime.parse("21:00:00"));
        service.addRestaurant("Dawaat A Khaas", "New York", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet & Sour soup", 15);
        restaurant.addToMenu("VChicken Korma", 25);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        assertEquals("Mom's cafe", service.findRestaurantByName("Mom's cafe").getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        assertThrows(restaurantNotFoundException.class, () -> {
            service.findRestaurantByName("Mom");
        });

    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Mom's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        restaurant_adding_for_testing();
        assertThrows(restaurantNotFoundException.class, () -> {
            service.removeRestaurant("Pantry d'or");
        });
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
        restaurant_adding_for_testing();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Laal Tamatar", "Jersey City", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}