package de.consol.dus.user.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class User {
  @Id
  @SequenceGenerator(name = "UserIdGenerator", sequenceName = "user_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "username", unique = true)
  private String username;

  @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
  @JoinColumn(name = "fk_favorite_color")
  private Color favoriteColor;
}
