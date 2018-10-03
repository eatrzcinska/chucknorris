package pfig.chucknorris;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JokeController {
    private JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/")
    public String showJoke(Model model) throws ApiChuckNorrisException {
       Joke joke = jokeService.showJoke();
         model.addAttribute("joke",joke);
        return "indexTemplate";
    }

    @ExceptionHandler({ApiChuckNorrisException.class})
    public String handleException() {
        return "error";
    }
}
