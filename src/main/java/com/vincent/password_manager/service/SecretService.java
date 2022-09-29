package com.vincent.password_manager.service;

import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.springframework.stereotype.Service;

import com.vincent.password_manager.bean.SecretOption;

@Service
public class SecretService 
{
    public String generateSecret(int length, int lowerCase, int upperCase, int digitCase, int specialCase) 
    {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "ERROR_CODE";
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);

        if(length < (lowerCase + upperCase + digitCase + specialCase))
        {
            lowerCase = 1;
            upperCase = 1;
            digitCase = 1;
            specialCase = 1;
        }

        List<CharacterRule> ruleList = new ArrayList<>();

        if(lowerCase >= 1)
        {
            lowerCaseRule.setNumberOfCharacters(lowerCase);
            ruleList.add(lowerCaseRule);
        }
        
        if(upperCase >= 1)
        {
            upperCaseRule.setNumberOfCharacters(upperCase);
            ruleList.add(upperCaseRule);
        }
        
        if(digitCase >= 1)
        {
            digitRule.setNumberOfCharacters(digitCase);
            ruleList.add(digitRule);
        }
        
        if(specialCase >= 1)
        {
            splCharRule.setNumberOfCharacters(specialCase);
            ruleList.add(splCharRule);
        }

        String password;

        if(ruleList.size() == 0)
        {
            lowerCaseRule.setNumberOfCharacters(1);
            ruleList.add(lowerCaseRule);
        }

        if(length < 4)
        {
            password = gen.generatePassword(4, ruleList);

            password = password.substring(0, length);
        }
        else
        {
            password = gen.generatePassword(length, ruleList);
        }

        return password;
    }

    public String generateSecret(SecretOption secretOption)
    {
        return this.generateSecret(secretOption.getLength(), secretOption.getLowerCase(), secretOption.getUpperCase(), secretOption.getDigitCase(), secretOption.getSpecialCase());
    }
}
