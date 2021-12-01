package lpnu.service;


import lpnu.dto.OrderDTO;



public interface BookService {
    OrderDTO bookCar(Long userId, Long carId);
    void cancelBook(Long orderId);
}
