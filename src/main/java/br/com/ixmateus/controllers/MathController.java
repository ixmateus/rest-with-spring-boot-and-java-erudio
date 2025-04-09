package br.com.ixmateus.controllers;

import br.com.ixmateus.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public double sum(@PathVariable String numberOne, @PathVariable String numberTwo) {
        return calculate(numberOne, numberTwo, Operation.SUM);
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public double subtract(@PathVariable String numberOne, @PathVariable String numberTwo) {
        return calculate(numberOne, numberTwo, Operation.SUBTRACT);
    }

    @GetMapping("/mul/{numberOne}/{numberTwo}")
    public double multiply(@PathVariable String numberOne, @PathVariable String numberTwo) {
        return calculate(numberOne, numberTwo, Operation.MULTIPLY);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public double divide(@PathVariable String numberOne, @PathVariable String numberTwo) {
        return calculate(numberOne, numberTwo, Operation.DIVIDE);
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public double mean(@PathVariable String numberOne, @PathVariable String numberTwo) {
        return calculate(numberOne, numberTwo, Operation.MEAN);
    }

    @GetMapping("/sqrt/{number}")
    public double squareRoot(@PathVariable String number) {
        validateNumeric(number);
        return Math.sqrt(parse(number));
    }

    // ENUM para centralizar as operações
    private enum Operation {
        SUM, SUBTRACT, MULTIPLY, DIVIDE, MEAN
    }

    // Metodo genérico que aplica a operação
    private double calculate(String numberOne, String numberTwo, Operation op) {
        validateNumeric(numberOne, numberTwo);
        double n1 = parse(numberOne);
        double n2 = parse(numberTwo);

        return switch (op) {
            case SUM -> n1 + n2;
            case SUBTRACT -> n1 - n2;
            case MULTIPLY -> n1 * n2;
            case DIVIDE -> n1 / n2;
            case MEAN -> (n1 + n2) / 2;
        };
    }

    private void validateNumeric(String... numbers) {
        for (String num : numbers) {
            if (num == null || !num.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                throw new UnsupportedMathOperationException("Please set a numeric value!");
            }
        }
    }

    private double parse(String number) {
        return Double.parseDouble(number.replace(",", "."));
    }
}
