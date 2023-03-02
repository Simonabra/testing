package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn(){
        //arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentSaldi(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn()  {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentBetalinger_LoggetInn(){
        //arrange
        List<Transaksjon> transaksjon = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(00065, "6373337733",
                1000, "2023-01-01", "innskudd", "yes", "3838383829");
        Transaksjon transaksjon2 = new Transaksjon(00065, "929292992",
                2000, "2023-02-01", "innskudd", "yes", "73737373");
        transaksjon.add(transaksjon1);
        transaksjon.add(transaksjon2);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(repository.hentBetalinger(anyString())).thenReturn(transaksjon);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertEquals(transaksjon, resultat);
    }
    @Test
    public void hentBetalinger_IkkeLoggetInn()  {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertNull(resultat);
    }

    @Test
    public void utforBetaling_LoggetInn(){
        //arrange
        List<Transaksjon> transaksjon = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(00065, "6373337733",
                1000, "2023-01-01", "innskudd", "yes", "3838383829");
        Transaksjon transaksjon2 = new Transaksjon(00065, "929292992",
                2000, "2023-02-01", "innskudd", "yes", "73737373");
        transaksjon.add(transaksjon1);
        transaksjon.add(transaksjon2);


        when(sjekk.loggetInn()).thenReturn("01010110523");
       when(repository.utforBetaling(anyInt())).thenReturn("OK");
       when(repository.hentBetalinger(anyString())).thenReturn(transaksjon);

        //act
       List<Transaksjon> resultat = bankController.utforBetaling(transaksjon.get(0).getTxID());

        // assert
       assertEquals(transaksjon, resultat);

    }

    @Test
    public void utforBetaling_ikkeLoggetInn(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);
        //act
        List<Transaksjon> resultat = bankController.utforBetaling(1);
        //assert
        assertNull(resultat);
    }

    @Test
    public void hentTransaksjoner_loggetInn(){
        // arrange
        List <Transaksjon> transaksjon = new ArrayList<>();
        Transaksjon transaksjon1 = new Transaksjon(00065, "6373337733",
                1000, "2023-01-01", "innskudd", "yes", "3838383829");
        Transaksjon transaksjon2 = new Transaksjon(00065, "929292992",
                2000, "2023-02-01", "innskudd", "yes", "73737373");
        transaksjon.add(transaksjon1);
        transaksjon.add(transaksjon2);

        Konto konti = new Konto("01010110523", "9957363633", 1000, "Brukskonto", "nok", transaksjon);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentTransaksjoner(anyString(), anyString(), anyString())).thenReturn(konti);

        // act
        Konto resultat = bankController.hentTransaksjoner("", "", "");

        // assert
        assertEquals(konti, resultat);

    }

    @Test
    public void hentTransaksjoner_ikkeLoggetInn(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Konto resultat = bankController.hentTransaksjoner("", "", "");

        //assert
        assertNull(resultat);

    }

    @Test
    public void endre_ikkeLoggetInn();
        when(sjekk.)kk

}

