package com.back.sbb.answer;

import com.back.sbb.question.Question;
import com.back.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/answer/create/{id}")
    public String createAnswer(@PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm,
                               BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);

        if (bindingResult.hasErrors()) {
            return "question_detail";
        }

        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}