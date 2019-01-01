package bar.service;

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

import bar.dao.BillDAO;
import bar.dao.OrderDAO;
import bar.model.Bill;
import bar.model.BillStatus;
import bar.model.Order;
import bar.model.OrderStatus;

@Controller
@RequestMapping("/orders")
public class OrderController {

	private static final String UNAUTHORIZED = "unauthorized";

	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private BillDAO billDAO;
	@Autowired
	private SecurityService securityService;
	// TODO make the roles access configurable

	// TODO create OrderDTO
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(@ModelAttribute() Order order) {
		Bill bill = billDAO.findByTableNumberAndStatus(order.getTable(), BillStatus.OPEN);
		if (bill == null) {
			bill = billDAO.save(new Bill(order.getTable(), BillStatus.OPEN));
		}
		order.setOrderDate(new Date().getTime());
		bill.addOrder(order);
		return "items";
	}

	// TODO convert to rest
	@RequestMapping(value = "/waiting", method = RequestMethod.GET)
	public String getWaitingOrders(ModelMap modelMap) {

		modelMap.addAllAttributes(orderDAO.findByStatus(OrderStatus.WAITING));

		return "orders";
	}

	// TODO decide to change the accepted to preparing status
	// TODO convert this to REST api
	@RequestMapping(value = "/accepted", method = RequestMethod.GET)
	public String getAcceptedOrders(ModelMap modelMap) {

		modelMap.addAllAttributes(orderDAO.findByExecutorAndStatus(securityService.getUser(), OrderStatus.ACCEPTED));

		return "acceptedOrders";
	}

	// TODO convert to rest
	@PostMapping("/accept")
	public String acceptOrder(@ModelAttribute() String orderId) {

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

		return "bill";
	}

	// TODO setOrderAsOverdue method

	// TODO setOrderAsCompleteMethod

}
