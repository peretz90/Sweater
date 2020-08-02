package by.peretz90.sweater.controllers;

import by.peretz90.sweater.domain.Role;
import by.peretz90.sweater.domain.User;
import by.peretz90.sweater.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  public final UserService userService;

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  public String userList(Model model) {
    model.addAttribute("users", userService.findAll());
    return "userList";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("{userId}")
  public String userEditForm(@PathVariable Long userId, Model model) {
    User user = userService.findById(userId);
    model.addAttribute("user", user);
    model.addAttribute("roles", Role.values());
    return "userEdit";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public String userSave(
      @RequestParam String username,
      @RequestParam Map<String, String> form,
      @RequestParam(name = "userId") Long userId
  ) {
    userService.saveUser(userId, username, form);
    return "redirect:/user";
  }

  @GetMapping("profile")
  public String getProfile(Model model, @AuthenticationPrincipal User user) {
    model.addAttribute("username", user.getUsername());
    model.addAttribute("email", user.getEmail());

    return "profile";
  }

  @PostMapping("profile")
  public String updateProfile(
      @AuthenticationPrincipal User user,
      @RequestParam String password,
      @RequestParam String email
  ) {
    userService.updadeProfile(user, password, email);
    return "redirect:/user/profile";
  }
}
