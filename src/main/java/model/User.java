package model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Builder
public class User {
    public String email;
    public String password;
    public String name;

    public static User getRandom() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@google.com";
        final String password = RandomStringUtils.randomAlphabetic(6);
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new User(email, password, name);
    }
}

