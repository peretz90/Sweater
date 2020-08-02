package by.peretz90.sweater.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(value = AccessLevel.NONE)
  private Long id;

  @NonNull
  @NotBlank(message = "Please fill the message")
  @Length(max = 2048, message = "Message too long (more than 2kB)")
  private String text;
  @NonNull
  @Length(max = 255, message = "Message too long (more than 255)")
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
