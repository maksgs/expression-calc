package math.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "user_roles")
public class Role  implements Serializable
{
    @Id
    @GeneratedValue
    @Column (name="role_id")
    private Long id;

    @Column
    private String authority;

    @Column
    private String roleName;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setAuthority(String authority){
        this.authority = authority;
    }

    public String getAuthority(){
        return authority;
    }

    public void setRoleName(String name){
        this.roleName = name;
    }

    public String getRoleName(){
        return roleName;
    }
}
