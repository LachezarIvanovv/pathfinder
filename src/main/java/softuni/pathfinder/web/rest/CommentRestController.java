package softuni.pathfinder.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import softuni.pathfinder.model.dto.CommentCreationDto;
import softuni.pathfinder.model.dto.CommentMessageDto;
import softuni.pathfinder.model.view.CommentDisplayView;
import softuni.pathfinder.service.CommentService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/{routeId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("routeId") Long routeId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDto commentDto){

        CommentCreationDto commentCreationDto = new CommentCreationDto(
                userDetails.getUsername(),
                routeId,
                commentDto.getMessage()
        );

        CommentDisplayView comment = commentService.createComment(commentCreationDto);

        return ResponseEntity.
                created(URI.create(String.format("/api/%d/comments/%d", routeId, comment.getId()))).
                body(comment);
    }
}
