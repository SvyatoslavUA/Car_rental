package lpnu.resource;


import lpnu.dto.OrderDTO;
import lpnu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingResource {
    @Autowired
    private BookService bookService;


    @PostMapping("/booking/{userId}/{carId}")
    public OrderDTO bookCar(@PathVariable Long userId,@PathVariable Long carId) {
        return bookService.bookCar(userId, carId);
    }

    @PutMapping("/cancel-booking/{orderId}")
    public ResponseEntity cancelBooking(@PathVariable Long orderId) {
        bookService.cancelBook(orderId);
        return ResponseEntity.ok().build();
    }
}
