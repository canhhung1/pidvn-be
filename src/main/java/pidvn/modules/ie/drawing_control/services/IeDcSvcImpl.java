package pidvn.modules.ie.drawing_control.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IeDc001;
import pidvn.entities.one.IeDc004;
import pidvn.entities.one.IeDc007;
import pidvn.entities.one.IeDc008;
import pidvn.mappers.one.ie.drawing_control.IeDcMapper;
import pidvn.modules.ie.drawing_control.models.*;
import pidvn.repositories.one.IeDc001Repo;
import pidvn.repositories.one.IeDc004Repo;
import pidvn.repositories.one.IeDc007Repo;
import pidvn.repositories.one.IeDc008Repo;

import java.io.File;
import java.util.List;
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
    private IeDc004Repo ieDc004Repo;

    @Autowired
    private IeDc007Repo ieDc007Repo;

    @Autowired
    private IeDc008Repo ieDc008Repo;

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

//    @Override
//    public ProjectProgressDto getProjectProgressDetail(Integer projectId, Integer projectProgressId) {
//        return this.ieDcMapper.getProjectProgressDetail(projectId, projectProgressId);
//    }
}
