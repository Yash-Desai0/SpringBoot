package com.example.book.controller;

import com.example.book.entity.User;
import com.example.book.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/")
    public String showIndex(Model model)
    {
        List<String> countries = Arrays.asList("USA", "UK", "Canada", "Australia");
        model.addAttribute("countries", countries);
        /*
        List<String> states = Arrays.asList("Amreli", "Mumbai", "Gujarat");
        List<String> cities = Arrays.asList("Ahmedabad", "Surat", "Vadodara");
        List<String> hobbies = Arrays.asList("Reading", "Sports", "Music", "Cooking");
        model.addAttribute("states", states);
        model.addAttribute("cities", cities);
        model.addAttribute("hobbies", hobbies);
        */
        model.addAttribute("saveuser",new User());
        return "index";
    }

    @PostMapping("/save")
    public String saveUSer(@ModelAttribute("saveuser") @Validated User user, BindingResult result, Model model,@RequestParam("profilePic") MultipartFile file)
    {
        if(file.isEmpty())
        {
            user.setImageName("null");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = "src/main/resources/uploads/" + fileName;
            user.setImageName(fileName);    // set the Image name into entity.
            File newFile = new File(filePath);
            newFile.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(file.getBytes());
            fos.close();

            String message = "File '"+ fileName + "' uploaded at '"+filePath+"' successfully";
            System.out.println(message);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        registerService.save(user);
        System.out.println(user);
        return "redirect:/";
    }
}
