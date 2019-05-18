package kssZamek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Lock {

    private String password;

    private String adminPassword;

    private Boolean unlocked;

    private Boolean adminBlocked = false;

    private  Boolean adminMode = false;

    private Integer failedAttempts = 0;

    public Integer getAttemptsLeft(){
        return 5 - failedAttempts;
    }

    public Lock(String password, String adminPassword) {
        this.password = password;
        this.adminPassword = adminPassword;
        unlocked = Boolean.FALSE;
    }

    public Boolean unlock(String inputPassword) throws AlreadyUnlockedException, AdminBlockedException {
        if(adminBlocked){
            throw new AdminBlockedException();
        }
        if (!Boolean.TRUE.equals(unlocked)) {
            if (this.password.equals(inputPassword)) {
                unlocked = Boolean.TRUE;
                failedAttempts = 0;
            }
            else {
                if(failedAttempts >= 4){
                    adminBlocked = Boolean.TRUE;
                }
                ++failedAttempts;
            }
            return unlocked;
        }
        else{
            throw new AlreadyUnlockedException();
        }
    }

    public void lock(){
        unlocked = Boolean.FALSE;
    }

    public void leaveAdminMode(){
        adminMode = Boolean.FALSE;
    }

    public Boolean getAdminMode() {
        return adminMode;
    }

    public Boolean enterAdminMode(String inputPassword) throws AlreadyInAdminModeException {
        if (!Boolean.TRUE.equals(adminMode)) {
            if (this.adminPassword.equals(inputPassword)) {
                adminMode = Boolean.TRUE;
                adminBlocked = Boolean.FALSE;
            }
            return adminMode;
        }
        else{
            throw new AlreadyInAdminModeException();
        }
    }

    public void changePassword(String newPassword) throws NotInAdminModeException, NotValidPasswordException, IOException {
        if(newPassword.length() != 6){
            throw new NotValidPasswordException();
        }

        if(Boolean.TRUE.equals(adminMode)){
            File passwordFile = new File(getClass().getResource("/resources/text/password.txt").getPath());
            FileOutputStream output = new FileOutputStream(passwordFile, false);
            output.write(newPassword.getBytes());
            password = newPassword;
            adminBlocked = Boolean.FALSE;
            failedAttempts = 0;
            output.close();
            return;
        }
        throw new NotInAdminModeException();
    }



}
