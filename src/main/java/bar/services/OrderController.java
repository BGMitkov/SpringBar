package bar.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bar.model.Order;
import bar.model.Role;

@Controller
public class OrderController {

	private static final String UNAUTHORIZED = "unauthorized";
	@Autowired
	private UserContext userContext;
	// private OrderDAO orderDAO;
	// private BillDAO billDAO;
	// TODO make the roles access configurable

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(@ModelAttribute() Order order) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.SERVER)) {
			return UNAUTHORIZED;
		}

		order.setOrderDate(new Date());
		order.calculateTotalPrice();
		// orderDAO.addOrder(order);
		// Bill bill = billDAO.getBill(order.getTableNumber());
		// bill.addOrder(order);
		return "items";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getWaitingOrders(ModelMap modelMap) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.BARTENDER)) {
			return UNAUTHORIZED;
		}

		// modelMap.addAllAttributes(orderDAO.getWaitingOrders());

		return "orders";
	}

	@RequestMapping(value = "/acceptedOrders", method = RequestMethod.GET)
	public String getAcceptedOrders(ModelMap modelMap) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.BARTENDER)) {
			return UNAUTHORIZED;
		}

		// modelMap.addAllAttributes(orderDAO.getAcceptedOrders(userContext.getUser()));
		// TODO decide whether this is part of the orders request
		return "acceptedOrders";
	}

	// TODO consider making this asynchrous request
	@PostMapping("/acceptOrder")
	public String acceptOrder(@ModelAttribute() String orderId) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.BARTENDER)) {
			return UNAUTHORIZED;
		}

		// Order order = orderDAO.findById(Long.parseLong(orderId));
		// if(order != null) {
		// if(order.getExecutor() == null) {
		// orderDAO.acceptOrder(order, userContext.getUser());
		// return "acceptedOrders";
		// } else {
		// return "conflict";
		// }
		// }
		return "orderNotFound";
	}

	@GetMapping(value = "/bill/{tableNumber}")
	public String printBill(@PathVariable("tableNumber") int tableNumber) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.SERVER)) {
			return UNAUTHORIZED;
		}

		
		return "bill";
	}

	// TODO setOrderAsOverdue method

	// TODO setOrderAsCompleteMethod

}
