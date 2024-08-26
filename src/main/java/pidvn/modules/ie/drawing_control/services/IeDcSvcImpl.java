package pidvn.modules.ie.drawing_control.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.ie.drawing_control.IeDcMapper;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.repositories.one.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IeDcSvcImpl implements IeDcSvc {

    Logger logger = LoggerFactory.getLogger(IeDcSvcImpl.class);

    @Autowired
    private IeDcMapper ieDcMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private IeDc001Repo ieDc001Repo;

    @Autowired
    private IeDc002Repo ieDc002Repo;

    @Autowired
    private IeDc006Repo ieDc006Repo;

    @Autowired
    private IeDc007Repo ieDc007Repo;

    //    private final String ROOT_FOLDER = "\\\\10.92.176.10\\DataSharePIDVN\\4. IE Drawing\\DRAWING-CONTROL\\IE-Project\\";
    public final String ROOT_FOLDER = "D:\\DataSharePIDVN\\4. IE Drawing\\HUNG-IT\\IE-Project\\";


    @Override
    public List<UserDto> getUsers(List<Integer> subsectionIds) {
        List<Users> data = usersRepo.findAllBySubsectionIds(subsectionIds);
        return data.stream()
                .map(item -> modelMapper.map(item, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(transactionManager = "transactionManagerOne")
    public ProjectDto createProject(ProjectDto projectDto) {

        // Lưu thông tin vào database
        IeDc001 data = ieDc001Repo.save(modelMapper.map(projectDto, IeDc001.class));


        // Tạo folder project

        String rootPath = this.ROOT_FOLDER + projectDto.getControlNo();
        try {
            // Tạo thư mục, bao gồm cả các thư mục cha nếu chúng chưa tồn tại
            Files.createDirectories(Paths.get(rootPath + "\\Drawing"));
            Files.createDirectories(Paths.get(rootPath + "\\Activity"));
            Files.createDirectories(Paths.get(rootPath + "\\Process"));
        } catch (IOException e) {
            logger.debug("DRAWING CONTROL (IeDcSvcImpl.insertProject): Failed to create directories: " + e.getMessage());
            throw new RuntimeException("Failed to create directories: " + e);
        }

        return this.modelMapper.map(data, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getProjects() {
        return this.ieDcMapper.getProjects();
    }

    @Override
    public ProjectDto getProject(Integer id) {
        return this.ieDc001Repo.findById(id)
                .map(data -> this.modelMapper.map(data, ProjectDto.class))
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + id));
    }


    @Override
    public List<ProjectTypeDto> getProjectTypes() {
        List<IeDc002> data = this.ieDc002Repo.findAll();
        return data.stream()
                .map(item -> modelMapper.map(item, ProjectTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcessDto> getProcesses(Integer projectId) {
        return this.ieDcMapper.getProcesses(projectId);
    }

    /**
     * Đọc file và insert dữ liệu vào bảng drawing (ie_dc_006)
     *
     * @param file
     * @param projectId
     * @return
     */
    @Override
    public List<DrawingDto> uploadDrawingStructure(MultipartFile file, Integer projectId) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<IeDc006> data = new ArrayList<>();

        String uuid1 = null;
        String uuid2 = null;
        String uuid3 = null;
        String uuid4 = null;
        String parentId = null;
        int count = 1;
        int START = 9;

        for (int i = START; i < 1000; i++) {

            try {
                XSSFRow row = sheet.getRow(i);

                IeDc006 obj = new IeDc006();

                if (row.getCell(21).getStringCellValue().equals("END")) {
                    break;
                }

                if (!("").equals(row.getCell(8).getStringCellValue())) {

                    String drawingNo = row.getCell(8).getStringCellValue();

                    uuid1 = UUID.randomUUID().toString();
                    parentId = null;
                    obj.setId(uuid1);
                    obj.setDrawingNo(drawingNo);
                    obj.setParentId(parentId);
                    obj.setName(row.getCell(12).getStringCellValue());
                    obj.setQty((int) row.getCell(13).getNumericCellValue());
                    obj.setUnit(row.getCell(14).getStringCellValue());
                    obj.setMaterial(row.getCell(15).getStringCellValue());
                    obj.setHardness(row.getCell(16).getStringCellValue());
                    obj.setPolishing(row.getCell(17).getStringCellValue());
                    obj.setSupplier(row.getCell(18).getStringCellValue());
                    obj.setVersion(row.getCell(19).getStringCellValue());
                    obj.setRemark(row.getCell(20).getStringCellValue());
                    obj.setProjectId(projectId);
                    obj.setOrdinal(count);
                    obj.setLevel(1);
                    data.add(obj);
                    count++;
                    continue;
                }

                if (!("").equals(row.getCell(9).getStringCellValue())) {
                    String drawingNo = row.getCell(9).getStringCellValue();
                    uuid2 = UUID.randomUUID().toString();
                    parentId = uuid1;
                    obj.setId(uuid2);
                    obj.setDrawingNo(drawingNo);
                    obj.setParentId(parentId);
                    obj.setName(row.getCell(12).getStringCellValue());
                    obj.setQty((int) row.getCell(13).getNumericCellValue());
                    obj.setUnit(row.getCell(14).getStringCellValue());
                    obj.setMaterial(row.getCell(15).getStringCellValue());
                    obj.setHardness(row.getCell(16).getStringCellValue());
                    obj.setPolishing(row.getCell(17).getStringCellValue());
                    obj.setSupplier(row.getCell(18).getStringCellValue());
                    obj.setVersion(row.getCell(19).getStringCellValue());
                    obj.setRemark(row.getCell(20).getStringCellValue());
                    obj.setProjectId(projectId);
                    obj.setOrdinal(count);
                    obj.setLevel(2);
                    data.add(obj);
                    count++;
                    continue;
                }

                if (!("").equals(row.getCell(10).getStringCellValue())) {
                    String drawingNo = row.getCell(10).getStringCellValue();
                    uuid3 = UUID.randomUUID().toString();
                    parentId = uuid2;
                    obj.setId(uuid3);
                    obj.setDrawingNo(drawingNo);
                    obj.setParentId(parentId);
                    obj.setName(row.getCell(12).getStringCellValue());
                    obj.setQty((int) row.getCell(13).getNumericCellValue());
                    obj.setUnit(row.getCell(14).getStringCellValue());
                    obj.setMaterial(row.getCell(15).getStringCellValue());
                    obj.setHardness(row.getCell(16).getStringCellValue());
                    obj.setPolishing(row.getCell(17).getStringCellValue());
                    obj.setSupplier(row.getCell(18).getStringCellValue());
                    obj.setVersion(row.getCell(19).getStringCellValue());
                    obj.setRemark(row.getCell(20).getStringCellValue());
                    obj.setProjectId(projectId);
                    obj.setOrdinal(count);
                    obj.setLevel(3);
                    data.add(obj);
                    count++;
                    continue;
                }

                if (!("").equals(row.getCell(11).getStringCellValue())) {
                    String drawingNo = row.getCell(11).getStringCellValue();
                    uuid4 = UUID.randomUUID().toString();
                    parentId = uuid3;
                    obj.setId(uuid4);
                    obj.setDrawingNo(drawingNo);
                    obj.setParentId(parentId);
                    obj.setName(row.getCell(12).getStringCellValue());
                    obj.setQty((int) row.getCell(13).getNumericCellValue());
                    obj.setUnit(row.getCell(14).getStringCellValue());
                    obj.setMaterial(row.getCell(15).getStringCellValue());
                    obj.setHardness(row.getCell(16).getStringCellValue());
                    obj.setPolishing(row.getCell(17).getStringCellValue());
                    obj.setSupplier(row.getCell(18).getStringCellValue());
                    obj.setVersion(row.getCell(19).getStringCellValue());
                    obj.setRemark(row.getCell(20).getStringCellValue());
                    obj.setProjectId(projectId);
                    obj.setOrdinal(count);
                    obj.setLevel(4);
                    data.add(obj);
                    count++;
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
        }

        List<IeDc006> saved = ieDc006Repo.saveAll(data);

        return saved.stream()
                .map(item -> modelMapper.map(item, DrawingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DrawingDto> getDrawingStructure(Integer projectId) {
        List<IeDc006> data = this.ieDc006Repo.findAllByProjectIdOrderByOrdinalAsc(projectId);
        return data.stream()
                .map(item -> modelMapper.map(item, DrawingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> uploadDrawingFiles(MultipartFile[] files, Integer projectId) {

        Map<String, Object> result = new HashMap<>();

        IeDc001 data = this.ieDc001Repo.findById(projectId).get();

        String rootPath = this.ROOT_FOLDER + data.getControlNo() + "\\Drawing\\";

        try {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(rootPath + file.getOriginalFilename());
                Files.write(path, bytes);
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    @Override
    public ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto) throws IOException {
        IeDc001 data = this.ieDc001Repo.findById(projectActivityDto.getProjectId()).get();
        String rootPath = this.ROOT_FOLDER + data.getControlNo() + "\\Activity\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(rootPath + file.getOriginalFilename());
        Files.write(path, bytes);
        IeDc007 result = this.ieDc007Repo.save(this.modelMapper.map(projectActivityDto, IeDc007.class));
        return modelMapper.map(result, ProjectActivityDto.class);
    }


}
