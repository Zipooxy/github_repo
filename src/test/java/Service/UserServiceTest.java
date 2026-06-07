package Service;

import Exception.InvalidRegistrationException;
import Exception.LoginException;
import Exception.UserNotFoundException;
import Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserService Tests")
public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @DisplayName("registerUser menyimpan user baru")
    void testRegisterUser() {
        User user = new User(1, "andi", "pass123", 50000.0);
        userService.registerUser(user);
        // Login untuk verifikasi user tersimpan
        assertDoesNotThrow(() -> {
            User loggedIn = userService.loginUser(1, "andi", "pass123");
            assertEquals("andi", loggedIn.getUsername());
        });
    }

    @Test
    @DisplayName("loginUser berhasil dengan kredensial yang benar")
    void testLoginUserSuccess() throws UserNotFoundException, LoginException {
        User user = new User(2, "sari", "rahasia", 100000.0);
        userService.registerUser(user);

        User loggedIn = userService.loginUser(2, "sari", "rahasia");
        assertNotNull(loggedIn);
        assertEquals("sari", loggedIn.getUsername());
    }

    @Test
    @DisplayName("loginUser gagal jika user tidak ditemukan")
    void testLoginUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> {
            userService.loginUser(999, "ghost", "nopass");
        });
    }

    @Test
    @DisplayName("loginUser gagal jika password salah")
    void testLoginUserWrongPassword() {
        User user = new User(3, "budi", "benar123", 75000.0);
        userService.registerUser(user);

        assertThrows(LoginException.class, () -> {
            userService.loginUser(3, "budi", "salah999");
        });
    }

    @Test
    @DisplayName("Status login awal adalah false")
    void testInitialLoginStatus() {
        assertFalse(userService.isIsLoggedIn());
        assertNull(userService.getActiveUser());
    }

    @Test
    @DisplayName("Logout mengatur ulang status login")
    void testLogout() throws UserNotFoundException, LoginException {
        User user = new User(4, "citra", "mypass", 200000.0);
        userService.registerUser(user);
        userService.loginUser(4, "citra", "mypass");

        userService.logout();
        assertFalse(userService.isIsLoggedIn());
        assertNull(userService.getActiveUser());
    }
}
