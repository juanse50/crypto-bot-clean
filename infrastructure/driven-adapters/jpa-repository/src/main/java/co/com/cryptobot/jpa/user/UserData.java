package co.com.cryptobot.jpa.user;

import co.com.cryptobot.jpa.CryptoUser.CryptoUserData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserData {

        @Id
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "username")
        private String username;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<CryptoUserData> cryptoUsers;

        public UserData(Long id, String firstName, String lastName, String username) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
        }
    }

