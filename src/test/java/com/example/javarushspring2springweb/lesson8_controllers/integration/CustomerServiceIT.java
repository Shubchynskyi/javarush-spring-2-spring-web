package com.example.javarushspring2springweb.lesson8_controllers.integration;

import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.service.CustomerService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MyTestContext
class CustomerServiceIT {

    @Autowired
    private EntityManager entityManager;

    private final CustomerService customerService;
    public static final long CUSTOMER_ID = 1L;

    private Customer inCustomer;
    private Customer expextedCustomer;

    CustomerServiceIT(CustomerService customerService) {
        this.customerService = customerService;
    }

    @BeforeEach
    void setUp() {
        inCustomer = Customer.builder()
                .name("test_login")
                .password("test_password")
                .build();
        expextedCustomer = Customer.builder()
                .id(CUSTOMER_ID)
                .name("Admin")
                .password("123")
                .build();
    }

    @Test
    @Transactional
    void get() {
        Customer customer = customerService.get(CUSTOMER_ID).orElseThrow();
        assertEquals(expextedCustomer.getId(), customer.getId());
    }

    @Test
    void getAll() {
        List<Customer> allCustomers = customerService.getAll();
        assertTrue(allCustomers.size() > 1, "List should have more than one customer");
    }

    @Test
    @Transactional
    @Rollback // это по умолчанию для тестов которые @Transactional
    void create() {
        Customer actualCustomer = customerService.create(inCustomer.getName(), inCustomer.getPassword()).orElseThrow();
        assertTrue(actualCustomer.getId() > 0);
        assertEquals(inCustomer.getName(), actualCustomer.getName());
        assertEquals(inCustomer.getPassword(), actualCustomer.getPassword());
    }

    @Test
    @Transactional
    void delete() {
        // Добавляем пользователя
        Customer addedCustomer = customerService.create("test", "password").orElseThrow();

        // Удаляем пользователя
        customerService.delete(addedCustomer);

        // Синхронизируем состояние и очищаем кеш
        entityManager.flush();
        entityManager.clear();

        // Проверяем, что пользователя больше нет в базе
        assertFalse(customerService.get(addedCustomer.getId()).isPresent(), "Customer should be deleted");
    }

    @Test
    @Transactional
    void deleteById() {
        // Добавляем пользователя
        Customer addedCustomer = customerService.create("test", "password").orElseThrow();

        // Удаляем пользователя по ID
        customerService.deleteById(addedCustomer.getId());

        // Синхронизируем состояние и очищаем кеш
        entityManager.flush();
        entityManager.clear();

        // Проверяем, что пользователя больше нет в базе
        assertFalse(customerService.get(addedCustomer.getId()).isPresent(), "Customer should be deleted");
    }
}