package softuni.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.pathfinder.model.Comment;
import softuni.pathfinder.model.Route;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findAllByRoute(Route route);
}
