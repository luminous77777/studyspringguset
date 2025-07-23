package com.devlumi.guestbook.service;

import com.devlumi.guestbook.dto.GuestbookDTO;
import com.devlumi.guestbook.repository.GuestbookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  GuestbookRepository repository;

  @Override
  public Long register(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  @Override
  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElseThrow(() -> new RuntimeException("글번호 없음")));
  }

  @Override
  public List<GuestbookDTO> readAll() {
    return repository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public int modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
    return repository.existsById(guestbookDTO.getGno()) ? 1 : 0;
  }

  @Override
  public int remove(Long gno) {
    try{
      repository.deleteById(gno);
      return 1;
    }catch(Exception e){
      return 0;
    }
  }
}
