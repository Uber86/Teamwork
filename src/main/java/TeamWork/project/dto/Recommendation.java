package TeamWork.project.dto;

import java.util.UUID;

public class Recommendation {
    private UUID id;
    private String name;
    private String text;

    public Recommendation(UUID id, String text, String name) {
        this.id = id;
        this.text = text;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }


}
