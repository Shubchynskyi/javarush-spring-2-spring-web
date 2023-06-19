package com.example.javarushspring2springweb.lesson8_controllers.service;

import com.example.javarushspring2springweb.lesson8_controllers.entity.User;
import com.example.javarushspring2springweb.lesson8_controllers.repository.UserRepo;
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
class UserServiceTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserService userService;

    public static final long USER_ID = 1L;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(USER_ID)
                .name("mock_login")
                .password("mock_password")
                .build();
    }

    @Test
    void get() {
        //given
//        when(userRepo.getReferenceById(USER_ID)).thenReturn(testUser);

        doAnswer(invocation -> Optional.of(testUser))    //возвращаю testUser
                .when(userRepo)                          //когда userRepo
                .findById(USER_ID);                      //вызывает метод findById с USER_ID


        //when
        User user = userService.get(USER_ID).orElseThrow(); // выполняю операцию которую буду проверять

        //then
        assertEquals(USER_ID, user.getId());                    //проверяю совпадают ли id и т.п.
        assertEquals(testUser.getName(), user.getName());
        assertEquals(testUser.getPassword(), user.getPassword());

        // проверяю что обращение к userRepo было один раз на метод getReferenceById
        verify(userRepo, times(1)).findById(USER_ID);

        // проверяю что больше ничего не обращалось к userRepo кроме того что в verify выше
        verifyNoMoreInteractions(userRepo);
        // !!! не путать с verifyNoInteractions - это проверяет что вообще не было обращений
    }

    @Test
    void getAll() {
        //given
        Sort sort = Sort.sort(User.class).by(User::getId);


        doAnswer(invocation -> List.of(testUser, testUser)) //отвечать листом пользователей
                .when(userRepo)                             //когда у репозитория
                .findAll(sort);                     //вызывают метод

        //when
        List<User> users = userService.getAll();

        //then
        assertEquals(2, users.size());
        for (User user : users) {
            assertEquals(testUser.getId(), user.getId());
            assertEquals(testUser.getName(), user.getName());
            assertEquals(testUser.getPassword(), user.getPassword());
        }

        verify(userRepo, times(1)).findAll(sort);
        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void create() {
        //given
//        when(userRepo.save(any(User.class))).thenReturn(testUser);

        doAnswer(invocation -> testUser)
                .when(userRepo)
                .saveAndFlush(any(User.class));

        //when
        User user = userService.create("mock_login", "mock_password").orElseThrow();

        //then
        assertEquals(testUser.getId(), user.getId());
        assertEquals(testUser.getName(), user.getName());
        assertEquals(testUser.getPassword(), user.getPassword());

        verify(userRepo, times(1)).saveAndFlush(any(User.class));
        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void delete() {
        //given
        doNothing()
                .when(userRepo)
                .delete(testUser);

        //when
        userService.delete(testUser);

        //then
        verify(userRepo, times(1)).delete(testUser);
        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void deleteById() {
        //given
        doNothing()
                .when(userRepo)
                .deleteById(USER_ID);

        //when
        userService.deleteById(USER_ID);

        //then
        verify(userRepo, times(1)).deleteById(USER_ID);
        verifyNoMoreInteractions(userRepo);
    }
}