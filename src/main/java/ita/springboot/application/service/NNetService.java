package ita.springboot.application.service;

import ita.springboot.application.model.NNetResult;
import ita.springboot.application.web.dto.NNetSettingsDto;
import org.encog.neural.networks.BasicNetwork;

public interface NNetService {
    NNetResult createNNetModel(NNetSettingsDto nNetSettingsDto);
}
