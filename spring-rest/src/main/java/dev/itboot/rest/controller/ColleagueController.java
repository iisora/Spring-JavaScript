package dev.itboot.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.itboot.rest.mapper.ColleagueMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ColleagueController {

	private final ColleagueMapper mapper;

	@GetMapping("/")
	public String getAllColleagues(Model model) {
		model.addAttribute("page", mapper.selectAll());

		return "list";
	}

}
