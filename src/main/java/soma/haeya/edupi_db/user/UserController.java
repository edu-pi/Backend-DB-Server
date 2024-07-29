package soma.haeya.edupi_db.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import soma.haeya.edupi_db.user.domain.Member;

@Slf4j
@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "save/signup", method = RequestMethod.POST)
    public ResponseEntity<String> createPost(@RequestBody Member member) {
        userService.saveUser(member);

        return ResponseEntity.ok().build();

    }
}
