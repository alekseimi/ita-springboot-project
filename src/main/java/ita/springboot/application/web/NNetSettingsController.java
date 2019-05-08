package ita.springboot.application.web;


import ita.springboot.application.model.NNetResult;
import ita.springboot.application.service.NNetService;
import ita.springboot.application.service.NNetServiceImpl;
import ita.springboot.application.web.dto.NNetSettingsDto;
import org.encog.neural.networks.BasicNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/nnetsettings")
public class NNetSettingsController {

    @Autowired
    private NNetService nNetService;

    @ModelAttribute("nnsettings")
    public NNetSettingsDto nNetSettingsDto() {
        return new NNetSettingsDto();
    }

    @GetMapping
    public String showSettingsForm(Model model) {
        return "nnetsettings";
    }

    @PostMapping
    public String createNeuralNetwork(@ModelAttribute("nnsettings") @Valid NNetSettingsDto nNetSettingsDto,
                                      BindingResult result) {
        nNetService.createNNetModel(nNetSettingsDto);
        return "index";
    }
}
