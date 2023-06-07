package jforce.hrms.core.adapters.abstracts;

public interface UserCheckService {
    boolean isRealPerson(String identityNo, String firstName, String lastName, int birthYear);
}