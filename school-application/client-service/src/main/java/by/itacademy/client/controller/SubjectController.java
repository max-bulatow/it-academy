package by.itacademy.client.controller;


import by.itacademy.client.controller.dto.SubjectDto;
import by.itacademy.client.domain.entity.Subject;
import by.itacademy.client.service.SubjectService;
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
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subjects-page")
    public String showSubjectList(final Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects-page";
    }

    @GetMapping("/subjects/add-view")
    public String showSignUpForm(final Subject subject) {
        return "add-subject";
    }

    @PostMapping("/subjects/add")
    public String addSubject(@Valid SubjectDto subjectDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-subject";
        }

        subjectService.save(subjectDto);
        return "redirect:/subjects-page";
    }

    @GetMapping("/subjects/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        SubjectDto subjectDto = subjectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Subject Id:" + id));
        model.addAttribute("subject", subjectDto);

        return "update-subject";
    }

    @PostMapping("/subjects/update/{id}")
    public String updateSubject(@PathVariable("id") int id, @Valid SubjectDto subjectDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            subjectDto.setId(id);
            return "update-subject";
        }

        subjectService.save(subjectDto);

        return "redirect:/subjects-page";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable("id") int id, Model model) {
        SubjectDto subjectDto = subjectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Subject Id:" + id));
        subjectService.delete(subjectDto.getId());

        return "redirect:/subjects-page";
    }

}
