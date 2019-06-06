package ita.springboot.application.service;

import ita.springboot.application.model.NNetResult;
import ita.springboot.application.model.User;
import ita.springboot.application.model.nnet.HandwrittenNN;
import ita.springboot.application.model.nnet.InvalidFileFormatException;
import ita.springboot.application.repository.NNetResultsRepository;
import ita.springboot.application.repository.UserRepository;
import ita.springboot.application.web.dto.NNetSettingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class NNetServiceImpl implements NNetService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NNetResultsRepository nNetResultsRepository;

    @Override
    public NNetResult createNNetModel(NNetSettingsDto nNetSettingsDto) {

        HandwrittenNN handwrittenNN = new HandwrittenNN();
        handwrittenNN.setEpochsCount(nNetSettingsDto.getEpochsCount())
                .setIterationsCount(nNetSettingsDto.getIterationCount())
                .setActivationType(nNetSettingsDto.getActivationType())
                .setTrainingType(nNetSettingsDto.getTrainingType())
                .setHiddenLayerCount(nNetSettingsDto.getHiddenLayerCount())
                .setHiddenLayerNeuronCount(nNetSettingsDto.getHiddenLayerNeuronCount());
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            NNetResult nNetResults = handwrittenNN.createModel();
            User user = userRepository.findByEmail(userDetails.getUsername());
            nNetResults.setUser(user);
            user.getnNetResults().add(nNetResults);
            userRepository.save(user);
            return nNetResults;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
