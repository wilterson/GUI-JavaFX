/**
 * Created by Wilterson on 14/06/2017.
 */

package hotDogExpress.models;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class UserObservable {

    private final IntegerProperty id;
    private final StringProperty role;
    private final StringProperty nome;
    private final StringProperty email;
    private final StringProperty senha;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty cpf;
    private final ObjectProperty<LocalDate> created_at;
    private final StringProperty department;


//    public UserObservable(IntegerProperty id, StringProperty role, StringProperty nome,
//                          StringProperty email, StringProperty senha, ObjectProperty<LocalDate> birthday,
//                          StringProperty cpf, ObjectProperty<LocalDate> created_at, StringProperty department
//    ) {
//        this.id = id;
//        this.role = role;
//        this.nome = nome;
//        this.email = email;
//        this.senha = senha;
//        this.birthday = birthday;
//        this.cpf = cpf;
//        this.created_at = created_at;
//        this.department = department;
//    }

    public UserObservable(User user){
        this.id = new SimpleIntegerProperty();
        this.role = new SimpleStringProperty();
        this.nome = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.senha = new SimpleStringProperty();
        this.cpf = new SimpleStringProperty();
        this.department = new SimpleStringProperty();
        this.birthday= new SimpleObjectProperty<LocalDate>();
        this.created_at = new SimpleObjectProperty<LocalDate>();

        this.id.set(user.getId());
        this.role.set(user.getRole());
        this.nome.set(user.getNome());
        this.email.set(user.getEmail());
        this.senha.set(user.getSenha());
        this.cpf.set(user.getCpf());
        this.department.set(user.getDepartment());
        this.birthday.set(user.getBirthday());
        this.created_at.set(user.getCreated_at());
    }

    public UserObservable() {
        this.id = new SimpleIntegerProperty();
        this.role= new SimpleStringProperty();
        this.nome = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.senha = new SimpleStringProperty("");
        this.cpf = new SimpleStringProperty("");
        this.department = new SimpleStringProperty("");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1990, 1, 1));
        this.created_at = new SimpleObjectProperty<LocalDate>(LocalDate.of(1990, 1, 1));
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

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
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

    public LocalDate getCreated_at() {
        return created_at.get();
    }

    public ObjectProperty<LocalDate> created_atProperty() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at.set(created_at);
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
