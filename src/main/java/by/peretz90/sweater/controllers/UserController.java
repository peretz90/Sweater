package by.peretz90.sweater.controllers;

import by.peretz90.sweater.domain.Role;
import by.peretz90.sweater.domain.User;
import by.peretz90.sweater.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class UserController {

  public final UserRepo userRepo;

  @GetMapping
  public String userList(Model model) {
    model.addAttribute("users", userRepo.findAll());
    return "userList";
  }

  @GetMapping("{userId}")
  public String userEditForm(@PathVariable Long userId, Model model) {
    User user = userRepo.findById(userId).orElseThrow();
    model.addAttribute("user", user);
    model.addAttribute("roles", Role.values());
    return "userEdit";
  }

  @PostMapping
  public String userSave(
      @RequestParam String username,
      @RequestParam Map<String, String> form,
      @RequestParam(name = "userId") Long userId
  ) {
    User user = userRepo.findById(userId).orElseThrow();
    user.setUsername(username);

    Set<String> roles = Arrays.stream(Role.values())
        .map(Role::name)
        .collect(Collectors.toSet());

    user.getRoles().clear();

    for (String key : form.keySet()) {
      if (roles.contains(key)) {
        user.getRoles().add(Role.valueOf(key));
      }
    }
    userRepo.save(user);
    return "redirect:/user";
  }
}
