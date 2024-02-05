package by.itacademy.client.controller;


import by.itacademy.client.controller.dto.AssessmentDto;
import by.itacademy.client.domain.entity.Assessment;
import by.itacademy.client.service.AssessmentService;
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
public class AssessmentController {

    private final AssessmentService assessmentService;

    @GetMapping("/assessments-page")
    public String showAssessmentList(final Model model) {
        model.addAttribute("assessments", assessmentService.findAll());
        return "assessments-page";
    }

    @GetMapping("/assessments/add-view")
    public String showSignUpForm(final Assessment assessment) {
        return "add-assessment";
    }

    @PostMapping("/assessments/add")
    public String addAssessment(@Valid AssessmentDto assessmentDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-assessment";
        }

        assessmentService.save(assessmentDto);
        return "redirect:/assessments-page";
    }

    @GetMapping("/assessments/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        AssessmentDto assessmentDto = assessmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid assessment Id:" + id));
        model.addAttribute("assessment", assessmentDto);

        return "update-assessment";
    }

    @PostMapping("/assessments/update/{id}")
    public String updateAssessment(@PathVariable("id") int id, @Valid AssessmentDto assessmentDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            assessmentDto.setId(id);
            return "update-assessment";
        }

        assessmentService.save(assessmentDto);

        return "redirect:/assessments-page";
    }

    @GetMapping("/assessments/delete/{id}")
    public String deleteAssessment(@PathVariable("id") int id, Model model) {
        AssessmentDto assessmentDto = assessmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid assessment Id:" + id));
        assessmentService.delete(assessmentDto.getId());

        return "redirect:/assessments-page";
    }

}
