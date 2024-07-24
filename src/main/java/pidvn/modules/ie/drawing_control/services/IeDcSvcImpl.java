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
    private UsersRepo usersRepo;

    @Autowired
    private ProductRepo productRepo;

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

        // Tạo folder project
        String rootPath = "E:\\Workspace\\Java\\PIDVN\\IE-Project\\" + projectDto.getProjectNo() + "\\Drawing";

        File nestedDirectory = new File(rootPath);

        if (!nestedDirectory.exists()) {
            // Tạo các thư mục lồng nhau
            if (nestedDirectory.mkdirs()) {
                System.out.println("Các thư mục lồng nhau đã được tạo thành công!");
            } else {
                System.out.println("Không thể tạo các thư mục lồng nhau.");
            }
        } else {
            System.out.println("Các thư mục lồng nhau đã tồn tại.");
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
    public Map<String, String> uploadDrawingFile(MultipartFile[] files, String projectNo) {

        // Đường dẫn lưu trữ file
        String rootPath = "D:\\Workspace\\ProjectManagement\\Project\\" + projectNo + "\\Drawing\\";



        return null;
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
    public ProjectActivityDto insertProjectActivity(MultipartFile file, ProjectActivityDto projectActivityDto) {

        IeDc003 ieDc003 = modelMapper.map(projectActivityDto, IeDc003.class);
        ieDc003 = ieDc003Repo.save(ieDc003);
        ProjectActivityDto result = this.modelMapper.map(ieDc003, ProjectActivityDto.class);

        // TODO upload file


        return result;
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepo.findAll();
    }

}
