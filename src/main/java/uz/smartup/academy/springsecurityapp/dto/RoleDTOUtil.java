package uz.smartup.academy.springsecurityapp.dto;

import org.springframework.stereotype.Component;
import uz.smartup.academy.springsecurityapp.entity.Role;
import uz.smartup.academy.springsecurityapp.entity.User;

import java.util.List;

@Component
public class RoleDTOUtil {
    public Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

    public RoleDTO toDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public List<RoleDTO> toEntities(List<Role> roleS){
        return roleS.stream().map(this::toDTO).toList();
    }
}
