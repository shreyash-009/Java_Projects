package com.codebridge.studentmanagementsystem.service;

import com.codebridge.studentmanagementsystem.model.SignUp;
import com.codebridge.studentmanagementsystem.view.SignupPage;

import java.util.List;

public interface SignupService {
    public void SaveCredentials(SignUp signup);
    public boolean checkCredentials(SignUp credentials);
    public boolean usernameExists(String username);

}
