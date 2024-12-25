package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        return User.builder()
                .username(employee.getPhoneNumber())
                .password(employee.getPassword())
                .roles(employee.getRole().name())
                .build();
    }
}
