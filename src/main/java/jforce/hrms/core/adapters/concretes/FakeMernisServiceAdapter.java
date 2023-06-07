package jforce.hrms.core.adapters.concretes;

import jforce.hrms.core.adapters.abstracts.UserCheckService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FakeMernisServiceAdapter implements UserCheckService {
    public boolean isRealPerson(String identityNo, String firstName, String lastName, int birthYear) {
        return true;
    }
}