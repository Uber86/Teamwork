package TeamWork.project.rules.querys;

import TeamWork.project.repository.RecommendationRepository;

import java.util.UUID;

/**
 * Абстрактный класс AbstractQuery
 * для работы с динамическими правилами
 */
public abstract class AbstractQuery {
    private final boolean negate;


    protected AbstractQuery(boolean negate) {
        this.negate = negate;
    }

    /**
     * Метод проверки пользователя
     * @param userId уникальный идентификатор user
     * @param repository обращения к БД для поиска рекомендации
     * @return возвращает true если пользователь подходит к определенной рекомендации
     * false если не подходит под определенную рекомендацию
     */
    public Boolean perform(UUID userId, RecommendationRepository repository) {
        return negate != internalPerform(userId, repository);
    }

    /**
     * Метод проверки рекомендации пользователя
     * @param userId уникальный идентификатор user
     * @param repository обращения к БД для поиска рекомендации
     * @return метод абстрактный для реализации в классах наследниках
     */
    protected abstract boolean internalPerform(UUID userId, RecommendationRepository repository) ;
}
