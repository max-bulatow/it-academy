package by.itacademy.client.controller;


import by.itacademy.client.controller.dto.TeacherDto;
import by.itacademy.client.domain.entity.Teacher;
import by.itacademy.client.service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@RequestMapping("/")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/teachers-page")
    public String showTeacherList(final Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers-page";
    }

    @GetMapping("/teachers/add-view")
    public String showSignUpForm(final Teacher teacher) {
        return "add-teacher";
    }

    @PostMapping("/teachers/add")
    public String addTeacher(@Valid TeacherDto teacherDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-teacher";
        }

        teacherService.save(teacherDto);
        return "redirect:/teachers-page";
    }

    @GetMapping("/teachers/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        TeacherDto teacherDto = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Teacher Id:" + id));
        model.addAttribute("teacher", teacherDto);

        return "update-teacher";
    }

    @PostMapping("/teachers/update/{id}")
    public String updateTeacher(@PathVariable("id") int id, @Valid TeacherDto teacherDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            teacherDto.setId(id);
            return "update-teacher";
        }

        teacherService.save(teacherDto);

        return "redirect:/teachers-page";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id, Model model) {
        TeacherDto teacherDto = teacherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        teacherService.delete(id);

        return "redirect:/teachers-page";
    }

}
