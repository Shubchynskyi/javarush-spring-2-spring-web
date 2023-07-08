package com.example.javarushspring2springweb.service;


import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    @Transactional
    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    @Transactional
    public List<Customer> getAll() {
        Sort sort = Sort.sort(Customer.class).by(Customer::getId); // сортировка по id
        return customerRepo.findAll(sort);
    }

    @Transactional
    public Optional<Customer> create (String name, String password) {
        Customer build = Customer.builder()
                .name(name)
                .password(password)
                .build();
        return Optional.of(customerRepo.saveAndFlush(build));
    }

    @Transactional
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }

    @Transactional
    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }


    public Optional<Customer> findByCustomerName(String customerName) {
        Example<Customer> customerExample = Example.of(Customer.builder().name(customerName).build());
        return customerRepo.findOne(customerExample);
    }
}
