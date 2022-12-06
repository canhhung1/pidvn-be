package pidvn.modules.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Roles;
import pidvn.repositories.one.RolesRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RolesRepo rolesRepo;

    @Override
    public List<Roles> getRoles() {
        return this.rolesRepo.findAll();
    }

    @Override
    public Roles createRole(Roles role) {
        return this.rolesRepo.save(role);
    }

    @Override
    public Roles updateRole(Roles role) {

        Roles obj = this.rolesRepo.findById(role.getId()).get();

        obj.setName(role.getName());
        obj.setLabel(role.getLabel());
        obj.setDescription(role.getDescription());
        obj.setPermission(role.getPermission());

        return this.rolesRepo.save(obj);
    }
}
