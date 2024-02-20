package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
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
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException exception,
//                                                                     WebRequest webRequest){
//        ErrorMessage errorMsg = new ErrorMessage(
//                webRequest.getDescription(false),
//                HttpStatus.BAD_REQUEST,
//                exception.getMessage(),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
//    }
}
