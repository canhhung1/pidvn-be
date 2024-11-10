package pidvn.logger.models;

import java.util.Date;

public class FileInfo {
    private String name;
    private String type;
    private Date createdAt;
    private String path;

    public FileInfo(String name, String type, Date createdAt, String path) {
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
