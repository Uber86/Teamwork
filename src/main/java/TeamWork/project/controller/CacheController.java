package TeamWork.project.controller;

import TeamWork.project.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class CacheController {

    private final CacheService cacheService;

    @Autowired
    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/clear-caches")
    public ResponseEntity<String> clearCaches() {
        try {
            cacheService.clearAllCaches();
            return new ResponseEntity<>("Кеш успешно очищен", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка при очистке кеша: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}