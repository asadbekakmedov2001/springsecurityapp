package uz.smartup.academy.springsecurityapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @EmbeddedId
    private RoleKey id;

    public String getUsername() {
        return id.getUsername();
    }

    public void setUsername(String username) {
        if (this.id == null) {
            this.id = new RoleKey();
        }
        this.id.setUsername(username);
    }

    public String getRole() {
        return id.getRole();
    }

    public void setRole(String role) {
        if (this.id == null) {
            this.id = new RoleKey();
        }
        this.id.setRole(role);
    }
}
