package TeamWork.project.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_text")
    private String productText;

    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Query> queries;

    public Rule(Long id, String productName, UUID productId, String productText, List<Query> queries) {
        this.id = id;
        this.productName = productName;
        this.productId = productId;
        this.productText = productText;
        this.queries = queries;
    }

    public Rule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }
}
