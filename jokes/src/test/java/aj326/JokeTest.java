package aj326;


import org.junit.Test;

import io.github.libs.aj326.Joke;


public class JokeTest {

    @Test
    public void testJokes(){
        assert(Joke.getJoke() != null);
    }


}