package pocketmoneybook.presentation.controller.helloworld;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorldController {

    static final String HELLO_WORLD = "Hello, world.";
    private static final int BUFFER_SIZE = 1024;

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    String byString() {
        return HELLO_WORLD;
    }

    @RequestMapping(value = "/byte", method = RequestMethod.GET)
    byte[] byBytes() {
        return HELLO_WORLD.getBytes();
    }

    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    ResponseEntity<InputStreamResource> byStream() {
        final InputStreamResource inputStreamResource = new InputStreamResource(
                new BufferedInputStream(new ByteArrayInputStream(HELLO_WORLD.getBytes()), BUFFER_SIZE)
        );

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(HELLO_WORLD.getBytes().length);

        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }

}