package CornSalad.TIE.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "USER_LOG")
public class User_log {
    @Id @GeneratedValue
    @Column(name = "USER_LOG_ID")
    private long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User USER;

    private LocalDateTime LOGIN_DATE;

    private Status LOGIN_STATUS;

}
