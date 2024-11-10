package pidvn.logger.services;


import org.springframework.stereotype.Service;
import pidvn.logger.models.FileInfo;
import pidvn.logger.models.SearchDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PidvnLoggerSvcImpl implements PidvnLoggerSvc {

    public final String ROOT_DIR = "D:\\IS\\CanhHung\\FDCS\\LogServer\\";

    @Override
    public List<FileInfo> getLogFiles(String path) {


        List<FileInfo> fileInfos = new ArrayList<>();
        File rootDir = new java.io.File(path);

        // Kiểm tra nếu là thư mục
        if (rootDir.exists() && rootDir.isDirectory()) {
            // Lấy danh sách các tệp và thư mục trong thư mục gốc
            File[] files = rootDir.listFiles();
            if (files != null) {
                for (java.io.File file : files) {
                    try {
                        // Lấy thông tin thời gian tạo
                        BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                        Date createdAt = new Date(attrs.creationTime().toMillis());

                        // Xác định loại (thư mục hoặc tệp tin)
                        String type = file.isDirectory() ? "Directory" : "File";

                        // Thêm vào danh sách với tên, loại và thời gian tạo
                        fileInfos.add(new FileInfo(file.getName(), type, createdAt, file.getPath()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        fileInfos.sort(Comparator.comparing(FileInfo::getCreatedAt).reversed());
        return fileInfos;
    }

    @Override
    public String readLogFileContent(SearchDto searchDto) throws IOException {
        Path filePath = Paths.get(searchDto.getPath(), searchDto.getFileName()).normalize();

        // Kiểm tra sự tồn tại của file và đảm bảo file có đuôi ".log"
        if (Files.exists(filePath) && Files.isRegularFile(filePath) && searchDto.getFileName().endsWith(".log")) {
            // Đọc nội dung file vào mảng byte và chuyển sang String
            byte[] fileBytes = Files.readAllBytes(filePath);
            return new String(fileBytes, "UTF-8"); // Chuyển đổi mảng byte thành chuỗi với mã hóa UTF-8
        } else {
            throw new IOException("File không tồn tại hoặc không phải là file log hợp lệ.");
        }
    }
}
