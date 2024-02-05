package by.itacademy.client.controller;


import by.itacademy.client.controller.dto.StudentGroupDto;
import by.itacademy.client.domain.entity.StudentGroup;
import by.itacademy.client.service.StudentGroupService;
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
public class StudentGroupController {

    private final StudentGroupService studentGroupService;

    @GetMapping("/student-groups-page")
    public String showStudentGroupList(final Model model) {
        model.addAttribute("studentGroups", studentGroupService.findAll());
        return "student-groups-page";
    }

    @GetMapping("/student-groups/add-view")
    public String showSignUpForm(final StudentGroup studentGroup) {
        return "add-student-group";
    }

    @PostMapping("/student-groups/add")
    public String addStudentGroup(@Valid StudentGroupDto studentGroupDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student-group";
        }

        studentGroupService.save(studentGroupDto);
        return "redirect:/student-groups-page";
    }

    @GetMapping("/student-groups/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        StudentGroupDto studentGroupDto = studentGroupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student group Id:" + id));
        model.addAttribute("studentGroup", studentGroupDto);

        return "update-student-group";
    }

    @PostMapping("/student-groups/update/{id}")
    public String updateStudentGroup(@PathVariable("id") int id, @Valid StudentGroupDto studentGroupDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            studentGroupDto.setId(id);
            return "update-student-group";
        }

        studentGroupService.save(studentGroupDto);

        return "redirect:/student-groups-page";
    }

    @GetMapping("student-groups/delete/{id}")
    public String deleteStudentGroup(@PathVariable("id") int id, Model model) {
        StudentGroupDto studentGroupDto = studentGroupService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student group Id:" + id));
        studentGroupService.delete(studentGroupDto.getId());

        return "redirect:/student-groups-page";
    }

}
