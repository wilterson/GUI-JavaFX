/**
 * Created by Wilterson on 09/06/2017.
 */

package hotDogExpress.models;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;

@XStreamAlias("user")
public class User {

    private int id;
    private String role;
    private String nome;
    private String email;
    private String senha;
    private LocalDate birthday;
    private String cpf;
    private LocalDate created_at;
    private String department;

    public User(int id, String role, String nome, String email,
                String senha, LocalDate birthday, String cpf,
                LocalDate created_at, String department
    ){
        this.id = id;
        this.role = role;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.birthday = birthday;
        this.cpf = cpf;
        this.created_at = created_at;
        this.department = department;
    }

    public User(User user){
        this.id = user.id;
        this.role = user.role;
        this.nome = user.nome;
        this.email = user.email;
        this.senha = user.senha;
        this.birthday = user.birthday;
        this.cpf = user.cpf;
        this.created_at = user.created_at;
        this.department = user.department;
    }

    public User(){
        this.id = 0;
        this.role = "";
        this.nome = "";
        this.email = "";
        this.senha = "";
        this.birthday = null;
        this.cpf = "";
        this.department = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role='" + role + '\'' + ", nome='" + nome + '\'' + ", email='" + email + '\'' + ", senha='" + senha + '\'' + ", birthday=" + birthday + ", cpf='" + cpf + '\'' + ", created_at=" + created_at + ", department='" + department + '\'' + '}';
    }
}
