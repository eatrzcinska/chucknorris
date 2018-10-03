package pfig.chucknorris;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class JokeService {

    Set<String> jokes = new HashSet<>();
    RestTemplate restTemplate = new RestTemplate();

    public Joke showJoke() throws ApiChuckNorrisException {
        Joke joke = null;

        try {
            joke = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", Joke.class);
        } catch (Exception e) {
            throw new ApiChuckNorrisException("Server error. Please refresh the page.");
        }

        if (jokes.contains(joke.getId())) {
            return showJoke();
        } else {
            jokes.add(joke.getId());
        }
        return joke;
    }

}




