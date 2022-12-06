package pidvn.modules.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidvn.entities.one.Permissions;
import pidvn.repositories.one.PermissionsRepo;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Override
    public List<Permissions> getAll() {
        return this.permissionsRepo.findByOrderByCreatedAtDesc();
    }

    @Override
    public Permissions createPermission(Permissions permission) {
        return this.permissionsRepo.save(permission);
    }
}
