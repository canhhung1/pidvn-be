package pidvn.modules.ie.drawing_control.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.IeDc001;
import pidvn.entities.one.IeDc002;
import pidvn.entities.one.Users;
import pidvn.mappers.one.ie.drawing_control.IeDcMapper;
import pidvn.modules.ie.drawing_control.models.ProjectDto;
import pidvn.modules.ie.drawing_control.models.ProjectTypeDto;
import pidvn.modules.ie.drawing_control.models.UserDto;
import pidvn.repositories.one.IeDc001Repo;
import pidvn.repositories.one.IeDc002Repo;
import pidvn.repositories.one.UsersRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IeDcSvcImpl implements IeDcSvc {

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

    @Override
    public List<UserDto> getUsers(List<Integer> subsectionIds) {
        List<Users> data = usersRepo.findAllBySubsectionIds(subsectionIds);
        return data.stream()
                .map(item -> modelMapper.map(item, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        IeDc001 data = ieDc001Repo.save(modelMapper.map(projectDto, IeDc001.class));
        return this.modelMapper.map(data, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getProjects() {
        return this.ieDcMapper.getProjects();
    }

    @Override
    public List<ProjectTypeDto> getProjectTypes() {
        List<IeDc002> data = this.ieDc002Repo.findAll();
        return data.stream()
                .map(item -> modelMapper.map(item, ProjectTypeDto.class))
                .collect(Collectors.toList());
    }


}
