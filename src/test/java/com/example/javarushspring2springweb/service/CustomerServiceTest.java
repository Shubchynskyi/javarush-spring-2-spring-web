package com.example.javarushspring2springweb.service;

import com.example.javarushspring2springweb.entity.Customer;
import com.example.javarushspring2springweb.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepo customerRepo;

    @InjectMocks
    CustomerService customerService;

    public static final long CUSTOMER_ID = 1L;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = Customer.builder()
                .id(CUSTOMER_ID)
                .name("mock_login")
                .password("mock_password")
                .build();
    }

    @Test
    void get() {
        //given
//        when(userRepo.getReferenceById(USER_ID)).thenReturn(testUser);

        doAnswer(invocation -> Optional.of(testCustomer))    //возвращаю testCustomer
                .when(customerRepo)                          //когда customerrRepo
                .findById(CUSTOMER_ID);                      //вызывает метод findById с CUSTOMER_ID


        //when
        Customer customer = customerService.get(CUSTOMER_ID).orElseThrow(); // выполняю операцию которую буду проверять

        //then
        assertEquals(CUSTOMER_ID, customer.getId());                    //проверяю совпадают ли id и т.п.
        assertEquals(testCustomer.getName(), customer.getName());
        assertEquals(testCustomer.getPassword(), customer.getPassword());

        // проверяю что обращение к customerRepo было один раз на метод getReferenceById
        verify(customerRepo, times(1)).findById(CUSTOMER_ID);

        // проверяю что больше ничего не обращалось к userRepo кроме того что в verify выше
        verifyNoMoreInteractions(customerRepo);
        // !!! не путать с verifyNoInteractions - это проверяет что вообще не было обращений
    }

    @Test
    void getAll() {
        //given
        Sort sort = Sort.sort(Customer.class).by(Customer::getId);


        doAnswer(invocation -> List.of(testCustomer, testCustomer)) //отвечать листом пользователей
                .when(customerRepo)                             //когда у репозитория
                .findAll(sort);                     //вызывают метод

        //when
        List<Customer> customers = customerService.getAll();

        //then
        assertEquals(2, customers.size());
        for (Customer customer : customers) {
            assertEquals(testCustomer.getId(), customer.getId());
            assertEquals(testCustomer.getName(), customer.getName());
            assertEquals(testCustomer.getPassword(), customer.getPassword());
        }

        verify(customerRepo, times(1)).findAll(sort);
        verifyNoMoreInteractions(customerRepo);
    }

    @Test
    void create() {
        //given
//        when(customerRepo.save(any(User.class))).thenReturn(testCustomer);

        doAnswer(invocation -> testCustomer)
                .when(customerRepo)
                .saveAndFlush(any(Customer.class));

        //when
        Customer customer = customerService.create("mock_login", "mock_password").orElseThrow();

        //then
        assertEquals(testCustomer.getId(), customer.getId());
        assertEquals(testCustomer.getName(), customer.getName());
        assertEquals(testCustomer.getPassword(), customer.getPassword());

        verify(customerRepo, times(1)).saveAndFlush(any(Customer.class));
        verifyNoMoreInteractions(customerRepo);
    }

    @Test
    void delete() {
        //given
        doNothing()
                .when(customerRepo)
                .delete(testCustomer);

        //when
        customerService.delete(testCustomer);

        //then
        verify(customerRepo, times(1)).delete(testCustomer);
        verifyNoMoreInteractions(customerRepo);
    }

    @Test
    void deleteById() {
        //given
        doNothing()
                .when(customerRepo)
                .deleteById(CUSTOMER_ID);

        //when
        customerService.deleteById(CUSTOMER_ID);

        //then
        verify(customerRepo, times(1)).deleteById(CUSTOMER_ID);
        verifyNoMoreInteractions(customerRepo);
    }
}