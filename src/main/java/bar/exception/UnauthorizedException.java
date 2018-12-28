package bar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Permission is needed to access this service")
public class UnauthorizedException extends RuntimeException {
}
