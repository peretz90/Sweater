package by.peretz90.sweater.repos;

import by.peretz90.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
  List<Message> findByTag(String tag);
}
