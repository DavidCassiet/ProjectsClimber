package com.dc.projectsclimber.service;

import com.dc.projectsclimber.dto.Userdto;
import com.dc.projectsclimber.entity.User;
import com.dc.projectsclimber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Userdto createUser(User user) {
        userRepository.save(user);

        Userdto userdto = new Userdto();
        userdto.setId(user.getId());
        userdto.setName(user.getName());
        userdto.setSurname(user.getSurname());
        userdto.setCountry(user.getCountry());
        return userdto;
    }

    public List<Userdto> getUsers(String city, LocalDate dateTime) {
        List<User> allUsers = new ArrayList<>();
        if (Objects.isNull(city) && Objects.isNull(dateTime)) {
            allUsers = userRepository.findAll();
        } else if (Objects.nonNull(city)) {
            allUsers = userRepository.findByCity(city);
        } else if (Objects.nonNull(dateTime)) {
            allUsers = userRepository.findByCreationDateAfter(dateTime.atStartOfDay());
        }
        List<Userdto> allUsersDto = new ArrayList<>();
        for (User user : allUsers) {
            Userdto userdto = new Userdto();
            userdto.setId(user.getId());
            userdto.setName(user.getName());
            userdto.setSurname(user.getSurname());
            userdto.setCountry(user.getCountry());
            allUsersDto.add((userdto));
        }
        return allUsersDto;
    }

    public Userdto editUser(Long idUser, User user) {
        User actualUser = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        actualUser.setName(user.getName());
        actualUser.setSurname(user.getSurname());
        actualUser.setPassword(user.getPassword());
        actualUser.setCity(user.getCity());
        actualUser.setProvince(user.getProvince());
        actualUser.setCountry(user.getCountry());
        userRepository.save(actualUser);

        Userdto userdto = new Userdto();
        userdto.setId(actualUser.getId());
        userdto.setName(actualUser.getName());
        userdto.setSurname(actualUser.getSurname());
        userdto.setCountry(actualUser.getCountry());
        return userdto;
    }

    public boolean deleteUser(Long idUser, String name, String password) {
        User actualUser = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if ((actualUser.getName().equals(name)) && (actualUser.getPassword().equals(password))) {
            userRepository.deleteById(idUser);
            return true;
        } else { return false; }
    }
}
