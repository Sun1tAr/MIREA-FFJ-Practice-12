package my.learn.mireaffjpractice12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

public interface HealthController {

    @GetMapping("/health")
    default ResponseEntity<?> health() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
        return ResponseEntity.ok().body(map);
    }

}
