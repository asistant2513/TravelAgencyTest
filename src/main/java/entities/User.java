package entities;

import enums.Roots;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Size(min = 4, max = 18)
    @Id
    @Column(name = "login")
    private String login;

    @Pattern(regexp = "[A-Z][0-9]*")
    @NotNull
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="roots")
    private Roots roots;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientID", referencedColumnName = "id")
    private Client client;

    public User(){ }

    //-------------------------------\\Getters//-------------------------------\\

    public Roots getRoots() {
        return roots;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public Client getClient() {
        return this.client;
    }

    //-------------------------------------------------------------------------\\

    //-------------------------------\\Setters//-------------------------------\\

    public void setRoots(Roots roots) {
        this.roots = roots;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //-------------------------------------------------------------------------\\
}
