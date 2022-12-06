package pidvn.modules.hr.user_file.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidvn.mappers.one.hr.user_file.HrUserFileMapper;
import pidvn.modules.hr.user_file.models.UserFileVo;
import pidvn.modules.hr.user_file.models.UserVo;
import pidvn.repositories.one.UserFileRepo;

import java.io.File;
import java.util.List;

@Service
public class HrUserFileSvc implements IHrUserFileSvc {

    Logger logger = LoggerFactory.getLogger(HrUserFileSvc.class);

    @Autowired
    private HrUserFileMapper hrUserFileMapper;

    @Autowired
    private UserFileRepo userFileRepo;

    @Override
    public List<UserVo> getUsers() {
        return this.hrUserFileMapper.getUsers();
    }

    @Override
    public List<UserFileVo> getUserFiles(String username) {
        return this.hrUserFileMapper.getUserFiles(username);
    }

    @Override
    @Transactional(transactionManager = "chainedTransactionManager", rollbackFor = Exception.class)
    public UserFileVo deleteFile(UserFileVo userFileVo) throws Exception {

        // Xóa trong database
        this.userFileRepo.deleteById(userFileVo.getId());

        // Xóa file vật lý
        File file = new File(userFileVo.getUrl() + userFileVo.getFileName());

        if (file.delete()) {
            logger.debug("======> HR DELETE USER FILE");
        } else {
            throw new Exception("Có lỗi: Không thể xóa file");
        }

        return userFileVo;
    }
}
