package Api;

import lombok.Builder;
import lombok.Data;

public @Data @Builder class UserCredentials {
    private String email;
    private String password;
}
