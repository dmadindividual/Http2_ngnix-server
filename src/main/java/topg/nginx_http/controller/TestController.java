package topg.nginx_http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/file1")
    public String file1() {
        return "File 1 Response - HTTP/1.1 or HTTP/2";
    }

    @GetMapping("/file2")
    public String file2() {
        return "File 2 Response - HTTP/1.1 or HTTP/2";
    }
}