package ita.springboot.application.service;

import ita.springboot.application.model.NNetResults;
import ita.springboot.application.model.nnet.HandwrittenNN;
import ita.springboot.application.model.nnet.InvalidFileFormatException;
import ita.springboot.application.web.dto.NNetSettingsDto;
import org.encog.neural.networks.BasicNetwork;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class NNetServiceImpl implements NNetService {

    @Override
    public NNetResults createNNetModel(NNetSettingsDto nNetSettingsDto) {

        HandwrittenNN handwrittenNN = new HandwrittenNN();
        handwrittenNN.setEpochsCount(nNetSettingsDto.getEpochsCount())
                .setIterationsCount(nNetSettingsDto.getIterationCount())
                .setActivationType(nNetSettingsDto.getActivationType())
                .setTrainingType(nNetSettingsDto.getTrainingType())
                .setHiddenLayerCount(nNetSettingsDto.getHiddenLayerCount())
                .setHiddenLayerNeuronCount(nNetSettingsDto.getHiddenLayerNeuronCount());
        try {
            NNetResults nNetResults = handwrittenNN.createModel();
            return nNetResults;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
