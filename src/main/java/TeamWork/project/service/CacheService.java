package TeamWork.project.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {

    private final Map<String, Cache<Object, Object>> caches;

    public CacheService(Map<String, Cache<Object, Object>> caches) {
        this.caches = caches;
    }

    public void clearAllCaches() {

        caches.values().forEach(Cache::invalidateAll);


        System.out.println("Все кеши очищены");
    }
}