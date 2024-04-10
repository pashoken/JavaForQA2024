package ru.shop;

import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;
import ru.shop.sevices.CustomerService;
import ru.shop.sevices.OrderService;
import ru.shop.sevices.ProductService;

import java.util.UUID;

public class Main {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final OrderRepository orderRepository = new OrderRepository();
    private static final OrderService orderService = new OrderService(orderRepository);
    private static final ProductService productService = new ProductService(productRepository);
    private static final CustomerService customerService = new CustomerService(customerRepository);



    public static void main(String[] args) throws BadOrderCountException {
        var product = new Product();

        product.setId(UUID.randomUUID());
        product.setName("product1");
        product.setCost(99);
        product.setProductType(ProductType.GOOD);

        productService.save(product);

        var product2 = new Product();

        product2.setId(UUID.randomUUID());
        product2.setName("product2");
        product2.setCost(55);
        product2.setProductType(ProductType.GOOD);

        productService.save(product2);

        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("customer1");
        customer.setAge(33);
        customer.setPhone("89997776655");

        customerService.save(customer);

        var customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setName("customer2");
        customer2.setAge(19);
        customer2.setPhone("89997776655");

        customerService.save(customer2);

        orderService.add(customer2, product2, 100);
        try {
            orderService.add(customer2, product2, -20);
        } catch (BadOrderCountException e) {
            System.out.println("Ошибка: заказ от " + customer2.getName() + " продукта " + product2.getName() + " в количестве " + -20 + " не удался.");
        };
        orderService.add(customer, product2, 20);

        System.out.println("Количество заказчиков = " + customerRepository.findAll().size());
        System.out.println("Количество заказов = " + orderRepository.findAll().size());
        System.out.println("Количество продукта " + ProductType.GOOD + " = "+ productService.findByProductType(ProductType.GOOD).size());
        System.out.println("Количество продукта " + ProductType.SERVICE + " = "+ productService.findByProductType(ProductType.SERVICE).size());
        System.out.println("Количество заказов для " + customer.getName() + " = "+ orderService.findByCustomer(customer).size());
        System.out.println("Количество заказов для " + customer2.getName() + " = "+ orderService.findByCustomer(customer2).size());
        System.out.println("Сумма заказов для " + customer.getName() + " = "+ orderService.getTotalCustomerAmount(customer));
        System.out.println("Сумма заказов для " + customer2.getName() + " = "+ orderService.getTotalCustomerAmount(customer2));
        //


//        allProducts.remove(0);
//
//
//        allProducts = productRepository.findAll();
//
//        System.out.println("products = " + allProducts);




//
//        var productType = ProductType.SERVICE;
//
//        var sp = switch (productType) {
//            case GOOD -> "G";
//            case SERVICE -> "S";
//        };

    }

}