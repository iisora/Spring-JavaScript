package com.dev.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.personal.model.Colleague;
import com.dev.personal.service.ColleagueService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ColleagueController {

//	private final ColleagueMapper mapper;
	private final ColleagueService service;

	@GetMapping("/list")
	public String getAllColleagues(Model model) {
//		model.addAttribute("page", mapper.selectAll());
		model.addAttribute("page", service.selectAll());
		return "list";
	}

	@GetMapping("/add")
	public String addColleague(@ModelAttribute Colleague colleague) {
		return "form";
	}

	@PostMapping("/process")
	public String process(@Validated @ModelAttribute Colleague colleague, BindingResult result) {
		if (result.hasErrors()) {
			log.error("バリデーションエラー発生: {}", result);
			return "form";
		}
		service.save(colleague);
		return "redirect:/list";
	}

	@GetMapping("/edit/{id}")
	public String editColleague(@PathVariable Long id, Model model) {
		model.addAttribute("colleague", service.selectByPrimaryKey(id));
		return "form";
	}

	@GetMapping("/delete/{id}")
	public String deleteColleague(@PathVariable Long id) {
		service.deleteByPrimaryKey(id);
		return "redirect:/list";
	}

}
