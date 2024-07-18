package pidvn.modules.ie.drawing_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IeDrawing;
import pidvn.entities.one.IeProject;
import pidvn.entities.one.IeProjectProgress;
import pidvn.entities.one.IeProjectType;
import pidvn.mappers.one.ie.drawing_management.DrawingMngMapper;
import pidvn.modules.ie.drawing_management.models.ProgressVo;
import pidvn.modules.ie.drawing_management.models.ProjectVo;
import pidvn.modules.ie.drawing_management.models.SearchVo;
import pidvn.repositories.one.IeDrawingRepo;
import pidvn.repositories.one.IeProjectProgressRepo;
import pidvn.repositories.one.IeProjectRepo;
import pidvn.repositories.one.IeProjectTypeRepo;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Service
public class DrawingMngSvc implements IDrawingMngSvc {


    @Autowired
    private DrawingMngMapper drawingMngMapper;

    @Autowired
    private IeProjectProgressRepo ieProjectProgressRepo;

    @Autowired
    private IeDrawingRepo ieDrawingRepo;

    @Autowired
    private IeProjectTypeRepo ieProjectTypeRepo;

    @Autowired
    private IeProjectRepo ieProjectRepo;

    @Override
    public IeProject getIeProjectById(Integer id) {
        return this.ieProjectRepo.findById(id).get();
    }

    @Override
    public List<ProjectVo> getProjects(SearchVo searchVo) {
        return this.drawingMngMapper.getProjects(searchVo);
    }

    @Override
    public IeProject saveIeProject(IeProject ieProject) {

        // Tạo folder

        String rootPath = "D:\\Work\\PIDVN\\WorkSpace\\IE-Project\\" + ieProject.getControlNo() + "\\Drawing";

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

        return this.ieProjectRepo.save(ieProject);
    }

    @Override
    public List<IeProjectProgress> getProjectProcesses(Integer projectId) {
        return null;
    }

    @Override
    public List<IeDrawing> getIeDrawings(Integer projectId, Integer progressId) {
        return this.ieDrawingRepo.findByProjectIdAndProgressId(projectId,progressId);
    }

    @Override
    public IeDrawing saveIeDrawing(IeDrawing ieDrawing) {
        return this.ieDrawingRepo.save(ieDrawing);
    }

    @Override
    public List<IeProjectType> getIeProjectTypes() {
        return this.ieProjectTypeRepo.findAll();
    }

    @Override
    public List<ProgressVo> getProgressByProject(SearchVo searchVo) {
        return this.drawingMngMapper.getProgressByProject(searchVo);
    }
}
