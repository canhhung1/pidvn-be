package pidvn.modules.relay.material_control;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MaterialExportUtils {

    public static String createTempFile(String sourcePath, String targetPath) throws IOException {
        File source = new File(sourcePath);
        File target = new File(targetPath);
        FileUtils.copyFile(source, target);
        return targetPath;
    }

}
