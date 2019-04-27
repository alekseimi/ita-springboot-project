package ita.springboot.application.service;

import ita.springboot.application.model.NNetResults;
import ita.springboot.application.web.dto.NNetSettingsDto;
import org.encog.neural.networks.BasicNetwork;

public interface NNetService {
    NNetResults createNNetModel(NNetSettingsDto nNetSettingsDto);
}
