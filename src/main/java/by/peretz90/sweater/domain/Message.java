package by.peretz90.sweater.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(value = AccessLevel.NONE)
  private Integer id;

  @NonNull
  private String text;
  @NonNull
  private String tag;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @NonNull
  private User author;

  private String filename;

  public String getAuthorName() {
    return author != null ? author.getUsername() : "<none>";
  }
}
