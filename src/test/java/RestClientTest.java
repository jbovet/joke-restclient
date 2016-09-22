/**
 * Copyright (C) 2016  Jose P. Bovet Derpich (jose.bovet@gmail.com)
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import cl.tuxy.client.Joke;
import cl.tuxy.client.JokeResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class RestClientTest {

    final static String BASE_API = "http://api.icndb.com/jokes";

    RestTemplate restTemplate;

    @Before
    public void setup() {
        assertNotNull(BASE_API);
    }

    @Before
    public void setup2() {
        this.restTemplate = new RestTemplate();
    }

    @Test
    public void testConnection() {
        assertNotNull(restTemplate.getForObject(BASE_API, String.class));
    }

    @Test(expected = UnknownHostException.class)
    public void testUnknownHost() throws UnknownHostException {
        try {
            assertNotNull(restTemplate.getForObject("http://bbr.bbr", String.class));
        } catch (Exception e) {
            throw new UnknownHostException(e.getMessage());
        }
    }

    @Test
    public void testAPI() {
        JokeResponse response = restTemplate.getForObject(BASE_API, JokeResponse.class);
        List<Joke> jokes = response.getValue();
        assertNotNull(response);
        assertEquals(response.getType(), "success");
        assertNotNull(jokes);
        assertTrue(jokes.size() > 0);
    }

    @Test
    public void testAPIQueryParams() {
        String query = "/random/{limit}?limitTo=[{category}]";

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("category", "nerdy");

        JokeResponse response = restTemplate.getForObject(BASE_API + query, JokeResponse.class, params);
        List<Joke> jokes = response.getValue();
        assertNotNull(response);
        assertEquals(response.getType(), "success");
        assertNotNull(jokes);
        assertEquals(jokes.size(), 10);
    }

    @Test
    public void testAPIQueryParams2() {
        String query = "/random/{limit}?limitTo=[{category}]";

        JokeResponse response = restTemplate.getForObject(BASE_API + query, JokeResponse.class, 3, "nerdy");
        List<Joke> jokes = response.getValue();
        assertEquals(jokes.size(), 3);
    }

    @Test
    public void testPrintJokes() {
        String query = "/random/{limit}?limitTo=[{category}]";

        JokeResponse response = restTemplate.getForObject(BASE_API + query, JokeResponse.class, 3, "nerdy");
        List<Joke> jokes = response.getValue();
        for (Joke joke : jokes) {
            System.out.println(joke);
        }
    }

    @Test
    public void testSpecificJoke() {
        String query = "/jokes/{id}";
        JokeResponse response = restTemplate.getForObject(BASE_API + query, JokeResponse.class, 1);
        assertEquals("Chuck Norris uses ribbed condoms inside out, so he gets the pleasure.", response.getValue().get(0).getJoke());
    }

}
