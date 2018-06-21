package by.bsuir.socialnetw.web;

import by.bsuir.socialnetw.config.Constants;
import by.bsuir.socialnetw.domain.Message;
import by.bsuir.socialnetw.domain.Person;
import by.bsuir.socialnetw.model.MessagePost;
import by.bsuir.socialnetw.model.MessageView;
import by.bsuir.socialnetw.service.MessageService;
import by.bsuir.socialnetw.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping(value = Constants.URI_MESSAGES, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;
    private final PersonService personService;

    
    @GetMapping(value = "/dialog/{id}")
    public ResponseEntity<List<MessageView>> getDialog(@PathVariable("id") Long id) {
        log.debug("")

        final Person interlocutor = personService.findById(id);
        if (null == interlocutor) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map(messageService.getDialog(profile, interlocutor)));
    }

    
    @GetMapping(value = "/last")
    public List<MessageView> getLastMessages(
        log.debug("")

        return map(messageService.getLastMessages(profile));
    }

    
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void send(@RequestBody @Valid MessagePost messagePost) {
        log.debug("")

        final Message message = new Message();
        message.setBody(messagePost.getBody());
        message.setSender(personService.findById(messagePost.getSender()));
        message.setRecipient(personService.findById(messagePost.getRecipient()));

        messageService.send(message);
    }

    private List<MessageView> map(List<Message> messages) {
        return messages.stream()
                .map(MessageView::new)
                .collect(toList());
    }

}
