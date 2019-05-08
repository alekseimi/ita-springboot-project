package ita.springboot.application.repositoryTest;


import ita.springboot.application.model.NNetResult;
import ita.springboot.application.model.Role;
import ita.springboot.application.model.User;
import ita.springboot.application.repository.NNetResultsRepository;
import ita.springboot.application.repository.UserRepository;
import ita.springboot.application.service.NNetServiceImpl;
import org.encog.engine.network.activation.ActivationBiPolar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NNetResultsRepoTest {

    @Autowired
    NNetServiceImpl nNetService;

    @Autowired
    NNetResultsRepository nNetResultsRepository;

    @Autowired
    UserRepository userRepository;

    private User user;

    private NNetResult nNetResult;

    @Before
    public void setUp() {
        user = new User();
        user.setEmail("ted@email.com");
        user.setFirstName("Janez");
        user.setLastName("Novak");
        user.setLastName("123");
        user.setPassword("password");
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        userRepository.save(user);

        nNetResult = new NNetResult();
        nNetResult.setActivationType("TestActivation");
        nNetResult.setAverageClassificationError(20.5);
        nNetResult.setAverageValidationError(10.5);
        nNetResult.setHiddenLayersCount(10);
        nNetResult.setEpochsCount(3);
        nNetResult.setTrainingType("TrainingType");
    }

    @Test
    public void saveResultsToUser() {
        nNetResult.setUser(user);
        user.getnNetResults().add(nNetResult);
        userRepository.save(user);
        User newUser = userRepository.findByEmail("ted@email.com");
        newUser.getnNetResults();
    }

    @After
    public void clearDatabase() {
        nNetResultsRepository.deleteAll();
    }

}
