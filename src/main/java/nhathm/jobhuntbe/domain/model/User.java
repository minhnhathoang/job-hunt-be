package nhathm.jobhuntbe.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author nhathm on 2022-12-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User {
    @Id
    private String id;
    private String fullName;
    private String email;
    @JsonIgnore
    private String password;

    private String bio;
    private String avatarUrl;
    private String location;

    private String primaryRoles;
    private List<String> roles;

    private List<String> skills;

    private String website;
    private String linkedIn;
    private String github;
    private String twitter;

    private String companyId;
    private String companyRole;

    private String fcmToken;
    private String token;
}
