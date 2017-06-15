/**
 * Created by Wilterson on 14/06/2017.
 */

package hotDogExpress.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class UserObservable {

    private final IntegerProperty id;
    private final StringProperty role;
    private final StringProperty nome;
    private final StringProperty email;
    private final StringProperty senha;
//    private final Date birthday;
    private final StringProperty cpf;
//    private final Date created_at;
    private final StringProperty department;


    public UserObservable(IntegerProperty id, StringProperty role,
                          StringProperty nome, StringProperty email,
                          StringProperty senha, StringProperty cpf, StringProperty department
    ){
        this.id = id;
        this.role = role;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
//        this.birthday = birthday;
//        this.created_at = created_at;
        this.department = department;
    }

    public UserObservable(User user){
        this.id = new SimpleIntegerProperty(user.getId());
        this.role = new SimpleStringProperty(user.getRole());
        this.nome = new SimpleStringProperty(user.getNome());
        this.email = new SimpleStringProperty(user.getEmail());
        this.senha = new SimpleStringProperty(user.getSenha());
        this.cpf = new SimpleStringProperty(user.getCpf());
        this.department = new SimpleStringProperty(user.getDepartment());
//        this.cpf = new SimpleStringProperty(user.getBirthday());
//        this.cpf = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getRole() {
        return role.get();
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getSenha() {
        return senha.get();
    }

    public StringProperty senhaProperty() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }
}
