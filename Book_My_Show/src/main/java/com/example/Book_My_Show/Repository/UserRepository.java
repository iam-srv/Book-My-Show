package com.example.Book_My_Show.Repository;



import com.example.Book_My_Show.Entites.UserEntity;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
}
