package ita.springboot.application.web;

import ita.springboot.application.model.NNetResult;
import ita.springboot.application.repository.NNetResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/nnresultsranks")
public class NNetSettingsRanks {

    @Autowired
    NNetResultsRepository nNetResultsRepository;

    @GetMapping
    public String showResultsList(Model model) {

        List<NNetResult> nNetResultsRepositoryAll = nNetResultsRepository.findAll();

        return null;
    }


}
