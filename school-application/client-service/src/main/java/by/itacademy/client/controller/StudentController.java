package by.itacademy.client.controller;


import by.itacademy.client.controller.dto.StudentDto;
import by.itacademy.client.domain.entity.Student;
import by.itacademy.client.service.StudentService;
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
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students-page")
    public String showStudentList(final Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students-page";
    }

    @GetMapping("/students/add-view")
    public String showSignUpForm(final Student student) {
        return "add-student";
    }

    @PostMapping("/students/add")
    public String addStudent(@Valid StudentDto studentDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentService.save(studentDto);
        return "redirect:/students-page";
    }

    @GetMapping("/students/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        StudentDto studentDto = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        model.addAttribute("student", studentDto);

        return "update-student";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @Valid StudentDto studentDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            studentDto.setId(id);
            return "update-student";
        }

        studentService.save(studentDto);

        return "redirect:/students-page";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        StudentDto studentDto = studentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentService.delete(studentDto.getId());

        return "redirect:/students-page";
    }

}
