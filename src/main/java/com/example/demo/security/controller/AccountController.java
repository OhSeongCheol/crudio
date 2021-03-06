package com.example.demo.security.controller;

import com.example.demo.security.domain.Account;
import com.example.demo.security.domain.AccountDto;
import com.example.demo.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/createAccount")
    public Account createAccount(){
        // 클라이언트에서 전달 받을 Account DTO(더미 객체로 대체)
        AccountDto accountDto = AccountDto.getThemeVo();

        // Account 객체로 변환
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);

        // 패스워드 암호화
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Account 저장
        accountService.createAccount(account);

        return account;
    }

    @GetMapping("/getAccount")
    public List<Account> getAccount(){
        List<Account> accountList = accountService.readAllAccount();
        System.out.println(accountList);
        return accountList;
    }

}
