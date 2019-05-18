package kssZamek;

public class Lock {

    private String password;

    private String adminPassword;

    private Boolean locked;

    private Boolean adminBlocked = false;

    private  Boolean adminMode = false;

    private Integer failedAttempts = 0;

    public Lock(String password, String adminPassword) {
        this.password = password;
        this.adminPassword = adminPassword;
        locked = Boolean.FALSE;
    }

    public Boolean unlock(String inputPassword) throws AlreadyUnlockedException, AdminBlockedException {
        if(adminBlocked){
            throw new AdminBlockedException();
        }
        if (!Boolean.TRUE.equals(locked)) {
            if (this.password.equals(inputPassword)) {
                locked = Boolean.TRUE;
            }
            else {
                if(failedAttempts >= 5){
                    adminBlocked = Boolean.TRUE;
                }
                ++failedAttempts;
            }
            return locked;
        }
        else{
            throw new AlreadyUnlockedException();
        }
    }

    public void lock(){
        locked = Boolean.TRUE;
    }

    public Boolean enterAdminMode(String inputPassword) throws AlreadyInAdminModeException {
        if (!Boolean.TRUE.equals(adminMode)) {
            if (this.adminPassword.equals(inputPassword)) {
                adminMode = Boolean.TRUE;
            }
            return adminMode;
        }
        else{
            throw new AlreadyInAdminModeException();
        }
    }

    public void changePassword(String newPassword) throws NotInAdminModeException {
        if(Boolean.TRUE.equals(adminMode)){
            password = newPassword;
            adminBlocked = Boolean.FALSE;
        }
        throw new NotInAdminModeException();
    }

}
