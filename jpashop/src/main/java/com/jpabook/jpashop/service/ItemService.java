package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
    // findItem으로 찾아온 이 객체는 영속성이기에, 커밋이 된다. (후에 flush(변경된 것 찾아서 변경시킴))
    // 어설프게 파라미터로 엔티티를 쓰지말고, 필요한 객체만 뽑아와서 업데이트에 사용한다.
    // 업데이트 할게 많다? => DTO를 만들자

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}

// 서비스 클래스는 보통 리포지토리의 기능을 단순 위임하는 코드이다.
// 상황에 따라서는 이를 꼭 작성해야하는지도 살펴보는 것도 좋다.