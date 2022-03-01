package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.techjobs.mvc.controllers.ListController.*;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "search", method = RequestMethod.POST)
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @GetMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType,@RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase(Locale.ROOT).equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columnsHead", columnsHead);

        return "search";
    }

}
