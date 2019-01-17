package bar.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import bar.model.Item;
import bar.repository.ItemRepository;

@Component
public class ItemReceiver {
	private static final Logger logger = LoggerFactory.getLogger(ItemReceiver.class);

	@Autowired
	private ItemRepository itemRepository;

	@JmsListener(destination = "itemRepository", containerFactory = "myFactory")
	public void receiveItem(Item item) {
		logger.info("Received {}", item);
		itemRepository.save(item);
	}
}
