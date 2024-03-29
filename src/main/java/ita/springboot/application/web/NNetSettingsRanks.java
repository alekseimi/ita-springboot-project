package ita.springboot.application.web;

import ita.springboot.application.model.NNetResult;
import ita.springboot.application.repository.NNetResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/nnresultsranks")
public class NNetSettingsRanks {

    @Autowired
    NNetResultsRepository nNetResultsRepository;

    @GetMapping
    public String showResultsList(Model model) {
        model.addAttribute("nnetresults", sortDBResults(nNetResultsRepository.findAll()));
        return "nnresultsranks";
    }

    public List<NNetResult> sortDBResults(List<NNetResult> listToSort) {
        listToSort.sort(Comparator.comparing(NNetResult::getAverageClassificationError));
        return listToSort;
    }
}
