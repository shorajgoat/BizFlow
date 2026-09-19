package com.himal.jewellery.boxorder;

import com.himal.jewellery.exception.BoxOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoxOrderService {
    @Autowired
    private BoxOrderRepository boxOrderRepository;

    public Page<BoxOrderResponseDto> getAll(Pageable pageable) {
        return boxOrderRepository.findAll(pageable).map(b -> new BoxOrderResponseDto(b));
    }
    public BoxOrderResponseDto getById(Long id) {
        BoxOrder b = boxOrderRepository.findById(id)
                .orElseThrow(() -> new BoxOrderNotFoundException("Box order not found with id: " + id));
        return new BoxOrderResponseDto(b);
    }
    public BoxOrderResponseDto create(BoxOrderRequestDto dto) {
        BoxOrder b = new BoxOrder(dto.getBoxType(), dto.getQuantity(), dto.getCostPerUnit(), dto.getSource());
        return new BoxOrderResponseDto(boxOrderRepository.save(b));
    }
    public BoxOrderResponseDto updateStatus(Long id, String status) {
        BoxOrder b = boxOrderRepository.findById(id)
                .orElseThrow(() -> new BoxOrderNotFoundException("Box order not found with id: " + id));
        b.setStatus(status);
        return new BoxOrderResponseDto(boxOrderRepository.save(b));
    }
    public void delete(Long id) {
        if (!boxOrderRepository.existsById(id))
            throw new BoxOrderNotFoundException("Box order not found with id: " + id);
        boxOrderRepository.deleteById(id);
    }
}