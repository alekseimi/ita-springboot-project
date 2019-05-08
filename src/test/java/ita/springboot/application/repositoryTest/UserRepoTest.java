package ita.springboot.application.repositoryTest;

import ita.springboot.application.model.Role;
import ita.springboot.application.model.User;
import ita.springboot.application.repository.UserRepository;
import ita.springboot.application.service.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepoTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @After
    public void clearDatabase(){
        userRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetUserCount_ArrayOfUsers(){
        User user = new User();
        user.setEmail("ted@email.com");
        user.setFirstName("Janez");
        user.setLastName("Novak");
        user.setLastName("123");
        user.setPassword("password");
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

        User user1 = new User();
        user1.setEmail("janez@email.com");
        user1.setFirstName("Janez");
        user1.setLastName("Novak");
        user1.setLastName("123");
        user1.setPassword("password");
        user1.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

        userRepository.save(user);
        userRepository.save(user1);

        Assert.assertEquals(userRepository.count(), 2);
    }

    @Test
    public void testFindOneUserById_OneUser(){
        User user = new User();
        user.setEmail("ted@email.com");
        user.setFirstName("Janez");
        user.setLastName("Novak");
        user.setLastName("123");
        user.setPassword("password");
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        userRepository.save(user);

        User retrievedUser = userService.findByEmail("ted@email.com");
        Assert.assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        Assert.assertEquals(user.getEmail(), retrievedUser.getEmail());
    }

    @Test
    public void testDeleteUserFromDatabase(){
        User user = new User();
        user.setEmail("ted@email.com");
        user.setFirstName("Janez");
        user.setLastName("Novak");
        user.setLastName("123");
        user.setPassword("password");
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

        User user1 = new User();
        user1.setEmail("janez@email.com");
        user1.setFirstName("Janez");
        user1.setLastName("Novak");
        user1.setLastName("123");
        user1.setPassword("password");
        user1.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

        userRepository.save(user);
        userRepository.save(user1);
    }



}
