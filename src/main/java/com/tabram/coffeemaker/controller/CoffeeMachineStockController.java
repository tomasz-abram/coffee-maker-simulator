package com.tabram.coffeemaker.controller;

import com.tabram.coffeemaker.model.CoffeeMachineStock;
import com.tabram.coffeemaker.service.CoffeeMachineConstantValueService;
import com.tabram.coffeemaker.service.CoffeeMachineStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock-coffee-machine")
public class CoffeeMachineStockController {

    private static final String REDIRECT = "redirect:/stock-coffee-machine";
    private final CoffeeMachineStockService coffeeMachineStockService;
    private final CoffeeMachineConstantValueService coffeeMachineConstantValueService;

    @Autowired
    public CoffeeMachineStockController(CoffeeMachineStockService coffeeMachineStockService, CoffeeMachineConstantValueService coffeeMachineConstantValueService) {
        this.coffeeMachineStockService = coffeeMachineStockService;
        this.coffeeMachineConstantValueService = coffeeMachineConstantValueService;
    }

    @ModelAttribute("coffeeMachineStock")
    public CoffeeMachineStock coffeeMachineStock() {
        return new CoffeeMachineStock();
    }

    @GetMapping
    public ModelAndView getStock() {
        ModelAndView mav = new ModelAndView("stock-coffee-machine");
        mav.addObject("waterStock", coffeeMachineStockService.findStockByName("Water"));
        mav.addObject("milkStock", coffeeMachineStockService.findStockByName("Milk"));
        mav.addObject("coffeeBeansStock", coffeeMachineStockService.findStockByName("Coffee beans"));
        mav.addObject("groundContainerStock", coffeeMachineStockService.findStockByName("Ground container"));
        mav.addObject("descaleCounter", coffeeMachineStockService.findStockByName("Descale counter"));
        mav.addObject("waterHardnessStock", coffeeMachineStockService.findStockByName("Water hardness"));
        mav.addObject("machine", coffeeMachineConstantValueService);
        coffeeMachineStockService.checkStockStatus();
        return mav;
    }

    @PutMapping("/water-hardness")
    public String setWaterHardness(@RequestParam("waterHardness") float value) {
        coffeeMachineStockService.updateWaterHardness(value);
        return REDIRECT;
    }

    @PutMapping("/fill-water-tank")
    public String fillWater(@RequestParam("quantity") int quantity) {
        coffeeMachineStockService.updateWater(quantity);
        return REDIRECT;
    }

    @PutMapping("/empty-water-tank")
    public String emptyWater() {
        coffeeMachineStockService.emptyWater();
        return REDIRECT;
    }

    @PutMapping("/fill-milk-tank")
    public String fillMilk(@RequestParam int quantity) {
        coffeeMachineStockService.updateMilk(quantity);
        return REDIRECT;
    }

    @PutMapping("/empty-milk-tank")
    public String emptyMilk() {
        coffeeMachineStockService.emptyMilk();
        return REDIRECT;
    }

    @PutMapping("/fill-beans-container")
    public String fillBeans(@RequestParam int quantity) {
        coffeeMachineStockService.updateBeans(quantity);
        return REDIRECT;
    }

    @PutMapping("/empty-coffee-beans")
    public String emptyCoffeeBeans() {
        coffeeMachineStockService.emptyCoffeeBeans();
        return REDIRECT;
    }

    @PutMapping("/empty-ground-container")
    public String cleanGroundContainer() {
        coffeeMachineStockService.emptyGroundContainer();
        return REDIRECT;
    }

    @PutMapping("/descale")
    public String descale() {
        coffeeMachineStockService.descale();
        return REDIRECT;
    }
}
