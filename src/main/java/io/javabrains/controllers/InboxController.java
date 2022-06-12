package io.javabrains.controllers;

import io.javabrains.Folder;
import io.javabrains.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InboxController {

    @Autowired
    private FolderRepository folderRepository;
    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal OAuth2User principal,
                           Model model) {
        if (principal == null || !StringUtils.hasText(principal.getAttribute("login"))) {
            return "index";
        }
            String userId = principal.getAttribute("login");
            List<Folder> userFolders = folderRepository.findAllByUserId(userId);
            model.addAttribute("userFolder",userFolders);

        return "inbox-home";
    }
}
