package my.learn.mireaffjpractice12.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public abstract class AppException extends RuntimeException {

    /*
    * В идеале кроме статуса передавать код внутренней ошибки, чтобы не декапсулировать внутреннюю структуру сервиса
    * Для учебного проекта достаточно пробрасывать сообщение
    * На реальном проекте - так делать нельзя
    * */
    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
