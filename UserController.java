package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    
    @RequestMapping("/")
    public String showLoginPage(Model model) 
    {
        model.addAttribute("userEntity", new UserEntity());
        return "index";
    }

    
    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute UserEntity userEntity, Model model) 
    {
        if (userEntity.getPrnNumber() != null && userEntity.getUserName() != null) 
        {
            userRepository.save(userEntity);
            return "redirect:/placement-form";
        }
        model.addAttribute("error", "Invalid PRN or Name");
        return "index";
    }
    
	/*
	 * @RequestMapping(value="/save", method=RequestMethod.POST) public ModelAndView
	 * save(@ModelAttribute UserEntity userEntity, Model model) {
	 * userRepository.save(userEntity); ModelAndView modelAndView = new
	 * ModelAndView(); modelAndView.setViewName("user-data");
	 * modelAndView.addObject("UserEntity", userEntity); return modelAndView ;
	 * 
	 * }
	 */


    @RequestMapping("/placement-form")
    public String showPlacementForm(Model model) 
    {
        model.addAttribute("placementEntity", new PlacementEntity());
        return "placementform";
    }

    
    @PostMapping("/savePlacement")
    public String savePlacement(@ModelAttribute PlacementEntity placementEntity, Model model) {
        {
            // Assuming there's a PlacementRepository, you can save the placement data here
            // placementRepository.save(placementEntity);
            return "redirect:/placement-data"; // Redirect to a page showing the saved placement data
        }
      
    }
}
