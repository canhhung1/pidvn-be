package pidvn.modules.ie.drawing_control.services;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pidvn.entities.one.*;
import pidvn.mappers.one.ie.drawing_control.IeDcMapper;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.repositories.one.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IeDcSvcImpl implements IeDcSvc {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IeDcMapper ieDcMapper;

    @Autowired
    private IeDc001Repo ieDc001Repo;

    @Autowired
    private IeDc003Repo ieDc003Repo;

    @Autowired
    private IeDc004Repo ieDc004Repo;

    @Autowired
    private IeDc007Repo ieDc007Repo;

    @Autowired
    private IeDc008Repo ieDc008Repo;

    @Autowired
    private IeDc009Repo ieDc009Repo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ProductRepo productRepo;

    private final String ROOT_FOLDER = "\\\\10.92.176.10\\DataSharePIDVN\\4. IE Drawing\\HUNG-IT\\IE-Project\\";

    @Override
    public List<Users> getPersonInCharges(List<Integer> subsectionIds) {
        return this.usersRepo.findAllBySubsectionIds(subsectionIds);
    }

    @Override
    public List<ProjectDto> getProjects(SearchDto searchDto) {
        return this.ieDcMapper.getProjects(searchDto);
    }

    @Override
    public ProjectDto insertProject(ProjectDto projectDto) {
        //\\10.92.176.10\DataSharePIDVN\4. IE Drawing\HUNG-IT
        // Tạo folder project
        String rootPath = this.ROOT_FOLDER + projectDto.getProjectNo();

        try {
            // Tạo thư mục, bao gồm cả các thư mục cha nếu chúng chưa tồn tại
            Files.createDirectories(Paths.get(rootPath + "\\Drawing"));
            Files.createDirectories(Paths.get(rootPath + "\\Activity"));
            Files.createDirectories(Paths.get(rootPath + "\\Progress"));
        } catch (IOException e) {
            System.err.println("Failed to create directories: " + e.getMessage());
        }



        // Lưu thông tin vào database

        IeDc001 ieDc001 = modelMapper.map(projectDto, IeDc001.class);
        ieDc001 = this.ieDc001Repo.save(ieDc001);

        return this.modelMapper.map(ieDc001, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        IeDc001 ieDc001 = modelMapper.map(projectDto, IeDc001.class);
        ieDc001 = this.ieDc001Repo.save(ieDc001);
        return this.modelMapper.map(ieDc001, ProjectDto.class);
    }

    @Override
    public List<ProjectTypeDto> getProjectTypes() {
        List<IeDc004> projectTypes = this.ieDc004Repo.findAll();
        return projectTypes.stream().map(item -> modelMapper.map(item, ProjectTypeDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Integer projectId) {
        return this.ieDcMapper.getProjectById(projectId);
    }

    @Override
    public List<ProjectProgressDto> getProjectProgresses(Integer projectId) {
        return this.ieDcMapper.getProjectProgresses(projectId);
    }

    @Override
    public ProjectProgressDto updateProjectProgress(ProjectProgressDto projectProgressDto) {
        IeDc007 ieDc007 = modelMapper.map(projectProgressDto, IeDc007.class);
        ieDc007 = this.ieDc007Repo.save(ieDc007);
        return this.modelMapper.map(ieDc007, ProjectProgressDto.class);
    }

    @Override
    public List<DrawingDto> getDrawings(Integer projectId) {
        return this.ieDcMapper.getDrawings(projectId);
    }

    @Override
    public DrawingDto saveDrawing(DrawingDto drawingDto) {
        IeDc008 ieDc008 = modelMapper.map(drawingDto, IeDc008.class);
        ieDc008 = ieDc008Repo.save(ieDc008);
        return this.modelMapper.map(ieDc008,DrawingDto.class);
    }

    @Override
    public Map<String, Object> uploadDrawingFile(MultipartFile[] files, String projectNo) {
        // Đường dẫn lưu trữ file
        String rootPath = this.ROOT_FOLDER + projectNo + "\\Drawing\\";

        try {

            for (MultipartFile file: files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(rootPath + file.getOriginalFilename());
                Files.write(path, bytes);
            }
        }catch (Exception e) {

        }
        Map<String, Object> result = new HashMap<>();
        result.put("files", files.length);
        return result;
    }

    @Override
    public Map uploadDrawingTreeList(MultipartFile file, Integer projectId)throws IOException {

        Map result = new HashMap<>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<IeDc008> data = new ArrayList<>();

        String uuid1 = null;
        String uuid2 = null;
        String uuid3 = null;
        String uuid4 = null;
        String parentId = null;


        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {

            XSSFRow row = sheet.getRow(i);

            IeDc008 obj = new IeDc008();

            if (!("").equals(row.getCell(8).getStringCellValue())) {

                String drawingNo = row.getCell(8).getStringCellValue();

                uuid1 = UUID.randomUUID().toString();
                parentId = null;
                obj.setId(uuid1);
                obj.setDrawingNo(drawingNo);
                obj.setParentId(parentId);
                obj.setDrawingName(row.getCell(12).getStringCellValue());
                obj.setQty((int) row.getCell(13).getNumericCellValue());
                obj.setUnit(row.getCell(14).getStringCellValue());
                obj.setMaterial(row.getCell(15).getStringCellValue());
                obj.setHardness(row.getCell(16).getStringCellValue());
                obj.setPolishing(row.getCell(17).getStringCellValue());
                obj.setSupplier(row.getCell(18).getStringCellValue());
                obj.setProjectId(projectId);
                obj.setProjectProgressId(3);
                obj.setOrdinalNumber((int)row.getCell(21).getNumericCellValue());
                data.add(obj);
                continue;
            }

            if (!("").equals(row.getCell(9).getStringCellValue())) {
                String drawingNo = row.getCell(9).getStringCellValue();
                uuid2 = UUID.randomUUID().toString();
                parentId = uuid1;
                obj.setId(uuid2);
                obj.setDrawingNo(drawingNo);
                obj.setParentId(parentId);
                obj.setDrawingName(row.getCell(12).getStringCellValue());
                obj.setQty((int) row.getCell(13).getNumericCellValue());
                obj.setUnit(row.getCell(14).getStringCellValue());
                obj.setMaterial(row.getCell(15).getStringCellValue());
                obj.setHardness(row.getCell(16).getStringCellValue());
                obj.setPolishing(row.getCell(17).getStringCellValue());
                obj.setSupplier(row.getCell(18).getStringCellValue());
                obj.setProjectId(projectId);
                obj.setProjectProgressId(3);
                obj.setOrdinalNumber((int) row.getCell(21).getNumericCellValue());
                data.add(obj);
                continue;
            }

            if (!("").equals(row.getCell(10).getStringCellValue())) {
                String drawingNo = row.getCell(10).getStringCellValue();
                uuid3 = UUID.randomUUID().toString();
                parentId = uuid2;
                obj.setId(uuid3);
                obj.setDrawingNo(drawingNo);
                obj.setParentId(parentId);
                obj.setDrawingName(row.getCell(12).getStringCellValue());
                obj.setQty((int) row.getCell(13).getNumericCellValue());
                obj.setUnit(row.getCell(14).getStringCellValue());
                obj.setMaterial(row.getCell(15).getStringCellValue());
                obj.setHardness(row.getCell(16).getStringCellValue());
                obj.setPolishing(row.getCell(17).getStringCellValue());
                obj.setSupplier(row.getCell(18).getStringCellValue());
                obj.setProjectId(projectId);
                obj.setProjectProgressId(3);
                obj.setOrdinalNumber((int) row.getCell(21).getNumericCellValue());
                data.add(obj);
                continue;
            }

            if (!("").equals(row.getCell(11).getStringCellValue())) {
                String drawingNo = row.getCell(11).getStringCellValue();
                uuid4 = UUID.randomUUID().toString();
                parentId = uuid3;
                obj.setId(uuid4);
                obj.setDrawingNo(drawingNo);
                obj.setParentId(parentId);
                obj.setDrawingName(row.getCell(12).getStringCellValue());
                obj.setQty((int) row.getCell(13).getNumericCellValue());
                obj.setUnit(row.getCell(14).getStringCellValue());
                obj.setMaterial(row.getCell(15).getStringCellValue());
                obj.setHardness(row.getCell(16).getStringCellValue());
                obj.setPolishing(row.getCell(17).getStringCellValue());
                obj.setSupplier(row.getCell(18).getStringCellValue());
                obj.setProjectId(projectId);
                obj.setProjectProgressId(3);
                obj.setOrdinalNumber((int) row.getCell(21).getNumericCellValue());
                data.add(obj);
                continue;
            }


        }

        List<IeDc008> saved = ieDc008Repo.saveAll(data);

        result.put("data", saved);

        return result;
    }

    @Override
    public List<ProjectActivityDto> getProjectActivities(Integer projectId) {
        List<IeDc003> projectActivities = this.ieDc003Repo.findAllByProjectId(projectId);
        return projectActivities.stream().map(item -> modelMapper.map(item, ProjectActivityDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto) throws IOException {

        IeDc003 ieDc003 = modelMapper.map(projectActivityDto, IeDc003.class);
        ieDc003 = ieDc003Repo.save(ieDc003);
        ProjectActivityDto result = this.modelMapper.map(ieDc003, ProjectActivityDto.class);

        // TODO upload file
        if (file != null) {
            IeDc001 project = this.ieDc001Repo.findById(projectActivityDto.getProjectId()).get();
            String rootPath = this.ROOT_FOLDER + project.getProjectNo() + "\\Activity\\";

            byte[] bytes = file.getBytes();
            Path path = Paths.get(rootPath + file.getOriginalFilename());
            Files.write(path, bytes);
        }

        return result;
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }




    @Override
    public Map uploadProgressFiles(MultipartFile[] files, Integer projectId, Integer projectProgressId) {
        Map result = new HashMap<>();
        List<IeDc009> arr = new ArrayList<>();
        IeDc001 ieDc001 = this.ieDc001Repo.findById(projectId).get();
        String projectNo = ieDc001.getProjectNo();
        String rootPath = this.ROOT_FOLDER + projectNo + "\\Progress\\";

        try {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(rootPath + file.getOriginalFilename());
                Files.write(path, bytes);

                IeDc009 obj = new IeDc009();
                obj.setName(file.getOriginalFilename());
                obj.setProjectId(projectId);
                obj.setProjectProgressId(projectProgressId);

                IeDc009 data = this.ieDc009Repo.save(obj);
                arr.add(data);
            }
        } catch (Exception e) {

        }

        result.put("data", arr);

        return result;
    }

    @Override
    public List<IeDc009> getProgressFiles(Integer projectId, Integer projectProgressId) {


        return Collections.emptyList();
    }

}
