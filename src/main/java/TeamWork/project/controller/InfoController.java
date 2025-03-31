package TeamWork.project.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {
    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${project.version}")
    private String serviceVersion;

    @GetMapping("/management/info")
    public Map<String, String> getServiceInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", serviceName);
        info.put("version", serviceVersion);
        return info;
    }
}