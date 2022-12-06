package pidvn.modules.administrator.services;

import pidvn.entities.one.Permissions;

import java.util.List;

public interface IPermissionService {
    List<Permissions> getAll();
    Permissions createPermission(Permissions permission);
}
