package by.peretz90.sweater.controllers;

import by.peretz90.sweater.domain.Message;
import by.peretz90.sweater.domain.User;
import by.peretz90.sweater.repos.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class MainController {

  public final MessageRepo messageRepo;

  @Value("${upload.path}")
  private String uploadPath;

  @GetMapping("/")
  public String greeting() {
    return "greeting";
  }
  @GetMapping("/main")
  public String main(
      @RequestParam(required = false, defaultValue = "") String filter,
      Model model
  ) {
    Iterable<Message> messages;

    if (filter != null && !filter.isEmpty()) {
      messages = messageRepo.findByTag(filter);
    } else {
      messages = messageRepo.findAll();
    }
    model.addAttribute("messages", messages);
    model.addAttribute("filter", filter);
    return "main";
  }

  @PostMapping("/main")
  public String addMessage(
      @AuthenticationPrincipal User user,
      @RequestParam String text,
      @RequestParam String tag,
      @RequestParam("file") MultipartFile file,
      Model model
  ) throws IOException {
    Message message = new Message(text, tag, user);
    if (file != null && !file.getOriginalFilename().isEmpty()) {
      File uploadDir = new File(uploadPath);
      if (!uploadDir.exists()) {
        uploadDir.mkdir();
      }
      String uuidFile = UUID.randomUUID().toString();
      String resultFilename = uuidFile + "." + file.getOriginalFilename();
      file.transferTo(new File(uploadPath + "/" +resultFilename));
      message.setFilename(resultFilename);
    }
    messageRepo.save(message);
    Iterable<Message> messages = messageRepo.findAll();
    model.addAttribute("messages", messages);
    return "main";
  }

}
