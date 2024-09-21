package mate.academy.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public Order completeOrder(Long userId) {
        logger.info("completeOrder method was called for userId: {}", userId);
        List<Product> products = null;
        try {
            products = getAllProductsFromShoppingCart(userId);
            logger.debug("Products retrieved from shopping cart for userId: {}: {}",
                    userId, products);
        } catch (Exception e) {
            logger.error("Failed to retrieve products from shopping cart for userId: {}",
                    userId, e);
        }

        Order order = new Order(products, userId);
        logger.info("Order created for userId: {} with products: {}", userId, products);
        // NOTE: In production ready code this order identifier should be generated by DB
        // For test purpose we simplify this and return dummy data
        order.setOrderId(1L);
        logger.debug("Order ID set to 1 for userId: {}", userId);

        logger.info("Order successfully completed for userId: {}", userId);
        return order;
    }

    private List<Product> getAllProductsFromShoppingCart(Long userId) {
        // NOTE: In production ready code this method should fetch data from DB
        // For test purpose we simplify this method and return dummy data
        Product iphone = new Product("iPhone X", BigDecimal.valueOf(1199));
        logger.debug("Product iphone created for userId: {}", userId);
        Product macBook = new Product("MacBook Air 2020", BigDecimal.valueOf(1399));
        logger.debug("Product macBook created for userId: {}", userId);
        Product xiaomi = new Product("Xiaomi 12", BigDecimal.valueOf(499));
        logger.debug("Product xiaomi created for userId: {}", userId);
        List<Product> products = List.of(iphone, macBook, xiaomi);
        logger.info("Data successfully fetched from DB");
        return products;
    }
}
