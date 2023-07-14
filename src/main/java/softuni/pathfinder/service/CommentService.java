package softuni.pathfinder.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import softuni.pathfinder.exceptions.RouteNotFoundException;
import softuni.pathfinder.model.Comment;
import softuni.pathfinder.model.Route;
import softuni.pathfinder.model.User;
import softuni.pathfinder.model.dto.CommentCreationDto;
import softuni.pathfinder.model.view.CommentDisplayView;
import softuni.pathfinder.repository.CommentRepository;
import softuni.pathfinder.repository.RouteRepository;
import softuni.pathfinder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(RouteRepository routeRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentDisplayView> getAllCommentsForRoute(Long routeId){
        Route route = routeRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);

        List<Comment> comments = commentRepository.findAllByRoute(route).get();
        return comments.stream().
                map(comment -> new CommentDisplayView(comment.getId(),
                        comment.getAuthor().getFullName(),
                        comment.getText())).
                collect(Collectors.toList());
    }

    public CommentDisplayView createComment(CommentCreationDto commentCreationDto){

        User author = userRepository.findByUsername(commentCreationDto.getUsername()).get();

        Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setRoute(routeRepository.getById(commentCreationDto.getRouteId()));
        comment.setAuthor(author);
        comment.setApproved(true);
        comment.setText(commentCreationDto.getMessage());

        commentRepository.save(comment);

        return new CommentDisplayView(comment.getId(), author.getFullName(), comment.getText());
    }
}
