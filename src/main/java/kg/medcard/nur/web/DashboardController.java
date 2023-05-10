package kg.medcard.nur.web;

import jakarta.validation.Valid;
import kg.medcard.nur.domain.enums.Gender;
import kg.medcard.nur.domain.model.Employee;
import kg.medcard.nur.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final EmployeeService employeeService;
    private Employee empl = new Employee();//ex

    //GET
    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("employees", employeeService.findAll());
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
        if (empl == null) empl.setGender(Gender.FEMALE);
        return "info";
    }

//    @GetMapping()
//    public String getAllEmployees(Model model) {
//        model.addAttribute("employees", employeeService.findAll());
//        return "dashboard";
//    }

    //POST
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("employeeForm") @Valid Employee employee,
                               BindingResult bindingResult , Model model){
        model.addAttribute(employee);
        model.addAttribute("value", Gender.values());

        ObjectError errorEmail = employeeService.existEmployee(employee.getEmail());
        if (errorEmail != null) bindingResult.addError(errorEmail);

        ObjectError errorConfirmPassword = employeeService
                .comparePassword(employee.getPassword(), employee.getConfirmPassword());
        if (errorConfirmPassword != null) bindingResult.addError(errorConfirmPassword);

        ObjectError errorPassword = employeeService.validPassword(employee.getPassword());
        if (errorPassword != null) bindingResult.addError(errorPassword);

        if(bindingResult.hasErrors()) return "register";

        empl = employee;

        employeeService.create(employee);
        return "redirect:/dashboard/info";
    }

}
