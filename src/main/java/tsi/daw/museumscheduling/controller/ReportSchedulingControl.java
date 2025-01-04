package tsi.daw.museumscheduling.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import tsi.daw.museumscheduling.dao.DAO;
import tsi.daw.museumscheduling.dao.service.SchedulingService;
import tsi.daw.museumscheduling.enums.UserProfile;
import tsi.daw.museumscheduling.interfaces.Messages;
import tsi.daw.museumscheduling.model.AppUser;
import tsi.daw.museumscheduling.model.Museum;
import tsi.daw.museumscheduling.model.Scheduling;
import tsi.daw.museumscheduling.utils.MuseumUtil;

@Controller
public class ReportSchedulingControl {
	
	@RequestMapping("report_day_time")
	public String reportDayTime(Model model) {
		return "reports/report_day_time";
	}
	
	@RequestMapping("report_day")
	public String reportDay() {
		return "reports/report_day";
	}
	
	@RequestMapping("report_details")
	public String reportDayTimeDetails(Model model) {
		return "reports/report_details";
	}
	
	@ResponseBody
	@RequestMapping(value = "getTimesMuseum", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getTimesMuseum(HttpSession session) {
	    
		AppUser user = (AppUser) session.getAttribute("user");
	    UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");
	    
	    DAO<Museum> daoMuseum = new DAO<>(Museum.class);
	    Set<String> uniqueTimes = new TreeSet<>();
	    
	    if (UserProfile.ADMIN.equals(userProfile)) {
	        for (Museum museum : daoMuseum.listAll())
	            uniqueTimes.addAll(MuseumUtil.getTimes(museum.getId()));
	    } else
	        uniqueTimes.addAll(MuseumUtil.getTimes(user.getMuseum().getId()));
	    
	    return new ArrayList<>(uniqueTimes);
	}
	
	@RequestMapping("reportDayTime")
	public String reportDayTime(@RequestParam("date") String dateStr, @RequestParam("time") String timeStr, HttpSession session, Model model) {
		
		try (SchedulingService schedulingService = new SchedulingService()){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(dateStr, formatter);
			LocalTime time = LocalTime.parse(timeStr);
			
			AppUser user = (AppUser) session.getAttribute("user");
			boolean isAdmin = user.getUserProfile() == UserProfile.ADMIN;
			
			List<Scheduling> schedulings = schedulingService.findSchedulingByDayAndTime(date, time, isAdmin, user.getMuseum().getId());
			
			if (schedulings.isEmpty()) {
				model.addAttribute("messageReturn", String.format("Nenhum agendamento encontrado para %s às %s", dateStr, timeStr));
				
				return "reports/report_day_time";
			}
				
			model.addAttribute("schedulings", schedulings);
			model.addAttribute("formattedDate", dateStr);
			
			return "reports/report_details";
		}
	}
	
	@RequestMapping("reportDay")
	public String reportDay(@RequestParam("date") String dateStr, HttpSession session, Model model) {
		
		try (SchedulingService schedulingService = new SchedulingService()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(dateStr, formatter);
			
			AppUser user = (AppUser) session.getAttribute("user");
			boolean isAdmin = user.getUserProfile() == UserProfile.ADMIN;
			
			List<Scheduling> schedulings = schedulingService.findSchedulingByDay(date, isAdmin, user.getMuseum().getId());
			
			if (schedulings.isEmpty()) {
				model.addAttribute("messageReturn", String.format(Messages.MESSAGE_RETURN_REPORT, dateStr));
				
				return "reports/report_day";
			}
				
			model.addAttribute("schedulings", schedulings);
			model.addAttribute("formattedDate", dateStr);
			model.addAttribute("numberScheduling", schedulings.size());
			
			return "reports/report_day_scheduling";
		}
	}
}
