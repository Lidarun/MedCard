package kg.medcard.nur.web;

import jakarta.validation.Valid;
import kg.medcard.nur.domain.enums.Gender;
import kg.medcard.nur.domain.model.Employee;
import kg.medcard.nur.service.EmployeeService;
import kg.medcard.nur.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final EmployeeService employeeService;
    private Employee empl = new Employee();//ex

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

        if (employeeService.isExists(empl.getEmail())){
            ObjectError err = new ObjectError("global", "Сотрудник с данным email существует");
            bindingResult.addError(err);
        }

        if(bindingResult.hasErrors()) return "register";

//        model.addAttribute(employee);
        empl = employee;

        employeeService.create(employee);
        return "redirect:/dashboard/info";
    }

}
