package by.itacademy.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> calculate(
            @RequestParam(name = "a", defaultValue = "0") final int a,
            @RequestParam(name = "b", defaultValue = "0") final int b
    ) {
        return new ResponseEntity<>(String.valueOf(a + b), HttpStatus.CREATED);
    }
}
