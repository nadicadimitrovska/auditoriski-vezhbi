package mk.ukim.finki.auditoriskivezhbi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super(String.format("Product with id: %d was not found",id));
    }
}
