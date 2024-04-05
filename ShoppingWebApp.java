
// ShoppingWebApp.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import javax.persistence.*;
import java.util.*;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ShoppingWebApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingWebApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started!");
    }

    @RestController
    @RequestMapping("/inventory")
    public class InventoryController {
        @Autowired
        private ProductService productService;
        // methods
    }

    @RestController
    @RequestMapping("/{userId}/order")
    public class OrderController {
        @Autowired
        private OrderService orderService;
        // methods
    }

    @RestController
    @RequestMapping("/{userId}/orders")
    public class UserController {
        @Autowired
        private OrderService orderService;
        // methods
    }

    @Entity
    class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private double price;
        private int availableQuantity;
        // constructors, getters, setters
    }

    @Entity
    class Coupon {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String code;
        private int discountPercentage;
        private boolean used;
        // constructors, getters, setters
    }

    @Entity
    class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        private User user;
        @ManyToOne
        private Product product;
        private int quantity;
        private double amount;
        private String couponCode;
        private String transactionId;
        private String status;
        // constructors, getters, setters
    }

    @Entity
    class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        // constructors, getters, setters
    }

    // Repositories
    interface ProductRepository extends JpaRepository<Product, Long> {
    }

    interface CouponRepository extends JpaRepository<Coupon, Long> {
    }

    interface OrderRepository extends JpaRepository<Order, Long> {
    }

    interface UserRepository extends JpaRepository<User, Long> {
    }

    // Services
    @Service
    class ProductService {
        @Autowired
        private ProductRepository productRepository;
        // methods
    }

    @Service
    class CouponService {
        @Autowired
        private CouponRepository couponRepository;
        // methods
    }

    @Service
    class OrderService {
        @Autowired
        private OrderRepository orderRepository;
        // methods
    }

    @Service
    class UserService {
        @Autowired
        private UserRepository userRepository;
        // methods
    }
}
