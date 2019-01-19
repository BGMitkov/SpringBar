package bar.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bar.model.Bill;
import bar.model.BillStatus;
import bar.model.Order;
import bar.model.OrderStatus;
import bar.repository.BillRepository;
import bar.repository.OrderRepository;
import bar.service.SecurityService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private SecurityService securityService;

	// TODO create OrderDTO
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(@ModelAttribute() Order order) {
		Bill bill = billRepository.findByTableNumberAndStatus(order.getTable(), BillStatus.OPEN);
		if (bill == null) {
			bill = billRepository.save(new Bill(order.getTable(), BillStatus.OPEN));
		}
		order.setOrderDate(new Date().getTime());
		bill.addOrder(order);
		return "items";
	}

	// TODO convert to rest - in progress
	@GetMapping(value = "/orders/{status}", produces = "application/json")
	public @ResponseBody List<Order> getWaitingOrders(@PathVariable String status) {
		OrderStatus orderStatus = null;
		try {
			orderStatus = OrderStatus.valueOf(status);
		} catch (IllegalArgumentException e) {
			log.info("Exception occured in method getWaitingOrders");
			return null;
		}
		return orderRepository.findByStatus(orderStatus);
	}

	// TODO decide to change the accepted to preparing status
	// TODO convert this to REST api
	@RequestMapping(value = "/accepted", method = RequestMethod.GET)
	public String getAcceptedOrders(ModelMap modelMap) {

		modelMap.addAllAttributes(orderRepository.findByExecutorAndStatus(securityService.getUser(), OrderStatus.ACCEPTED));

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
