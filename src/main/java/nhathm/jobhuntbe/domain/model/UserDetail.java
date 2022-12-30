package nhathm.jobhuntbe.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @author nhathm on 2022-12-23
 */
@Data
public class UserDetail {
    private String userId;
    private String fullName;
    private String email;
    private String bio;
    private String avatarUrl;
    private String location;

    private String primaryRole;
    private List<String> roles;

    private List<String> skills;

    private String website;
    private String linkedIn;
    private String github;
    private String twitter;
}
