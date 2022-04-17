package net.accessory.paragram.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BlacklistTokens")
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_token",length = 1000)
    private String old_token;

    public Token(String old_token) {
        this.old_token = old_token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOld_token() {
        return old_token;
    }

    public void setOld_token(String old_token) {
        this.old_token = old_token;
    }
}
