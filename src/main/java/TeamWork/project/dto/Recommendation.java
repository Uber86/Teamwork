package TeamWork.project.dto;

import java.util.UUID;

public class Recommendation {
    private UUID id;
    private String name;
    private String text;

    public Recommendation( String name, UUID id, String text) {
        this.name = name;
        this.id = id;
        this.text = text;
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
