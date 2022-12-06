package pidvn.modules.administrator.services;

import pidvn.entities.one.Roles;

import java.util.List;

public interface IRoleService {
    List<Roles> getRoles();
    Roles createRole(Roles role);
    Roles updateRole(Roles role);
}
