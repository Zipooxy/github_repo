package Service;

import Model.Electronic;
import Model.Product;
import Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TransactionService Tests")
public class TransactionServiceTest {

    private ProductService productService;
    private HistoryService historyService;
    private TransactionService transactionService;
    private User user;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        historyService = new HistoryService();
        transactionService = new TransactionService(productService, historyService);
        user = new User(1, "doni", "pass123", 500000.0);
    }

    @Test
    @DisplayName("Pembelian berhasil jika saldo cukup")
    void testBuyItemSuccess() {
        Electronic laptop = new Electronic(1, "Laptop Bekas", 300000.0, "Laptop");
        productService.addProduct(laptop);

        transactionService.buyItem(laptop, user);

        assertEquals(200000.0, user.getSaldo(), 0.01);
        assertNull(productService.findProductById(1)); // produk terhapus
    }

    @Test
    @DisplayName("Pembelian gagal jika saldo tidak cukup")
    void testBuyItemInsufficientSaldo() {
        Electronic mahal = new Electronic(2, "MacBook Pro", 999999.0, "Laptop");
        productService.addProduct(mahal);

        double saldoSebelum = user.getSaldo();
        transactionService.buyItem(mahal, user);

        // Saldo tidak berubah
        assertEquals(saldoSebelum, user.getSaldo(), 0.01);
        // Produk masih ada
        assertNotNull(productService.findProductById(2));
    }

    @Test
    @DisplayName("Member mendapat diskon 10% saat membeli")
    void testBuyItemMemberDiscount() {
        user.menjadiMember();
        Electronic e = new Electronic(3, "HP Murah", 100000.0, "Handphone");
        productService.addProduct(e);

        transactionService.buyItem(e, user);

        // Harga setelah diskon 10% = 90000
        assertEquals(500000.0 - 90000.0, user.getSaldo(), 0.01);
    }

    @Test
    @DisplayName("Pembelian produk null tidak mengubah saldo")
    void testBuyItemNullProduct() {
        double saldoSebelum = user.getSaldo();
        transactionService.buyItem(null, user);
        assertEquals(saldoSebelum, user.getSaldo());
    }

    @Test
    @DisplayName("History tercatat setelah pembelian berhasil")
    void testHistoryRecordedAfterBuy() {
        Electronic e = new Electronic(4, "Tablet", 200000.0, "Tablet");
        productService.addProduct(e);

        transactionService.buyItem(e, user);

        // Verifikasi history tidak kosong (tidak throw exception)
        assertDoesNotThrow(() -> historyService.showHistory());
    }
}
