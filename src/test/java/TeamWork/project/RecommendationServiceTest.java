package TeamWork.project;

import TeamWork.project.dto.Recommendation;
import TeamWork.project.rules.RecommendationRuleSet;
import TeamWork.project.service.RecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecommendationServiceTest {

    @Mock
    private RecommendationRuleSet ruleSet1;
    @Mock
    private RecommendationRuleSet ruleSet2;
    @InjectMocks
    private RecommendationService recommendationService;
    @BeforeEach
    public void setUp() {
        ruleSet1 = Mockito.mock(RecommendationRuleSet.class);
        ruleSet2 = Mockito.mock(RecommendationRuleSet.class);

        List<RecommendationRuleSet> ruleSets = Arrays.asList(ruleSet1, ruleSet2);
        recommendationService = new RecommendationService(ruleSets);
    }

    @Test
    public void testGetRecommendationReturnsNull() {
        UUID userId = UUID.randomUUID();

        when(ruleSet1.perform(any(UUID.class))).thenReturn(Optional.empty());
        when(ruleSet2.perform(any(UUID.class))).thenReturn(Optional.empty());

        List<Recommendation> recommendations = recommendationService.getRecommendation(userId);

        assertEquals(Collections.emptyList(), recommendations);
    }

    @Test
    public void testGetRecommendationReturnsTrue() {
        UUID userId = UUID.randomUUID();
        Recommendation recommendation1 = new Recommendation("recommendation 1", UUID.randomUUID(), "text 1");
        Recommendation recommendation2 = new Recommendation("recommendation 2", UUID.randomUUID(), "text 2");

        when(ruleSet1.perform(userId)).thenReturn(Optional.of(recommendation1));
        when(ruleSet2.perform(userId)).thenReturn(Optional.of(recommendation2));

        List<Recommendation> recommendations = recommendationService.getRecommendation(userId);

        assertEquals(recommendation1, recommendations.get(0));
        assertEquals(recommendation2, recommendations.get(1));

    }

}
