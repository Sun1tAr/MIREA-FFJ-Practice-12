package my.learn.mireaffjpractice12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.learn.mireaffjpractice12.model.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@Data
@Table(name = "user_auths")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private List<UserRole> authorities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;



}
