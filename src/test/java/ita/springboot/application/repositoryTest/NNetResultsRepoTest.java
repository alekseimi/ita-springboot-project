package ita.springboot.application.repositoryTest;


import ita.springboot.application.model.NNetResult;
import ita.springboot.application.model.Role;
import ita.springboot.application.model.User;
import ita.springboot.application.repository.DataAccessHelper;
import ita.springboot.application.repository.NNetResultsRepository;
import ita.springboot.application.repository.UserRepository;
import ita.springboot.application.service.NNetServiceImpl;
import org.hibernate.LazyInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NNetResultsRepoTest {

    @Autowired
    private DataAccessHelper helper;

    @Autowired
    NNetServiceImpl nNetService;

    @Autowired
    NNetResultsRepository nNetResultsRepository;

    @Autowired
    UserRepository userRepository;

    private User user;

    private String emailTest = "test@email.com";

    @Before
    public void setUp() {
        user = createTestUser();
        userRepository.save(user);
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }
    //LAZY INITIALIZATION ON NNETRESULTSs
    @Test(expected = LazyInitializationException.class)
    public void shouldFailWithLazyException() {
        NNetResult nNetResult = createTestNNetResult();
        user.getnNetResults().add(nNetResult);
        nNetResult.setUser(user);
        User retrievedUser = userRepository.findByEmail(emailTest);
        assertTrue(retrievedUser.getnNetResults().contains(nNetResult));
    }

    @Test
    public void retrieveResultsExpectedZero() {
        int expectedSetLength = 0;
        helper.doInTransaction(entityManager -> {
            User newUser = userRepository.findByEmail(emailTest);
            assertEquals(expectedSetLength, newUser.getnNetResults().size());
        });
    }

    @Test
    public void shouldReturnSavedResultLengthOfTwo() {
        int expectedSetLength = 2;

        NNetResult nNetResult1 = createTestNNetResult();
        NNetResult nNetResul2 = createTestNNetResult();

        helper.doInTransaction(entityManager -> {
            //nnetResult1
            User user = userRepository.findByEmail(emailTest);
            user.getnNetResults().add(nNetResult1);
            nNetResult1.setUser(user);
            //nnetResult2
            user.getnNetResults().add(nNetResul2);
            nNetResul2.setUser(user);
            //save user
            userRepository.save(user);

            User retrievedUser = userRepository.findByEmail(emailTest);
            assertEquals(expectedSetLength, retrievedUser.getnNetResults().size());
        });
    }

    @Test
    public void shouldReturnLengthOneOnDelete() {
        int expectedSetLength = 1;

        NNetResult nNetResult1 = createTestNNetResult();
        NNetResult nNetResult2 = createTestNNetResult();

        helper.doInTransaction(entityManager -> {
            //nnetResult1
            User user = userRepository.findByEmail(emailTest);
            user.getnNetResults().add(nNetResult1);
            nNetResult1.setUser(user);
            //nnetResult2
            user.getnNetResults().add(nNetResult2);
            nNetResult2.setUser(user);
            //save user
            userRepository.save(user);

            User retrievedUser = userRepository.findByEmail(emailTest);
            Page<NNetResult> nnetResultsPage = nNetResultsRepository.findByUserId(retrievedUser.getId(), PageRequest.of(0, 2));
            List<NNetResult> nNetResultsList = nnetResultsPage.getContent();
            retrievedUser.getnNetResults().remove(nNetResultsList.get(0));
            assertEquals(expectedSetLength, retrievedUser.getnNetResults().size());
        });
    }

    @AfterEach
    public void clearDatabase() {
        nNetResultsRepository.deleteAll();
    }

    public NNetResult createTestNNetResult() {
        NNetResult nNetResult = new NNetResult();
        nNetResult.setActivationType("TestActivation");
        nNetResult.setAverageClassificationError(20.5);
        nNetResult.setAverageValidationError(10.5);
        nNetResult.setHiddenLayersCount(10);
        nNetResult.setEpochsCount(3);
        nNetResult.setTrainingType("TrainingType");
        return nNetResult;
    }

    public User createTestUser() {
        User user = new User();
        user.setEmail("test@email.com");
        user.setFirstName("Janez");
        user.setLastName("Novak");
        user.setLastName("123");
        user.setPassword("password");
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        return user;
    }

}
