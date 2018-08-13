package judgeSystem.domain.dto.userImport;

import com.google.gson.annotations.Expose;

public class UserImportJSON {

    @Expose
    private String username;

    public UserImportJSON() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
}
