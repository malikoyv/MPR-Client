package pjwstk.edu.pl.mpr.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(CatNotFoundException.class)
//    protected ResponseEntity<Object> CatNotFoundException(CatNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(CatIsNullException.class)
//    protected ResponseEntity<Object> CatIsNullException(CatIsNullException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(EmptyString.class)
    public String handleEmptyStringException(EmptyString ex, RedirectAttributes ra){
        ra.addFlashAttribute("error", ex.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, RedirectAttributes ra){
        ra.addFlashAttribute("error", "An unexpected error occurred: " + ex.getMessage());
        return "redirect:/";
    }
}
