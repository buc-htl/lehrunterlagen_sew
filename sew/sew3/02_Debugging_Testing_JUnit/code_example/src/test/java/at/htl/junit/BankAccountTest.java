package at.htl.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Namenskonvention: Klassenname + "Test"
// Testmethoden: methode_bedingung_erwartetesErgebnis
class BankAccountTest {

    private BankAccount account;



    // Läuft vor JEDEM Test -> jeder Test bekommt ein frisches Konto (Unabhängigkeit)
    @BeforeEach
    void setUp() {
        account = new BankAccount("Anna", 100);
    }


    // ---------- Normalfälle ----------

    @Test
    void deposit_positiveAmount_increasesBalance() {
        // Arrange: erledigt @BeforeEach (Konto mit 100)
        // Act
        account.deposit(50);
        // Assert
        assertEquals(150, account.getBalance());
    }

    @Test
    void withdraw_lessThanBalance_decreasesBalance() {
        account.withdraw(30);

        // Mit optionaler Fehlermeldung als letztem Parameter
        assertEquals(70, account.getBalance(), "Kontostand nach Abbuchung falsch");
    }

    @Test
    void getOwner_afterCreation_returnsName() {
        assertEquals("Anna", account.getOwner());
    }

    @Test
    void getBalance_afterCreation_isNotNegative() {
        assertTrue(account.getBalance() >= 0, "Kontostand darf nicht negativ sein");
    }

    // ---------- Grenzfälle ----------

    @Test
    void withdraw_exactlyWholeBalance_balanceIsZero() {
        account.withdraw(100);

        assertEquals(0, account.getBalance());
    }

    @Test
    void constructor_startBalanceZero_isAllowed() {
        BankAccount empty = new BankAccount("Ben", 0);

        assertEquals(0, empty.getBalance());
    }

    // ---------- Fehlerfälle und ungültige Eingaben: Exceptions ----------

    @Test
    void withdraw_moreThanBalance_throwsIllegalStateException() {
        assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(150)
        );
    }

    @Test
    void withdraw_negativeAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
    }

    @Test
    void deposit_zeroAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void constructor_negativeStartBalance_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Chris", -1));
    }

    @Test
    void withdraw_failed_balanceStaysUnchanged() {
        assertThrows(IllegalStateException.class, () -> account.withdraw(150));

        assertEquals(100, account.getBalance());
    }

    // ---------- Parametrisierter Test: viele Eingaben, eine Prüfung ----------

    // Jede Zeile = ein eigener Testlauf: Abbuchung, erwarteter Kontostand
    @ParameterizedTest
    @CsvSource({
            "30, 70",
            "1, 99",
            "99.5, 0.5",
            "100, 0"
    })
    void withdraw_validAmounts_returnsExpectedBalance(double amount, double expectedBalance) {
        account.withdraw(amount);

        assertEquals(expectedBalance, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({"0", "-1", "-10.5"})
    void withdraw_invalidAmounts_throwsIllegalArgumentException(double amount) {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
    }

    // double-Vergleiche: Toleranz (delta) angeben
    @Test
    void deposit_decimalAmounts_balanceWithinTolerance() {
        account.deposit(0.1);
        account.deposit(0.2);

        assertEquals(100.3, account.getBalance(), 0.0001);
    }
}
