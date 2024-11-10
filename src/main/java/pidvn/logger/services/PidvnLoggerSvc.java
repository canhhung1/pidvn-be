package pidvn.logger.services;

import pidvn.logger.models.FileInfo;
import pidvn.logger.models.SearchDto;

import java.io.IOException;
import java.util.List;

public interface PidvnLoggerSvc {
    List<FileInfo> getLogFiles(String path);
    String readLogFileContent(SearchDto searchDto) throws IOException;
}
