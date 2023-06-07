package jforce.hrms.core.utilities.helpers;

import jforce.hrms.core.utilities.results.Result;
import jforce.hrms.entities.User;

public interface EmailService {
    Result send(User user);
    Result check(String email);
    Result checkWithDomain(String email, String website);
}
