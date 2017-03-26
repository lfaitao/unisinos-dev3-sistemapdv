package infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Faitão on 25/03/2017.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Hello World!";
    }

}
