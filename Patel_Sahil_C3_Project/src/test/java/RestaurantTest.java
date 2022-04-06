import com.RestaurantFinder.Item;
import com.RestaurantFinder.Restaurant;
import com.RestaurantFinder.itemNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    //spoof variable acts as the menu selected by the user
    List<Item> spoof = new ArrayList<Item>();

    public void restaurantCreation() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Mom's cafe", "New Jersey", openingTime, closingTime);
        restaurant.addToMenu("Sweet & Sour soup", 15);
        restaurant.addToMenu("Chicken Korma", 25);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        restaurantCreation();
        restaurant.setClosingTime(LocalTime.now().plusMinutes(10));
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        restaurantCreation();
        restaurant.setClosingTime(LocalTime.now().minusMinutes(10));
        assertFalse(restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void order_value_should_get_cumulative_total_when_collection_of_items_selected() {
        restaurantCreation();
        spoof = restaurant.getMenu();
        assertEquals(175, restaurant.getOrderValue(spoof));
    }

    @Test
    public void order_value_should_reduce_cumulative_total_when_an_item_removed() {
        restaurantCreation();
        spoof = restaurant.getMenu();
        int total = restaurant.getOrderValue(spoof);
        int afterTotal = spoof.get(1).getPrice();
        spoof.remove(1);
        assertEquals(total - afterTotal, restaurant.getOrderValue(spoof));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        restaurantCreation();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Brownie", 3);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurantCreation();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Chicken Korma");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurantCreation();
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("Mexican Pizza"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}