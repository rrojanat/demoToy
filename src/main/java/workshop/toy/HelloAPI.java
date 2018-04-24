package workshop.toy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAPI {

    @GetMapping("/hello/{name}")
    public Message sayHi(@PathVariable String name) {
        Message message = new Message();
        message.name = "Hello " + name;
        return message;
    }
}

class Message {
    public String name;
}
