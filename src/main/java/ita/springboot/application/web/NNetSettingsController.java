package ita.springboot.application.web;


import ita.springboot.application.web.dto.NNetSettingsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nnetsettings")
public class NNetSettingsController {

    @ModelAttribute("nnetsettingsdto")
    public NNetSettingsDto nNetSettingsDto() {
        return new NNetSettingsDto();
    }

    @GetMapping
    public String showSettingsForm() {
        return "nnetsettings";
    }
}
