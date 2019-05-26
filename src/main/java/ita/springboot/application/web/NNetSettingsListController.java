package ita.springboot.application.web;

import ita.springboot.application.model.User;
import ita.springboot.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nnresultslist")
public class NNetSettingsListController {

    @Autowired
    UserRepository userRepository;

    /*
        @GetMapping
    public String showSettingsDetails(Model model){
        return "";
    }
     */

    @GetMapping
    public String showSettingsList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("nnetresults", user.getnNetResults());
        return "nnresultslist";
    }

}
