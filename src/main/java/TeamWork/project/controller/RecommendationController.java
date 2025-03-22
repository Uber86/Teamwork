package TeamWork.project.controller;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable UUID userId) {

        List<Recommendation> recommendations = recommendationService.getRecommendation(userId);
        return ResponseEntity.ok(recommendations);
    }




}
