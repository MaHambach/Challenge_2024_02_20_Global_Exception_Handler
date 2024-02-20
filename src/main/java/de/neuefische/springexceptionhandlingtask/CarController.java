package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new IllegalArgumentException("Only 'porsche' allowed");
        }
        return brand;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception,
                                                                       WebRequest webRequest){
        ErrorMessage errorMsg = new ErrorMessage(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    String getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }
}
