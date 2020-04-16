package pub.imba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {



    @RequestMapping({"/login","/"})
    public String login(){
        return "login";
    }

    @RequestMapping({"/main"})
    public String main(){
        return "index";
    }

    @RequestMapping("/hotshop")
    public String hotshop(){
        return "hotshop";
    }

    @RequestMapping("/collection")
    public String collection(){
        return "collection";
    }

    @RequestMapping("/commodity")
    public String commodity(){
        return "commodity";
    }
}
