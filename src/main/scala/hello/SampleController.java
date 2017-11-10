package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class SampleController {

    // HATEOS

    @RequestMapping("/name/{name}")
    @ResponseBody
    String helloName(@PathVariable("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/echo")
    @GetMapping
    @ResponseBody
    Map<String, String> echoName(@RequestParam("name") String name) {
        HashMap<String, String> m  = new HashMap();
        m.put("Hello",name);
        return m;
    }


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/testclient")
    @ResponseBody
    String testClient() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/echo?name=hari", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> m = objectMapper.readValue(response, HashMap.class);
        return m.get("Hello");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}