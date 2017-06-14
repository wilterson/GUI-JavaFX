/**
 * Created by Wilterson on 09/06/2017.
 */

package hotDogExpress.models;

import java.util.Date;

public class User {

    private final int id;
    private String role;
    private final String nome;
    private final String email;
    private final String senha;
    private final Date birthday;
    private final String cpf;
    private final Date created_at;

    public User(int id, String role, String nome, String email, String senha, Date birthday, String cpf, Date created_at) {
        this.id = id;
        this.role = role;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.birthday = birthday;
        this.cpf = cpf;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getCreated_at() {
        return created_at;
    }
}
