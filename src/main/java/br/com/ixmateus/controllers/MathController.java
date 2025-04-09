package br.com.ixmateus.controllers;

import br.com.ixmateus.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public double subtract (@PathVariable String numberOne,
                            @PathVariable String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }
    @RequestMapping("/mul/{numberOne}/{numberTwo}")
    public double multiply (@PathVariable String numberOne,
                            @PathVariable String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }
    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public double divide (@PathVariable String numberOne,
                            @PathVariable String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public double mean (@PathVariable String numberOne,
                        @PathVariable String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) /2;
    }

    @RequestMapping("/square-root/{number}")
    public double squareRoot (@PathVariable ("number") String number )throws Exception {
        if (!isNumeric(number)) throw new UnsupportedMathOperationException("Please set a numeric value!");
        // Double result = Math.sqrt(convertToDouble(number));
        // return result;
        return Math.sqrt(convertToDouble(number));
    }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value!");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }
    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",","."); // BRL R$ 5,00 || USD $ 5.0
        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }

}
