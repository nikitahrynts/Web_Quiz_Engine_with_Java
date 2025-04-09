package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebQuizEngine extends Thread {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

}
