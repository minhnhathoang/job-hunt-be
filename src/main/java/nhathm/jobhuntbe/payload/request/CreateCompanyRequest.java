package nhathm.jobhuntbe.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author nhathm
 */
@Data
public class CreateCompanyRequest {
    private String userId;
    @NotNull
    private String companyRoleOfUser;
    @NotNull
    private String logoUrl;
    @NotNull
    private String name;
    @NotNull
    private String website;
    @NotNull
    private String location;
    @NotEmpty
    List<String> markets;
    @NotNull
    private String companySize;
    private String description;
}
