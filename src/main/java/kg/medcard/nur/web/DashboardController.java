package kg.medcard.nur.web;

import jakarta.validation.Valid;
import kg.medcard.nur.models.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String greeting() {
        return "dashboard";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("employee", new Employee());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @Valid @ModelAttribute Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "register";

        model.addAttribute(employee);
        return "info";
    }
}
