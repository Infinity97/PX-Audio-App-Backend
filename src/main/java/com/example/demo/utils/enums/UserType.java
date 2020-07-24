package com.example.demo.utils.enums;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum UserType {

    ADMIN(0, "Admin"),
    EMPLOYEE(1, "Employee"),
    DEALER(2,"A Person who is assigned as a seller from the admin"),
    SELLER(3,
            "A Person who sells any products they have irrespective of whether they are assigned to sell it or not."),
    SERVICE(4, "Service Franchise operator"),
    CUSTOMER(100, "Customer"),
    GUEST(101,"Customer who has not logged in yet!! or Whose Auth has Failed/Incomplete So Far");

    private final int hierarchy;
    private final String message;

    UserType(int hierarchy, String message) {
        this.hierarchy = hierarchy;
        this.message = message;
    }

    public UserType getEnumValueFromHierarchy(int hierarchy){
        for (UserType userType : UserType.values()) {
            log.info("THe following is the UserType {} ", userType);
            if(userType.hierarchy == hierarchy)
                return userType;
        }
        return null;
    }
}
