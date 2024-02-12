package pl.kurs.exchange_api_micro.event;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailEvent<T> extends ApplicationEvent {

    private final T data;

    public EmailEvent(T event) {
        super(event);
        this.data = event;
    }
}
