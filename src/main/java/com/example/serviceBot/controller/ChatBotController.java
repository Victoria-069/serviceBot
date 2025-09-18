package com.example.serviceBot.controller;

import com.example.serviceBot.dto.Feedback;
import com.example.serviceBot.model.UserSession;
import com.example.serviceBot.model.Role;
import com.example.serviceBot.service.OpenAIService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatBotController {

    @Autowired
    private OpenAIService openAIService;

    private final Map<String, UserSession> sessions = new HashMap<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    @GetMapping("/start")
    public String start(@RequestParam String userId) {
        if (!sessions.containsKey(userId)) {
            sessions.put(userId, new UserSession());
            return "Вітаємо! Виберіть вашу роль: Механік, Електрик, Менеджер";
        }
        return "Сесія вже існує. Виберіть роль через /message";
    }

    @GetMapping("/message")
    public String message(@RequestParam String userId, @RequestParam String text) {
        UserSession session = sessions.get(userId);
        if (session == null) return "Спробуйте спочатку /start";

        if (session.getRole() == null) {
            // Перевіряємо, чи текст відповідає будь-якій ролі
            Optional<Role> matchedRole = Arrays.stream(Role.values())
                    .filter(r -> r.name().equalsIgnoreCase(text.trim()))
                    .findFirst();

            if (matchedRole.isPresent()) {
                session.setRole(matchedRole.get());
                return "Ви обрали роль " + matchedRole.get() + ". Тепер введіть вашу філію:";
            } else {
                return "Невідома роль. Спробуйте: Механік, Електрик, Менеджер";
            }
        }

        if (session.getBranch() == null) {
            if (text.trim().isEmpty()) {
                return "Назва філії не може бути порожньою. Введіть філію:";
            }

            session.setBranch(text.trim());
            return "Дякуємо! Ви обрали філію: " + session.getBranch() + ". Тепер можна залишати відгуки.";
        }
        return "Можете залишати відгуки або інші повідомлення.";
    }

    @GetMapping("/feedback")
    public String feedback(@RequestParam String userId, @RequestParam String text) throws Exception {
        UserSession session = sessions.get(userId);
        if (session == null || session.getRole() == null || session.getBranch() == null)
            return "Спочатку виберіть роль та філію через /start";

        Feedback fb = new Feedback(text, session.getRole().toString(), session.getBranch());
        feedbacks.add(fb);

        JSONObject analysis = openAIService.analyzeFeedback(text);
        return "Дякуємо за відгук! Аналіз: " + analysis.toString();
    }

}
