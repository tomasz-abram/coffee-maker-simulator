package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.dto.CoffeeMachineConstantValueDto;
import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.service.CoffeeAdminService;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.RoleService;
import com.tabram.coffeemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private final UserService userService;
    private final CoffeeAdminService coffeeAdminService;
    private final RoleService roleService;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    @Autowired
    public AdminController(UserService userService, CoffeeAdminService coffeeAdminService, RoleService roleService, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.userService = userService;
        this.coffeeAdminService = coffeeAdminService;
        this.roleService = roleService;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @ModelAttribute("userD")
    public UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("machineConst")
    public CoffeeMachineConstantValueDto coffeeMachineConstantValueDto() {
        return new CoffeeMachineConstantValueDto();
    }

    @DeleteMapping(path = {"/admin/deactivate-user"})
    public String deactivationUser(@RequestParam("userId") Long userId) {
        userService.deactivationUser(userId);
        return "redirect:/admin/admin-users-list";
    }

    @GetMapping("/admin/admin-users-list")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("admin/admin-users-list");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @PutMapping("/admin/admin-update-user")
    public String updateUser(@ModelAttribute("userD") UserDto userDto) {
        coffeeAdminService.updateUser(userDto);
        return "redirect:/admin/admin-users-list";
    }

    @GetMapping("/admin/update-user-form")
    public ModelAndView showUpdateForm(@RequestParam Long userId) {
        ModelAndView mav = new ModelAndView("admin/admin-update-user");
        mav.addObject("userD", userService.findUserById(userId));
        mav.addObject("roles", roleService.getAllRoles());
        return mav;
    }

    @GetMapping("/admin/admin-coffee-machine-constant-value-list")
    public ModelAndView coffeeMachineConstValue() {
        ModelAndView mav = new ModelAndView("admin/admin-coffee-machine-constant-value-list");
        mav.addObject("constValues", coffeeMachineConstantValueService.getAllConstantValue());
        return mav;
    }

    @PutMapping("/admin/admin-update-coffee-machine-constant-value")
    public String updateCoffeeMachineConstValue(@ModelAttribute("machineConst") CoffeeMachineConstantValueDto coffeeMachineConstantValueDto) {
        coffeeMachineConstantValueService.updateConstantValue(coffeeMachineConstantValueDto);
        return "redirect:/admin/admin-coffee-machine-constant-value-list";
    }

    @GetMapping("/admin/update-const-form")
    public ModelAndView updateConstForm(@RequestParam Long machineConstId) {
        ModelAndView mav = new ModelAndView("admin/admin-update-coffee-machine-constant-value");
        mav.addObject("machineConst", coffeeMachineConstantValueService.findConstantValueById(machineConstId));
        return mav;
    }
}
