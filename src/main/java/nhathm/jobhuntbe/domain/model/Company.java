package nhathm.jobhuntbe.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author nhathm on 2022-12-23
 */
@Data
@Document("Company")
public class Company {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String logoUrl;
    @NotNull
    private String website;
    @NotNull
    private String location;
    @NotEmpty
    List<String> markets;
    @NotNull
    private String companySize;
    private String description;

    @LastModifiedDate
    private Date updateAt;
    @CreatedDate
    private Date createdAt;

}
