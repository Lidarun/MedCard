package kg.medcard.nur.web;

import jakarta.validation.Valid;
import kg.medcard.nur.enums.Gender;
import kg.medcard.nur.models.Employee;
import kg.medcard.nur.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    final EmployeeService employeeService;
    private Employee empl = new Employee();

    public DashboardController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String greeting() {
        return "dashboard";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("value", Gender.values());
        model.addAttribute("employeeForm", new Employee());
        return "register";
    }

    @GetMapping("/info")
    public String getInfo(Model model){
        model.addAttribute("value", Gender.values());
        if(empl != null) model.addAttribute(empl);
        return "info";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("employeeForm") @Valid Employee employee,
                               BindingResult bindingResult , Model model){
        model.addAttribute("value", Gender.values());

        ObjectError error = employeeService.validPassword(employee.getPassword(), employee.getConfirmPassword());
        if (error != null) bindingResult.addError(error);

        if(bindingResult.hasErrors()) return "register";

        model.addAttribute(employee);
        empl = employee;

        return "redirect:/dashboard/info";
    }

}
