/**
 * Created by Wilterson on 19/06/2017.
 */
package hotDogExpress.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SystemLogObservable {
    private IntegerProperty cod;
    private StringProperty acao;
    private ObjectProperty<User> user;
    private ObjectProperty<LocalDate> dateAcao;

    public SystemLogObservable(IntegerProperty cod, String acao, User user, LocalDate date) {
        this.cod = cod;
        this.acao.set(acao);
        this.user.set(user);
        this.dateAcao.set(date);
    }

    public SystemLogObservable() {
        this.cod = new SimpleIntegerProperty();
        this.acao = new SimpleStringProperty();
        this.user = null;
        this.dateAcao = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017, 1, 1));
    }

    public SystemLogObservable(SystemLog log) {
        this.cod = new SimpleIntegerProperty();
        this.acao = new SimpleStringProperty();
        this.user = null;
        this.dateAcao = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017, 1, 1));

        this.cod.set(log.getCod());
        this.acao.set(log.getAcao());
        this.user.set(log.getUser());
        this.dateAcao.set(log.getDateAcao());
    }
}
