package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Model Tests")
public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "budi", "pass1234", 100000.0);
    }

    @Test
    @DisplayName("User dibuat dengan data yang benar")
    void testUserCreation() {
        assertEquals(1, user.getId());
        assertEquals("budi", user.getUsername());
        assertEquals("pass1234", user.getPassword());
        assertEquals(100000.0, user.getSaldo());
        assertFalse(user.isMember());
    }

    @Test
    @DisplayName("Saldo berkurang saat kurangiSaldo dipanggil")
    void testKurangiSaldo() {
        user.kurangiSaldo(30000.0);
        assertEquals(70000.0, user.getSaldo());
    }

    @Test
    @DisplayName("Saldo bertambah saat tambahSaldo dipanggil")
    void testTambahSaldo() {
        user.tambahSaldo(50000.0);
        assertEquals(150000.0, user.getSaldo());
    }

    @Test
    @DisplayName("User menjadi member setelah menjadiMember dipanggil")
    void testMenjadiMember() {
        assertFalse(user.isMember());
        user.menjadiMember();
        assertTrue(user.isMember());
    }

    @Test
    @DisplayName("Saldo tidak boleh menjadi negatif jika diurangi pas")
    void testSaldoNolSetelahKurangi() {
        user.kurangiSaldo(100000.0);
        assertEquals(0.0, user.getSaldo());
    }
}
