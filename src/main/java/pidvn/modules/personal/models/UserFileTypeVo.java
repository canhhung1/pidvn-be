package pidvn.modules.personal.models;

import pidvn.entities.one.UserFile;

import java.util.List;

public class UserFileTypeVo {
    private String name;
    private List<UserFile> files;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserFile> getFiles() {
        return files;
    }

    public void setFiles(List<UserFile> files) {
        this.files = files;
    }
}
