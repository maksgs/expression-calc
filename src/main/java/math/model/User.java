package math.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import math.model.Role;

//import javax.management.relation.Role;
import javax.persistence.*;


@Entity
@Table (name="Usr")
public class User implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String password;

    private String confirmPassword;

    @Column
    private Date date;

    @Column
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public void setConfirmPassword(String p){
        this.confirmPassword = p;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public  Role getRole(){
        return role;
    }

    public  void setEnabled(Boolean b){
        this.enabled = b;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setDate(){
        this.date = new Date();
    }

    public Date getDate(){
        return date;
    }
}
