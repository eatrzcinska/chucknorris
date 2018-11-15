package pfig.chucknorris.sevice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pfig.chucknorris.exception.ApiChuckNorrisException;
import pfig.chucknorris.model.Joke;

import java.util.HashSet;
import java.util.Set;

@Service
public class JokeService {

    private Set<String> jokes = new HashSet<>();
    private RestTemplate restTemplate = new RestTemplate();

    public Joke showJoke() throws ApiChuckNorrisException {
        Joke joke = null;

        do {
            try {
                joke = restTemplate.getForObject("https://api.chucknorris.io/jokes/random", Joke.class);
            } catch (Exception e) {
                throw new ApiChuckNorrisException("Server error. Please refresh the page.", e.getCause());
            }
        } while (!jokes.add(joke.getId()));

        return joke;
    }
}




