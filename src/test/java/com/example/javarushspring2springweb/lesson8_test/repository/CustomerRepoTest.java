package com.example.javarushspring2springweb.lesson8_test.repository;


import com.example.javarushspring2springweb.lesson8_test.entity.Customer;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Context
@AllArgsConstructor
class CustomerRepoTest {

    private final CustomerRepo customerRepo;

    @Test
    void findCustomerByLoginStartingWithAndLoginEndingWith() {
        Customer customer = customerRepo
                .findCustomer("i", "n")
                .orElseThrow();
        Assertions.assertEquals("ivan", customer.getLogin());
    }

    @Test
    void findAll() {
        Customer customer = customerRepo
                .findCustomer("i", "n")
                .orElseThrow();

        List<Customer> all = customerRepo.findAll();
        assertTrue(all.size() > 0);
        assertTrue(all.contains(customer));
    }

    @Test
    void findAndSort() {
        Sort.TypedSort<Customer> customerSort = Sort.sort(Customer.class);
        // сортировка
//        Sort.TypedSort<String> orders = customerSort.by(Customer::getLogin);
        // сортировка в обратную сторону
//        Sort orders = customerSort.by(Customer::getLogin).descending();
        //две сортировки
        Sort orders = customerSort.by(Customer::getLogin) //сортировка по логину
                .and(customerSort.by(Customer::getId)) //сортировка по id
                .descending(); //разворот, в обратном порядке

        List<Customer> allSort = customerRepo.findAll(orders);
        allSort.forEach(System.out::print);
    }

    @Test
    void findAndPageable() {
        Sort.TypedSort<Customer> customerSort = Sort.sort(Customer.class);
        Sort orders = customerSort.by(Customer::getLogin) //сортировка по логину
                .and(customerSort.by(Customer::getId)) //сортировка по id
                .descending(); //разворот, в обратном порядке
        Pageable pageable = PageRequest.of(0, 3);
        Page<Customer> page = null;
        do {
            page = customerRepo.findAll(pageable);
            pageable = pageable.next();
            page.forEach(System.out::println);
            System.out.printf("Page %d, pages %d, count %d%n"
                    , page.getNumber()
                    , page.getTotalPages()
                    , page.getTotalElements());
        } while (page.hasNext());
    }


}