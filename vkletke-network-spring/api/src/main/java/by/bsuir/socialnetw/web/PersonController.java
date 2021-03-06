package by.bsuir.socialnetw.web;

import by.bsuir.socialnetw.config.Constants;
import by.bsuir.socialnetw.domain.Person;
import by.bsuir.socialnetw.security.CurrentProfile;
import by.bsuir.socialnetw.service.PersonService;

import lombok.RequiredArgsConstructor;
import by.bsuir.socialnetw.model.PersonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.URI_API_PREFIX,
                produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final PersonService personService;

    
    @GetMapping("/person/{id}")
    public ResponseEntity<PersonView> getPerson(
            @PathVariable("id") Long id) {
        log.debug("REST request to get person id:{}", id);

        final Person person = personService.findById(id);
        if (null == person) {
            log.debug("Person id:{} is not signed up", id);

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PersonView(person));
    }
    
    @GetMapping("/people")
    public Page<PersonView> getPeople(
            @RequestParam(name = "searchTerm", defaultValue = "", required = false) String searchTerm,
            @PageableDefault(size = 20) Pageable pageRequest) {
        log.debug("REST request to get people list (searchTerm:{}, pageRequest:{})", searchTerm, pageRequest);

        final Page<Person> people = personService.getPeople(searchTerm, pageRequest);

        return people.map(PersonView::new);
    }

    
    @GetMapping("/friends")
    public Page<PersonView> getFriends(
            @CurrentProfile Person profile,
            @RequestParam(name = "searchTerm", defaultValue = "", required = false) String searchTerm,
            @PageableDefault(size = 20) Pageable pageRequest) {
        log.debug("REST request to get person's: {} friend list (searchTerm:{}, pageRequest:{})", profile, searchTerm, pageRequest);

        final Page<Person> friends = personService.getFriends(profile, searchTerm, pageRequest);

        return friends.map(PersonView::new);
    }

    
    @GetMapping("/friendOf")
    public Page<PersonView> getFriendOf(
            @CurrentProfile Person profile,
            @RequestParam(name = "searchTerm", defaultValue = "", required = false) String searchTerm,
            @PageableDefault(size = 20) Pageable pageRequest) {
        log.debug("");

        final Page<Person> friendOf = personService.getFriendOf(profile, searchTerm, pageRequest);

        return friendOf.map(PersonView::new);
    }

    
    @PutMapping("/friends/add/{personId}")
    public ResponseEntity<Void> addFriend(
            @CurrentProfile Person profile,
            @PathVariable("personId") Long id) {
        log.debug("REST request to add id:{} as a person's: {} friend", id, profile);

        final Person person = personService.findById(id);
        if (null == person) {
            return ResponseEntity.notFound().build();
        }

        personService.addFriend(profile, person);

        return ResponseEntity.ok().build();
    }

    
    @PutMapping("/friends/remove/{personId}")
    public ResponseEntity<Void> removeFriend(
            @CurrentProfile Person profile,
            @PathVariable("personId") Long id) {
        log.debug("");

        final Person person = personService.findById(id);
        if (null == person) {
            return ResponseEntity.notFound().build();
        }

        personService.removeFriend(profile, person);

        return ResponseEntity.ok().build();
    }
}
