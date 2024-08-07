package com.challenge7user.challenge7user.Controller;

import com.challenge7user.challenge7user.Repository.UserRepository;
import com.challenge7user.challenge7user.Service.UserService;
import com.challenge7user.challenge7user.Utils.Response;
import com.challenge7user.challenge7user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Response response;

    @GetMapping(value = {"/all-User", "/all-User/"})
    public ResponseEntity<Map> getListUser() {
        return new ResponseEntity<>(response.sucsess(userService.getAllUser()), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> saveProduct(@RequestBody User request) {
        Map obj = userService.addUser(request);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping(value = {"/update/{userId}", "/update/{userId}/"})
    public ResponseEntity<Map> updateUser(@RequestBody User request, @PathVariable("userId") UUID UserId) {
        Map obj = userService.updateUser(UserId, request);
        return new ResponseEntity<>(response.sucsess(obj), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{userId}", "/delete/{userId}/"})
    public ResponseEntity<Map> deleteUser(@PathVariable("userId") UUID UserId) {
        return new ResponseEntity<>(userService.deleteUser(UserId), HttpStatus.OK);
    }

    @GetMapping(value = {"/get/{UserId}", "/get/{UserId}/"})
    public ResponseEntity<Map> getById(@PathVariable("UserId") UUID UserId) {
        return new ResponseEntity<>(userService.getUserById(UserId), HttpStatus.OK);
    }

    @GetMapping(value = { "/list-Product", "/list-Product/" })
    public ResponseEntity<Map> list(@RequestParam(required = false, name = "username") String username,
                                    @RequestParam(required = false, name = "emailAddress") String emailAddress,
                                    @RequestParam(required = false, name = "password") String password,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Specification<User> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (username != null && !username.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
            }
            if (emailAddress != null) {
                predicates.add(criteriaBuilder.equal(root.get("emailAddress"), emailAddress));
            }
            if (password != null) {
                predicates.add(criteriaBuilder.equal(root.get("password"), password));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<User> userList = userRepository.findAll(spec, pageable);
        return new ResponseEntity<>(response.sucsess(userList), HttpStatus.OK);
    }
}
