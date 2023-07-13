package softuni.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.pathfinder.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
