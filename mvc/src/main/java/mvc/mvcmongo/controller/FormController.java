package mvc.mvcmongo.controller;

import mvc.mvcmongo.model.Form;
import mvc.mvcmongo.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @Autowired
    FormRepository formRepository;

    @RequestMapping("/users")
    public String user(Model model) {
        model.addAttribute("users", formRepository.findAll());
        return "users";
    }

    @RequestMapping("/form")
    public String form(Model model)
    {
        return "index";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String phoneNo,@RequestParam String place) {
        Form form = new Form();
        form.setFirstName(firstName);
        form.setLastName(lastName);
        form.setAddress(address);
        form.setPhoneNo(phoneNo);
        form.setPlace(place);
        formRepository.save(form);

        return "redirect:/welcome/" + form.getId();
    }

    @RequestMapping("/welcome/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("user", formRepository.findById(id).get());
        return "welcome";
    }

}
