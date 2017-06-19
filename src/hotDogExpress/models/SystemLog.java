/**
 * Created by Wilterson on 19/06/2017.
 */

package hotDogExpress.models;

import java.time.LocalDate;

public class SystemLog {

    private int cod;
    private String acao;
    private User user;
    private LocalDate dateAcao;

    public SystemLog(int cod, String acao, User user, LocalDate dateAcao) {
        this.cod = cod;
        this.acao = acao;
        this.user = user;
        this.dateAcao = dateAcao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateAcao() {
        return dateAcao;
    }

    public void setDateAcao(LocalDate dateAcao) {
        this.dateAcao = dateAcao;
    }
}
