package bar.services;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bar.model.Order;
import bar.model.Role;

@Controller
public class OrderController {
	
	private UserContext userContext;
	//private OrderDAO orderDAO;
	
	@RequestMapping(value="/order", method = RequestMethod.POST)
	public String order(@ModelAttribute() Order order) {
		if(!userContext.isUserInRole(Role.MANAGER, Role.SERVER)) {
			return "unauthorized";
		}
		
		order.setOrderDate(new Date());
		order.calculateTotalPrice();
		//orderDAO.addOrder(order);
		
		return "items";
	}
}
