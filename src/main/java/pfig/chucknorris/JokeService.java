package pfig.chucknorris;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class JokeService {

    Set<Joke> jokes = new HashSet<>();
    RestTemplate restTemplate = new RestTemplate();

    public Joke showJoke() {
        Joke joke = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", Joke.class);

        for (Joke e : jokes) {
            if (e.hashCode() == joke.hashCode()) {
                if (e.equals(joke)) {
                    showJoke();
                } else {
                    jokes.add(joke);
                    return joke;
                }
            }
        }
        jokes.add(joke);
        return joke;

        //Tak próbowałam przed Twoją poranną wiadomością :)
/*        if(jokes.contains(joke)){
            return showJoke();
        } else {
        jokes.add(joke);*/

    }
}




