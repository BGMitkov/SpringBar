package bar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.repository.BillRepository;

@Service
public class BillService {
	@Autowired
	private BillRepository billRepository;
}
